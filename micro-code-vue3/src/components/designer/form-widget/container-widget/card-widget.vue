<template>
  <el-card :shadow="field.options.shadow">
    <RenderWidget :designer="designer" :widget="field" />
  </el-card>
</template>

<script>
import RenderWidget from "../RenderWidget.vue";

export default {
  name: "CardWidget",
  componentName: "ContainerWidget",
  components: {
    RenderWidget
  },
  props: {
    field: Object,
    designer: Object
  }
};
</script>

<style lang="scss" scoped></style>
