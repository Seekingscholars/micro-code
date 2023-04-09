<script lang="tsx">
import Draggable from "vuedraggable";
import FieldComponents from "./field-widget";
import ColWrapper from "./wrapper/ColWrapper.vue";
import StaticWrapper from "./wrapper/StaticWrapper.vue";
import FormItemWrapper from "./wrapper/FormItemWrapper.vue";

export default {
  name: "RenderWidget",
  componentName: "RenderWidget",
  components: {
    Draggable,
    ColWrapper,
    StaticWrapper,
    FormItemWrapper,
    ...FieldComponents
  },
  props: {
    designer: Object,
    widget: Object
  },
  data() {
    return {
      formModel: {},
      widgetRefList: {}
    };
  },
  computed: {
    disabled() {
      return this.designer.selectedPanel && this.designer === this.widget;
    }
  },
  methods: {
    getWidgetName(widget) {
      return widget.type + "-widget";
    },

    onDragAdd(evt) {
      const newIndex = evt.newIndex;
      if (!!this.widget.widgetList[newIndex]) {
        this.designer.setSelected(this.widget.widgetList[newIndex]);
      }
    },

    checkMove(evt) {
      return true;
    },
    renderForm() {
      const children = this.renderDraggable();
      return (
        <el-form
          size={this.designer.formConfig.size}
          labelWidth={this.designer.formConfig.labelWidth + "px"}
          labelPosition={this.designer.formConfig.labelPosition}
          disabled={this.designer.formConfig.disabled}
          class="full-height-width"
        >
          {children}
        </el-form>
      );
    },
    renderDraggable() {
      if (this.designer.design) {
        const slots = {
          item: ({ element, index }) => {
            return <div>{this.renderColSpanWrapper(element, index)}</div>;
          }
        };
        return (
          <draggable
            list={this.widget.widgetList}
            item-key="id"
            group="dragGroup"
            ghostClass="ghost"
            animation="300"
            tag="transition-group"
            component-data={{ name: "fade" }}
            handle=".drag-handler"
            onAdd={this.onDragAdd}
            disabled={this.disabled}
            move={this.checkMove}
            v-slots={slots}
          ></draggable>
        );
      } else {
        return (
          <div>
            {this.widget.widgetList.map((subWidget, index) => {
              return this.renderColSpanWrapper(subWidget, index);
            })}
          </div>
        );
      }
    },

    renderColSpanWrapper(subWidget, index) {
      return(
        <ColWrapper key={subWidget.id} field={subWidget}>
          {this.renderStaticWrapper(subWidget, index)}
        </ColWrapper>
      );
    },
    renderStaticWrapper(subWidget, index) {
      const children = this.renderFormItemWrapper(subWidget, index);
      if (
        this.designer.design &&
        subWidget.type != "dialog" &&
        subWidget.type != "drawer"
      ) {
        return (
          <StaticWrapper
            key={subWidget.id}
            designer={this.designer}
            parent-widget={this.widget}
            widget={subWidget}
            parent-list={this.widget.widgetList}
            index-of-parent-list={index}
          >
            {children}
          </StaticWrapper>
        );
      } else {
        return children;
      }
    },
    renderFormItemWrapper(subWidget, index) {
      const children = this.renderComponent(subWidget, index);
      if (!subWidget["form-item"] || subWidget["form-item"].disabled) {
        return children;
      } else {
        return (
          <FormItemWrapper
            key={subWidget.id}
            designer={this.designer}
            field={subWidget}
            parent-widget={this.widget}
          >
            {children}
          </FormItemWrapper>
        );
      }
    },
    renderComponent(subWidget, index) {
      const widgetName = this.getWidgetName(subWidget);
      const Component = this.$options.components[widgetName];
      return (
        <Component
          field={subWidget}
          designer={this.designer}
          key={subWidget.id}
          parent-list={this.widget.widgetList}
          index-of-parent-list={index}
          parent-widget={this.widget}
        ></Component>
      );
    }
  },

  render(h, context) {
    return this.renderForm();
  }
};
</script>
