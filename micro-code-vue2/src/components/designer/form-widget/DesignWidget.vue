<template>
  <el-form
    :size="designer.formConfig.size"
    :label-width="designer.formConfig.labelWidth + 'px'"
    :label-position="designer.formConfig.labelPosition"
    :disabled="designer.formConfig.disabled"
  >
    <RenderWidget :designer="designer" :widget="designer" />
  </el-form>
</template>

<script>
import request from '@/utils/request'
import './container-widget/index'
import RenderWidget from './RenderWidget'

export default {
  name: 'design-widget',
  inject: ['$model'],
  components: {
    RenderWidget
  },
  props: {
    designer: Object
  },
  provide() {
    return {
      wrapWith: this.wrapWith
    }
  },
  watch: {
    'designer.formConfig.dataCode'(dataCode) {
      if (dataCode) {
        const dataObject = new Function('return {' + dataCode + '}')()
        Object.assign(this.$model, dataObject)
      }
    }
  },
  mounted() {
    this.initDataSource()
    this.handleOnMounted()
  },
  methods: {
    refresh(widget) {
      if ('group' in widget && widget.options) {
        const group = widget.group
        const options = widget.options
        if (options.name) {
          const defaultValue = options.defaultValue
          if (group) {
            if (!this.$model.group) {
              this.$model.group = {}
            }
            this.$model.group[options.name] = defaultValue
          } else {
            this.$model[options.name] = defaultValue
          }
        }
      }
      if (widget.widgetList) {
        for (const w of widget.widgetList) {
          this.refresh(w)
        }
      }
    },
    handleOnCreated() {
      if (!!this.designer.formConfig && !!this.designer.formConfig.onCreated) {
        const customFunc = new Function(this.wrapWith(this.designer.formConfig.onCreated))
        customFunc.call(this)
      }
    },

    handleOnMounted() {
      if (!!this.designer.formConfig && !!this.designer.formConfig.onMounted) {
        const customFunc = new Function(this.wrapWith(this.designer.formConfig.onMounted))
        customFunc.call(this)
      }
    },
    wrapWith(event) {
      return 'with(this.$model){\n' + event + '\n}'
    },
    requestApi(api) {
      return () => {
        const param = {
          url: api.url,
          method: api.method,
          data: api.data
        }
        if (api.param) {
          param.params = new Function(this.wrapWith('return {' + api.param + '}')).call(this)
        }
        if (api.data) {
          param.data = new Function(this.wrapWith('return {' + api.data + '}')).call(this)
        }
        if (api.timeout) {
          param.timeout = api.timeout
        }
        let successFunction
        if (api.successFunction) {
          successFunction = new Function('res', this.wrapWith(api.successFunction))
        }
        let failFunction
        if (api.failFunction) {
          failFunction = new Function('err', this.wrapWith(api.failFunction))
        }
        request(param, { headers: api.header }).then(res => {
          if (successFunction) {
            successFunction.call(this, res)
          }
        }).catch(err => {
          if (failFunction) {
            failFunction.call(this, err)
          }
        })
      }
    },
    initDataSource() {
      const apis = this.designer.formConfig.apis
      if (apis) {
        for (const api of apis) {
          this.$model[api.name] = this.requestApi(api)
        }
      }
    }

  }
}
</script>
