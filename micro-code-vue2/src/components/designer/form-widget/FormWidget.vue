<template>
  <div class="form-container">
    <div class="form-main" :style="{width:designer.formConfig.width}">
      <el-form
        ref="form"
        :model="$model"
        :disabled="designer.formConfig.disabled"
        :label-position="designer.formConfig.labelPosition"
        :label-width="designer.formConfig.labelWidth + 'px'"
        :size="designer.formConfig.size"
      >
        <RenderWidget :designer="designer" :widget="designer" />
      </el-form>
    </div>
  </div>
</template>

<script>
import request from '@/utils/request'
import './widget/index'
import RenderWidget from './RenderWidget'

export default {
  name: 'DesignWidget',
  inject: ['$model'],
  components: {
    RenderWidget
  },
  props: {
    designer: Object
  },
  provide() {
    return {
      wrapWith: this.wrapWith,
      validate: this.validate
    }
  },
  created() {
    this.handleOnCreated()
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
      if (this.designer.formConfig.dataCode) {
        const customData = new Function('return {' + this.designer.formConfig.dataCode + '}').call(this)
        Object.keys(customData).forEach(key => this.$set(this.$model, key, customData[key]))
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
    },
    validate: function(callback) {
      this.$refs.form.validate(valid => {
        if (valid && callback) {
          callback(this.$model)
        }
      })
    }
  }
}
</script>
<style scoped>
.form-container{
  width: 100%;
  height: 100%;
  background-color: #f5f6f8;
  position: relative;
}
.form-main{
  height: 100%;
  background-color: #ffffff;
  width: 100%;
  margin: 0 auto;
  position: relative;
  padding: 10px;
}
</style>
