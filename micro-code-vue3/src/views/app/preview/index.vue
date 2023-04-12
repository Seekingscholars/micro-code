<template>
  <div>
  <FormRender v-if="flushed" :formJson="formJson" />
  </div>
</template>
<script setup name="AppPreview">
import { ref } from "vue";
import FormRender from "@/components/render/index.vue";
const formJson = ref({});
const flushed = ref(false);
const storage = window.sessionStorage;
const key = "preview";
const formValue = storage.getItem(key);
if (formValue) {
  formJson.value = JSON.parse(formValue);
  flushed.value = true;
}
</script>

<style scoped></style>
