<template>
  <FormRender v-if="flushed" :formJson="formJson" />
</template>
<script setup name="AppPreview">
import { ref } from "vue";
import FormRender from "@/components/render/index.vue";
import { useAppStoreHook } from "@/store/modules/app";
const flushed = ref(false);
const formJson = ref({});
console.log('aaaaaaaaa')
formJson.value=useAppStoreHook().getFormJson
console.log('aaaaaaaaa')
console.log(formJson.value)
// const router = useRouter();
// const query = router.currentRoute.value.query;
// formJson.value = JSON.parse(query.formJson);
flushed.value = true;
</script>

<style scoped></style>
