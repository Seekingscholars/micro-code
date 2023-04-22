<template>
  <div>
    <el-form-item label="change" label-width="120px">
      <el-button icon="el-icon-edit" plain round type="info" @click="openEvent">
        编写代码
      </el-button>
    </el-form-item>
    <el-dialog
      :visible.sync="showEventDialogFlag"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :destroy-on-close="true"
      :show-close="true"
      class="small-padding-dialog"
      title="组件事件处理"
    >
      <el-alert :closable="false" :title="title" type="info" />
      <code-editor v-model="eventCode" :mode="'javascript'" :readonly="false" />
      <el-alert :closable="false" title="}" type="info" />
      <div slot="footer" class="dialog-footer">
        <el-button @click="showEventDialogFlag=false">
          取消
        </el-button>
        <el-button type="primary" @click="submit">
          确定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import CodeEditor from '@/components/code-editor/index'
export default {
  components: { CodeEditor },
  props: {
    designer: Object,
    eventName: String,
    eventParam: String
  },
  data() {
    return {
      eventCode: '',
      showEventDialogFlag: false
    }
  },
  computed: {
    title() {
      return this.eventName + '(){'
    }
  },
  methods: {
    openEvent() {
      this.eventCode = this.designer.selectedWidget.events[this.eventName]
      this.showEventDialogFlag=true
    },
    submit() {
      this.designer.selectedWidget.events[this.eventName] = this.eventCode
      this.showEventDialogFlag=false
    }
  }
}
</script>

<style scoped>

</style>
