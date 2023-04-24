<template>
  <div class="form-widget-container">
    <FormWidget :designer="designer"></FormWidget>
  </div>
</template>

<script>
import '@/styles/global.scss'
import FormWidget from './FormWidget'

export default {
  name: 'VFormWidget',
  componentName: 'VFormWidget',
  components: {
    FormWidget
  },
  props: {
    designer: Object,
    formConfig: Object
  }
}
</script>

<style lang="scss">
.form-widget-container {
  padding: 10px;
  contain: paint;

  .form-widget-list {
    height: 60vh;
    padding: 10px;
  }

  .form-widget-item {
    min-height: 80px;
    padding: 10px;
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
