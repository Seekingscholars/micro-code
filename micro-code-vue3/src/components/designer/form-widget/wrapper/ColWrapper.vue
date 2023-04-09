<template>
  <el-col :span="field.options.span">
    <slot></slot>
  </el-col>
</template>

<script>

export default {
  name: 'col-wrapper',
  props: {
    field: Object
  }
}
</script>

<style lang="scss" scoped>

</style>
