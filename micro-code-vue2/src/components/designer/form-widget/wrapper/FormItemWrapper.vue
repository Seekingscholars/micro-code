<template>
  <el-form-item
    :label="label"
    :label-width="labelWidth + 'px'"
    :prop="getPropName()"
    :rules="rules"
  >
    <slot />
  </el-form-item>
</template>

<script>
import FormValidators from '@/utils/validators'

export default {
  name: 'FormItemWrapper',
  props: {
    field: Object,
    designer: Object
  },
  inject: ['$model'],
  data() {
    const validator = (rule, value, callback) => {
      const formItem = this.field['form-item']
      const required = formItem.required
      const validation = formItem.validation
      if (required || validation) {
        const group = this.field.options.group
        const name = this.field.options.name
        const validationHint = formItem.validationHint
        let dataModel
        if (group) {
          dataModel = this.$model[group]
        } else {
          dataModel = this.$model
        }
        value = dataModel[name]
        if (required) {
          if (!value) {
            if (validationHint) {
              callback(validationHint)
            } else {
              callback(formItem.label + '不能为空')
            }
            return
          }
        }
        if (validation) {
          const validator = FormValidators[validation]
          if (validator) {
            validator({ label: formItem.label, errorMsg: validationHint }, value, callback)
          } else {
            callback('校验器' + validation + '未找到')
          }
          return
        }
      }
      callback()
    }
    return {
      rules: {
        required: this.field['form-item'].required,
        validator: validator,
        trigger: ['blur', 'change']
      }
    }
  },
  computed: {
    label() {
      if (this.field['form-item'].hidden) {
        return ''
      }
      return this.field['form-item'].label
    },

    labelWidth() {
      if (this.field['form-item'].hidden) {
        return 0
      }
      if (this.field['form-item'].width) {
        return this.field['form-item'].width
      }
      return this.designer.formConfig.labelWidth
    }
  },
  methods: {
    getPropName() {
      return this.field.options.name
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
