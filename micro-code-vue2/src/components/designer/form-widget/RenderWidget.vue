<script>
import Draggable from 'vuedraggable'
import FieldComponents from './field-widget'
import ColWrapper from './wrapper/ColWrapper'
import StaticWrapper from './wrapper/StaticWrapper'
import FormItemWrapper from './wrapper/FormItemWrapper'

export default {
  name: 'RenderWidget',
  componentName: 'RenderWidget',
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
    }
  },
  computed: {
    disabled() {
      return this.designer.selectedPanel && this.designer === this.widget
    }
  },
  methods: {
    getWidgetName(widget) {
      return widget.type + '-widget'
    },

    onDragAdd(evt) {
      const newIndex = evt.newIndex
      if (this.widget.widgetList[newIndex]) {
        this.designer.setSelected(this.widget.widgetList[newIndex])
      }
    },

    checkMove(evt) {
      return true
    },
    renderDraggable() {
      const children = this.renderTransitionGroup()
      if (this.designer.design) {
        return <draggable list={this.widget.widgetList} group='dragGroup' ghostClass='ghost' animation='300'
          handle='.drag-handler' onAdd={this.onDragAdd} disabled={this.disabled}
          move={this.checkMove}
        >
          {children}
        </draggable>
      } else {
        return <div>{children}</div>
      }
    },
    renderTransitionGroup() {
      const children = this.renderColSpanWrapper()
      if (this.designer.design) {
        let widgetClass
        if (this.designer === this.widget) {
          widgetClass = 'form-widget-list'
        } else {
          widgetClass = 'form-widget-item'
        }
        return <transition-group name='fade' tag='div' class={widgetClass}>
          {children}
        </transition-group>
      } else {
        return children
      }
    },
    renderColSpanWrapper() {
      return this.widget.widgetList.map((subWidget, index) => {
        return <ColWrapper key={subWidget.id} field={subWidget}
        >{this.renderStaticWrapper(subWidget, index)}</ColWrapper>
      })
    },
    renderStaticWrapper(subWidget, index) {
      const children = this.renderFormItemWrapper(subWidget, index)
      if (this.designer.design) {
        return <StaticWrapper key={subWidget.id} designer={this.designer} parent-widget={this.widget} widget={subWidget}
          parent-list={this.widget.widgetList} index-of-parent-list={index}
        >
          {children}
        </StaticWrapper>
      } else {
        return children
      }
    },
    renderFormItemWrapper(subWidget, index) {
      const children = this.renderComponent(subWidget, index)
      if (!subWidget['form-item'] || subWidget['form-item'].disabled) {
        return children
      } else {
        return <FormItemWrapper key={subWidget.id} designer={this.designer} field={subWidget}
          parent-widget={this.widget}
        >{children}</FormItemWrapper>
      }
    },
    renderComponent(subWidget, index) {
      const widgetName = this.getWidgetName(subWidget)
      const Component = this.$options.components[widgetName]
      return <Component field={subWidget} designer={this.designer} key={subWidget.id}
        parent-list={this.widget.widgetList}
        index-of-parent-list={index} parent-widget={this.widget}
      ></Component>
    }
  },

  render(h, context) {
    return this.renderDraggable()
  }
}
</script>
