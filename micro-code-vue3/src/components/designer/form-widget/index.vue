<template>
  <div class="form-widget-container">
    <DesignWidget :designer="designer"/>
  </div>
</template>

<script>
import DesignWidget from "./DesignWidget.vue";

export default {
  name: "FormWidget",
  componentName: "FormWidget",
  components: {
    DesignWidget
  },
  props: {
    designer: Object,
    formConfig: Object
  }
};
</script>

<style lang="scss">
.form-widget-container {
  padding: 10px;
  contain: paint;

  .form-height {
    min-height: 60vh;
  }

  .fade-enter-active,
  .fade-leave-active {
    transition: opacity 0.5s;
  }

  .fade-enter,
  .fade-leave-to {
    opacity: 0;
  }
}
</style>
