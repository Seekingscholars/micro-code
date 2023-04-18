<template>
  <el-dialog
    v-bind="$attrs"
    width="880px"
    :show-close="true"
    append-to-body
    :close-on-click-modal="false"
    title="新建空白应用"
    @close="close"
  >
    <el-form label-width="80px" label-position="top">
      <el-form-item prop="appName" label="应用名称">
        <el-input v-model="modelForm.appName"></el-input>
      </el-form-item>
      <el-form-item prop="appName" label="应用分组">
        <el-input v-model="modelForm.categoryId"></el-input>
      </el-form-item>
      <el-form-item prop="imageName" label="图标">
        <el-input v-model="modelForm.imageName"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
    <el-button @click="close">取 消</el-button>
    <el-button type="primary" @click="submit">确 定</el-button>
  </span>
  </el-dialog>
</template>

<script>
export default {
  name: 'CreateEmptyAppDialog',
  data() {
    return {
      showAppDialogFlag: false,
      modelForm:{
        appName:'',
        categoryId:'',
        imageName:'',
        color:''
      }
    }
  },
  methods: {
    close() {
      this.$emit('update:visible', false)
    }
  }
}
</script>

<style>

</style>
