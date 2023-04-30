<template>
  <el-button
    v-bind="field.options"
    @click="handleSubmit"
  >
    {{ field.options.label }}
  </el-button>
</template>
<script>

export default {
  name: 'submit-widget',
  componentName: 'FieldWidget',
  inject: ['validate'],
  props: {
    field: Object
  },
  methods: {
    handleSubmit() {
      this.validate(form => {
      })
    }
  }
}
</script>
<style lang="scss" scoped>

</style>
