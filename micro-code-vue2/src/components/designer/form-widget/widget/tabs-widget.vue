<template>
  <el-tabs v-model="activeTab" v-bind="field.options">
    <el-tab-pane v-for="(tab, index) in field.widgetList" :key="index" :label="tab.name"
                 :name="tab.value"
    >
      <RenderWidget :designer="designer" :widget="tab"></RenderWidget>
    </el-tab-pane>
  </el-tabs>
</template>
<script>
import RenderWidget from '../RenderWidget'

export default {
  name: 'tabs-widget',
  componentName: 'ContainerWidget',
  components: {
    RenderWidget
  },
  props: {
    field: Object,
    designer: Object
  },
  data() {
    return {
      activeTab: ''
    }
  },
  created() {
    if (!!this.field.options.active) {
      this.activeTab = this.field.options.active
    } else {
      this.activeTab = this.field.widgetList[0].value
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
