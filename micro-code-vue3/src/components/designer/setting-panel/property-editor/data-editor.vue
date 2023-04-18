<template>
  <el-form-item :label="label" label-width="120px">
    <el-button icon="el-icon-edit" plain round type="info" @click="showWidgetEventDialogFlag=true">
      编辑数据
    </el-button>
    <el-dialog v-model="showWidgetEventDialogFlag" :close-on-click-modal="false"
               :close-on-press-escape="false"
               :destroy-on-close="true"
               :draggable="true"
               :show-close="true"
               :title="label"
    >
      <code-editor ref="ecEditor" v-model="data" :mode="'javascript'" :readonly="false"></code-editor>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="showWidgetEventDialogFlag = false">
            取消
          </el-button>
          <el-button type="primary" @click="saveEventHandler"> 保存</el-button>
        </div>
      </template>
    </el-dialog>
  </el-form-item>
</template>

<script>
import CodeEditor from '@/components/code-editor/index.vue'

export default {
  name: 'data-editor',
  props: {
    optionModel: Object,
    label: {
      type: String,
      default: '动态数据'
    }
  },
  components: {CodeEditor},
  data() {
    return {
      showWidgetEventDialogFlag: false,
      data: ''
    }
  },
  watch: {
    showWidgetEventDialogFlag(value) {
      if (value) {
        this.data = this.optionModel.data
      }
    }
  },
  methods: {
    saveEventHandler() {
      this.optionModel.data = this.data
      this.showWidgetEventDialogFlag = false
    }
  }

}
</script>

<style scoped>

</style>
