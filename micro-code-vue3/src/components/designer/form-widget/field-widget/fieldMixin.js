export default {
  props: {
    field: Object,
    parentWidget: Object,
    designer: Object
  },
  inject: ['$model', 'wrapWith'],

  watch: {
    'field.options.defaultValue'(newValue) {
      this.refreshDefaultValue(newValue)
    }
  },
  data() {
    return {
      fieldModel: null,
      dataModel: {}
    }
  },
  created() {
    let group
    if ('group' in this.field.options) {
      group = this.field.options.group
      if (!group && this.parentWidget.options) {
        group = this.parentWidget.options.name
      }
    }
    if (group) {
      this.field.options.group = group
      let dataModel = this.$model[group]
      if (!dataModel) {
        dataModel = {}
        this.$model[group]=dataModel
      }
      this.dataModel = dataModel
    } else {
      this.dataModel = this.$model
    }
    if (!this.dataModel[this.field.options.name]) {
      this.refreshDefaultValue(this.field.options.defaultValue)
    }
  },
  methods: {
    callFunc(func) {
      const self = this

      function call() {
        func.call(self)
      }

      return call
    },
    getListeners(field) {
      const eventMap = {}
      if (field && field.events) {
        Object.keys(field.events).forEach(name => {
          const event = field.events[name]
          if (event) {
            const func = new Function(this.wrapWith(event))
            eventMap[name] = this.callFunc(func)
          }
        })
      }
      return eventMap
    },
    refreshDefaultValue(value) {
      this.dataModel[this.field.options.name]=value
    },
    $(name) {
      return this.designer.findWidgetByName(name)
    },
    $attr(name) {
      const widget = this.designer.findWidgetByName(name)
      return widget.options
    }
  }
}
