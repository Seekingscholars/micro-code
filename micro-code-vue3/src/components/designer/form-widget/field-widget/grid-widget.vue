<template>
  <el-row
    :class="[designer.design ? 'grid-container' : '']"
    v-bind="field.options"
  >
    <RenderWidget :designer="designer" :widget="field" />
  </el-row>
</template>

<script>
import RenderWidget from "../RenderWidget.vue";

export default {
  name: "grid-widget",
  componentName: "GridWidget",
  components: {
    RenderWidget
  },
  props: {
    field: Object,
    designer: Object
  }
};
</script>
<style>
.grid-container {
  padding: 10px;
  outline: 1px dashed #336699;
  min-height: 80px;
}
</style>
