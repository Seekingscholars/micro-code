<template>
  <div class="block">
    <el-timeline>
      <el-timeline-item placement="top" timestamp="2018/4/12">
        <el-card>
          <h4>更新 Github 模板</h4>
          <p>王小虎 提交于 2018/4/12 20:46</p>
        </el-card>
      </el-timeline-item>
      <el-timeline-item placement="top" timestamp="2018/4/3">
        <el-card>
          <h4>更新 Github 模板</h4>
          <p>王小虎 提交于 2018/4/3 20:46</p>
        </el-card>
      </el-timeline-item>
      <el-timeline-item placement="top" timestamp="2018/4/2">
        <el-card>
          <h4>更新 Github 模板</h4>
          <p>王小虎 提交于 2018/4/2 20:46</p>
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </div>
</template>

<script>


export default {
  name: 'timeline-widget',
  componentName: 'FieldWidget',
  props: {
    designer: Object,
    field: Object
  },

  inject: ['$model'],

  data() {
    return {}
  }

}
</script>

<style lang="scss" scoped>


</style>
