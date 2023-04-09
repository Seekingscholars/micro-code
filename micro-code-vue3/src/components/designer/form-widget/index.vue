<template>
  <div class="form-widget-container">
    <div v-if="designer.widgetList.length === 0" class="no-widget-hint">
      请从左侧列表选择一个组件拖动放置于此处
    </div>
    <DesignWidget :designer="designer"/>
  </div>
</template>

<script>
import DesignWidget from "./DesignWidget.vue";

export default {
  name: "VFormWidget",
  componentName: "VFormWidget",
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
  height: calc(100vh - 92px);

  .no-widget-hint {
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;
    font-size: 24px;
    color: #e59d9d;
  }
  .full-height-width {
    height: 100%;
  }
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>
