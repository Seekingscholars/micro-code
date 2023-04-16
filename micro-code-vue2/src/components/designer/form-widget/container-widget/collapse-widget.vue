<template>
  <el-collapse v-model="activeTab">
    <el-collapse-item v-for="(item, index) in field.options.tabs" :key="index" :name="item.value"
                      :title="item.label"
    >
      <RenderWidget :designer="designer" :widget="item"></RenderWidget>
    </el-collapse-item>
  </el-collapse>
</template>
<script>
import RenderWidget from '../RenderWidget'

export default {
  name: 'collapse-widget',
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
      activeTab: []
    }
  },
  created() {
    const active = this.field.options.active
    if (!!active && Array.isArray(active) && active.length > 0) {
      this.activeTab = active
    } else {
      this.activeTab = this.field.options.tabs.map(tab => tab.value)
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
