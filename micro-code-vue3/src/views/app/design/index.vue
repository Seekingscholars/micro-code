<template>
  <FormDesigner ref="FormDesignerRef">
    <template #header>
      <div class="header">
        <Logo />
        <div class="header-right">
          <el-button :icon="Search" color="#626aef" @click="previewForm"
            >预览</el-button
          >
        </div>
      </div>
    </template>
  </FormDesigner>
</template>

<script setup name="AppDesign">
import { ref } from "vue";
import { useRouter } from "vue-router";
import { Search } from "@element-plus/icons-vue";
import Logo from "@/layout/default/components/logo.vue";
import FormDesigner from "@/components/designer/index.vue";
const FormDesignerRef = ref(null);
const router = useRouter();

function previewForm() {
  const formJson = FormDesignerRef.value.getFormJson();
  const key = "preview";
  const storage = window.localStorage;
  storage.setItem(key, JSON.stringify(formJson));
  const to = router.resolve({
    name: "app-preview"
  });
  window.open(to.href, "_blank");
}
</script>
<style scoped>
.header {
  display: flex;
  justify-content: space-between;
}
.header-right {
  height: 100%;
  line-height: 48px;
  margin-right: 20px;
}
</style>
