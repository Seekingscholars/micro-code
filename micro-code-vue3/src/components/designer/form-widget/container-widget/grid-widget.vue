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
  name: "GridWidget",
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
