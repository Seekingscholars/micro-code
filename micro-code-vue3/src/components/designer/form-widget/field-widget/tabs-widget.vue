<template>
  <el-tabs v-model="activeTab" v-bind="field.options">
    <el-tab-pane
      v-for="(tab, index) in field.options.tabs"
      :key="index"
      :label="tab.label"
      :name="tab.value"
    >
      <RenderWidget :designer="designer" :widget="tab" />
    </el-tab-pane>
  </el-tabs>
</template>
<script>
import RenderWidget from "../RenderWidget.vue";

export default {
  name: "tabs-widget",
  componentName: "ContainerWidget",
  components: {
    RenderWidget
  },
  props: {
    field: Object,
    designer: Object
  },
  data() {
    return {
      activeTab: ""
    };
  },
  created() {
    if (!!this.field.options.active) {
      this.activeTab = this.field.options.active;
    } else {
      this.activeTab = this.field.options.tabs[0].value;
    }
  }
};
</script>

<style lang="scss" scoped></style>
