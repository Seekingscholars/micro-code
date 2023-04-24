<template>
  <div class="process-container">
    <div class="process-main">
      <FormWidget ref="FormWidgetRef" :designer="designer"></FormWidget>
    </div>
    <div class="process-footer">
      <el-button type="success" @click="submit">提交</el-button>
      <el-button type="primary">保存草稿</el-button>
    </div>
  </div>
</template>

<script>
import FormWidget from './FormWidget'

export default {
  name: 'ProcessWidget',
  componentName: 'ProcessWidget',
  components: {
    FormWidget
  },
  props: {
    designer: Object
  },
  methods:{
    submit(){
      this.$refs.FormWidgetRef.validate((model)=>{
        debugger
      })
    }
  }
}
</script>
<style scoped>
.process-container{
  width: 780px;
  margin: 0 auto;
  border: 1px solid gainsboro;
}
.process-main{
  min-height: 80px;
}
.process-footer{
  text-align: center;
}
</style>
