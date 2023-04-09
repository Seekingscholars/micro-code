import {
  deepClone,
  generateId,
  getDefaultFormConfig,
  overwriteObj
} from "@/utils/util";

export function createDesigner(vueInstance) {
  let defaultFormConfig = getDefaultFormConfig();

  return {
    widgetList: [],
    formConfig: {
      size: "",
      labelWidth: 80,
      labelPosition: "left",
      disabled: false,
      dataCode: "",
      functionCode: "",
      onCreated: "",
      onMounted: "",
      apis: []
    },
    design: true,
    selectedId: null,
    selectedWidget: null,
    selectedPanel: null,
    selectedWidgetName: null, //选中组件名称（唯一）
    vueInstance: vueInstance,
    formWidget: null, //表单设计容器
    initDesigner() {
      this.widgetList = [];
      this.formConfig = deepClone(defaultFormConfig);
    },
    clearDesigner() {
      this.widgetList = [];
      this.selectedId = null;
      this.selectedWidgetName = null;
      this.selectedWidget = {}; //this.selectedWidget = null
      overwriteObj(this.formConfig, defaultFormConfig); //
    },

    loadFormJson(formJson) {
      let modifiedFlag = false;

      if (!!formJson && !!formJson.widgetList) {
        this.widgetList = formJson.widgetList;
        modifiedFlag = true;
      }
      if (!!formJson && !!formJson.formConfig) {
        //this.formConfig = importObj.formConfig
        overwriteObj(
          this.formConfig,
          formJson.formConfig
        ); /* 用=赋值，会导致inject依赖注入的formConfig属性变成非响应式 */
        modifiedFlag = true;
      }

      return modifiedFlag;
    },

    setSelected(selected) {
      if (!selected) {
        this.clearSelected();
        return;
      }
      this.selectedWidget = selected;
      if (!!selected.id) {
        this.selectedId = selected.id;
        this.selectedWidgetName = selected.options.name;
      }
    },
    clearSelected() {
      this.selectedId = null;
      this.selectedWidgetName = null;
      this.selectedWidget = {}; //this.selectedWidget = null
    },

    cloneWidget(widget) {
      return deepClone(widget, (data, label) => {
        if (label === "id") {
          return generateId();
        }
        return data[label];
      });
    },
    findWidgetById(widgetId) {
      return this.findWidget(widget => widget.id === widgetId);
    },
    findWidgetByName(widgetName) {
      return this.findWidget(widget => {
        if (widget.options) {
          return widget.options.name === widgetName;
        } else {
          return false;
        }
      });
    },
    findWidget(handler, parentWidget = this) {
      if (!parentWidget) {
        return;
      }
      if (handler(parentWidget)) {
        return parentWidget;
      }
      if (parentWidget.widgetList) {
        for (let widget of parentWidget.widgetList) {
          const targetWidget = this.findWidget(handler, widget);
          if (targetWidget) {
            return targetWidget;
          }
        }
      }
    },
    removeWidgetById(widgetId, parentWidget = this) {
      if (!parentWidget) {
        return;
      }
      if (parentWidget.widgetList) {
        const widgetList = parentWidget.widgetList;
        for (let i = 0; i < widgetList.length; i++) {
          const widget = widgetList[i];
          if (widget.id === widgetId) {
            widgetList.splice(i, 1);
            return;
          } else {
            this.removeWidgetById(widgetId, widget);
          }
        }
      }
    }
  };
}
