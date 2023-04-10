<script lang="tsx">
import Draggable from "@/components/draggable";
import ColWrapper from "./wrapper/ColWrapper.vue";
import StaticWrapper from "./wrapper/StaticWrapper.vue";
import FormItemWrapper from "./wrapper/FormItemWrapper.vue";

export default {
  name: "render-widget",
  componentName: "RenderWidget",
  components: {
    Draggable,
    ColWrapper,
    StaticWrapper,
    FormItemWrapper
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
    renderDraggable() {
      if (this.designer.design) {
        const slots = {
          item: ({ element, index }) =>
            this.renderColSpanWrapper(element, index)
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
        return this.widget.widgetList.map((subWidget, index) =>
          this.renderColSpanWrapper(subWidget, index)
        );
      }
    },

    renderColSpanWrapper(subWidget, index) {
      return (
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
      const Component = this.$root.$options.components[widgetName]
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
    return this.renderDraggable();
  }
};
</script>
