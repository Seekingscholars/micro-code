<template>
  <router-view />
</template>

<script lang="ts">
import { defineComponent } from "vue";
import Comps from "@/components/designer/form-widget/field-widget";
export default defineComponent({
  name: "App",
  components: {
    ...Comps
  }
});
</script>
