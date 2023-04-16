<template>
  <vue-editor v-model="fieldModel" :disabled="field.options.disabled"
              :editor-toolbar="customToolbar" :placeholder="field.options.placeholder"
              @blur="handleRichEditorBlurEvent"
              @focus="handleRichEditorFocusEvent" @text-change="handleRichEditorChangeEvent"
  >
  </vue-editor>
</template>

<script>

import { VueEditor } from 'vue2-editor'
import emitter from '@/utils/emitter'

import { deepClone } from '@/utils/util'
import fieldMixin from './fieldMixin'

export default {
  name: 'rich-editor-widget',
  componentName: 'FieldWidget',
  mixins: [emitter, fieldMixin],
  props: {
    field: Object,
    parentWidget: Object,
    parentList: Array,
    indexOfParentList: Number,
    designer: Object

  },
  components: {

    VueEditor
    // VueEditor: resolve => { //懒加载！！
    //   require(['vue2-editor'], ({VueEditor}) => resolve(VueEditor))
    // }
  },

  data() {
    return {
      oldFieldValue: null, //field组件change之前的值
      fieldModel: null,
      rules: [],

      customToolbar: [], //富文本编辑器自定义工具栏
      valueChangedFlag: false //vue2-editor数据值是否改变标志
    }
  },

  methods: {
    handleRichEditorChangeEvent() {
      this.valueChangedFlag = true
      this.syncUpdateFormModel(this.fieldModel)
    },

    handleRichEditorFocusEvent() {
      this.oldFieldValue = deepClone(this.fieldModel)
    },

    handleRichEditorBlurEvent() {
      if (this.valueChangedFlag) {
        this.emitFieldDataChange(this.fieldModel, this.oldFieldValue)
        this.valueChangedFlag = false
      }
    }

  }
}
</script>

<style lang="scss" scoped>


.full-width-input {
  width: 100% !important;
}

</style>
