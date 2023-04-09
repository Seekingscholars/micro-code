<template>
  <div class="form-widget-container">
    <div v-if="designer.widgetList.length === 0" class="no-widget-hint">
      请从左侧列表选择一个组件拖动放置于此处
    </div>
    <DesignWidget :designer="designer" />
  </div>
</template>

<script>
import "@/style/global.scss";
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
.container-scroll-bar {
  ::v-deep .el-scrollbar__wrap,
  ::v-deep .el-scrollbar__view {
    overflow-x: hidden;
  }
}

.form-widget-container {
  padding: 10px;
  background: #f1f2f3;
  overflow-x: hidden;
  overflow-y: auto;
  contain: paint;

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

  .el-form.full-height-width {
    height: 100%;
    padding: 3px;
    background: #ffffff;

    .form-widget-list {
      min-height: calc(100vh - 130px);
      padding: 10px;
    }

    .form-widget-item {
      min-height: 80px;
      padding: 10px;
    }
  }
}

.grid-cell {
  min-height: 30px;
  border-right: 1px dotted #cccccc;
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
