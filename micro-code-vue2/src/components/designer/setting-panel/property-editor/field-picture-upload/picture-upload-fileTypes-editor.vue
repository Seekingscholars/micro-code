<template>
  <el-form-item>
    <span slot="label">上传文件类型
      <el-tooltip content="支持添加其他文件类型" effect="light">
        <i class="el-icon-info"></i></el-tooltip>
    </span>
    <el-select v-model="optionModel.fileTypes" allow-create default-first-option filterable
               multiple style="width: 100%"
    >
      <el-option v-for="(ft, ftIdx) in uploadPictureTypes"
                 :key="ftIdx"
                 :label="ft.label"
                 :value="ft.value"
      >
      </el-option>
    </el-select>
  </el-form-item>
</template>

<script>


export default {
  name: 'picture-upload-fileTypes-editor',

  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  },
  data() {
    return {
      uploadPictureTypes: [
        {value: 'jpg', label: 'jpg'},
        {value: 'jpeg', label: 'jpeg'},
        {value: 'png', label: 'png'},
        {value: 'gif', label: 'gif'}
      ]
    }
  }
}
</script>

<style scoped>

</style>
