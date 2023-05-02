<template>
  <el-divider :content-position="field.options.contentPosition" direction="horizontal">
    {{ field.options.label }}
  </el-divider>
</template>

<script>
import fieldMixin from './fieldMixin'

export default {
  name: 'divider-widget',
  componentName: 'FieldWidget',
  mixins: [fieldMixin]
}
</script>
