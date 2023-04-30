import { deepClone, generateId } from '@/utils/util'

export function createDesigner(vueInstance) {
  return {
    widgetList: [],
    formConfig: {
      width: '100%',
      size: '',
      labelWidth: 80,
      labelPosition: 'left',
      disabled: false,
      dataCode: '',
      functionCode: '',
      onCreated: '',
      onMounted: ''
    },
    design: true,
    selectedId: null,
    selectedWidget: null,
    selectedPanel: null,
    selectedWidgetName: null, // 选中组件名称（唯一）
    vueInstance: vueInstance,
    formWidget: null, // 表单设计容器
    loadFormJson(formJson) {
      let modifiedFlag = false

      if (!!formJson && !!formJson.widgetList) {
        this.widgetList = formJson.widgetList
        modifiedFlag = true
      }
      if (!!formJson && !!formJson.formConfig) {
        this.formConfig = Object.assign(this.formConfig, formJson.formConfig)
        modifiedFlag = true
      }

      return modifiedFlag
    },

    setSelected(selected) {
      if (!selected) {
        this.clearSelected()
        return
      }
      this.selectedWidget = selected
      if (selected.id) {
        this.selectedId = selected.id
        this.selectedWidgetName = selected.options.name
      }
    },
    clearSelected() {
      this.selectedId = null
      this.selectedWidgetName = null
      this.selectedWidget = {}
    },

    cloneWidget(widget) {
      return deepClone(widget, (data, label) => {
        if (label === 'id') {
          return generateId()
        }
        return data[label]
      })
    },
    findWidgetById(widgetId) {
      return this.findWidget(widget => widget.id === widgetId)
    },
    findWidgetByName(widgetName) {
      return this.findWidget(widget => {
        if (widget.options) {
          return widget.options.name === widgetName
        } else {
          return false
        }
      })
    },
    findWidget(handler, parentWidget = this) {
      if (!parentWidget) {
        return
      }
      if (handler(parentWidget)) {
        return parentWidget
      }
      if (parentWidget.widgetList) {
        for (const widget of parentWidget.widgetList) {
          const targetWidget = this.findWidget(handler, widget)
          if (targetWidget) {
            return targetWidget
          }
        }
      }
    }
  }
}
