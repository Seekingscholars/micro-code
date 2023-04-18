<template>
  <el-dialog
    :close-on-click-modal="false"
    :show-close="true"
    append-to-body
    title="新建空白应用"
    v-bind="$attrs"
    width="880px"
    @close="close"
  >
    <el-form :model="modelForm" label-position="top" label-width="80px">
      <el-form-item label="应用名称" prop="appName">
        <el-input v-model="modelForm.appName"></el-input>
      </el-form-item>
      <el-form-item label="应用分组" prop="appName">
        <el-input v-model="modelForm.categoryId"></el-input>
      </el-form-item>
      <el-form-item label="图标" prop="imageName">
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
import appApi from './api/app.api'
export default {
  name: 'CreateEmptyAppDialog',
  data() {
    return {
      showAppDialogFlag: false,
      modelForm: {
        appName: '',
        categoryId: '',
        imageName: '',
        color: ''
      }
    }
  },
  methods: {
    close() {
      this.$emit('update:visible', false)
    },
    async submit(){
      await appApi.save(this.modelForm);
      this.$emit('ok')
      this.close()
    }
  }
}
</script>

<style>

</style>
