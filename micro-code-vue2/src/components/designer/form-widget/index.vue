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
  height: 100%;
  overflow-x: auto;
  .form-widget-list {
    height: calc(100vh - 100px);
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
