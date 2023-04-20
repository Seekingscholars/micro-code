<template>
  <el-dialog
    :close-on-click-modal="false"
    :show-close="true"
    append-to-body
    :title="title"
    v-bind="$attrs"
    width="880px"
    @close="close"
  >
    <el-form :model="modelForm" label-position="top" label-width="80px">
      <el-form-item label="应用名称" prop="appName">
        <el-input v-model="modelForm.appName" placeholder="请输入应用名称" />
      </el-form-item>
      <el-form-item label="应用分组" prop="appName">
        <el-select v-model="modelForm.categoryId" placeholder="请选择应用分组" style="width: 100%">
          <el-option v-for="item in categoryList" :key="item.id" :label="item.categoryName" :value="item.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="图标" prop="imageName">
        <el-popover placement="right-start" width="400" trigger="hover">
          <Icons :model="modelForm" />
          <div slot="reference" class="icon" :style="{backgroundColor:modelForm.color}">
            <svg-icon class="svg-icon" :icon-class="modelForm.imageName" />
          </div>
        </el-popover>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="submit">确 定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import Icons from './components/icons/index.vue'
import appApi from '@/api/app'
import categoryApi from '@/api/category'
export default {
  name: 'CreateEmptyAppDialog',
  components: { Icons },
  props: {
    app: Object
  },
  data() {
    return {
      showAppDialogFlag: false,
      categoryList: [],
      title: '新建空白应用',
      modelForm: {
        appName: '',
        categoryId: '',
        imageName: 'document',
        color: '#1E90FF'
      }
    }
  },
  created() {
    this.listCategory()
    if (this.app) {
      this.modelForm = Object.assign(this.modelForm, this.app)
      this.title = '修改应用信息'
    }
  },
  methods: {
    close() {
      this.$emit('update:visible', false)
    },
    async submit() {
      await appApi.saveOrEdit(this.modelForm, !!this.app)
      this.$emit('ok')
      this.close()
    },
    async listCategory() {
      this.categoryList = await categoryApi.list() || []
    }
  }
}
</script>

<style scoped>
.icon{
  position: relative;
  height: 48px;
  width: 48px;
  text-align: center;
  line-height: 48px;
  border-radius: 12px;
}
.svg-icon{
  color: #ffffff;
}
</style>
