<template>
  <FormDesigner ref="FormDesignerRef">
    <template slot="header">
      <div class="header">
        <Logo/>
        <div class="header-right">
          <el-button
            icon="el-icon-search"
            @click="previewForm"
          >预览
          </el-button>
          <el-button
            icon="el-icon-plus"
            type="primary"
            @click="previewForm"
          >保存
          </el-button>
        </div>
      </div>
    </template>
  </FormDesigner>
</template>

<script>
import FormDesigner from '@/components/designer/index.vue'
import Logo from '@/layout/components/Logo.vue'

export default {
  components: {FormDesigner, Logo},
  methods: {
    previewForm() {
      const formJson = this.$refs.FormDesignerRef.getFormJson()
      const key = 'preview'
      const storage = window.sessionStorage
      storage.setItem(key, JSON.stringify(formJson))
      const to = this.$router.resolve({
        name: 'app-preview'
      })
      window.open(to.href, '_blank')
    }
  }
}
</script>
<style scoped>
.header {
  display: flex;
  justify-content: space-between;
}

.header-right {
  display: flex;
  height: 100%;
  line-height: 48px;
  margin-right: 20px;
}
</style>
