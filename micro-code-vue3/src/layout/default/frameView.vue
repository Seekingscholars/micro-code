<script lang="ts" setup>
import {useRoute} from "vue-router";
import {nextTick, onMounted, ref, unref} from "vue";

defineOptions({
  name: "FrameView"
});

const loading = ref(true);
const currentRoute = useRoute();
const frameSrc = ref<string>("");
const frameRef = ref<HTMLElement | null>(null);

if (unref(currentRoute.meta)?.frameSrc) {
  frameSrc.value = unref(currentRoute.meta)?.frameSrc as string;
}
unref(currentRoute.meta)?.frameLoading === false && hideLoading();

function hideLoading() {
  loading.value = false;
}

function init() {
  nextTick(() => {
    const iframe = unref(frameRef);
    if (!iframe) return;
    const _frame = iframe as any;
    if (_frame.attachEvent) {
      _frame.attachEvent("onload", () => {
        hideLoading();
      });
    } else {
      iframe.onload = () => {
        hideLoading();
      };
    }
  });
}

onMounted(() => {
  init();
});
</script>

<template>
  <div v-loading="loading" class="frame" element-loading-text="加载中...">
    <iframe ref="frameRef" :src="frameSrc" class="frame-iframe"/>
  </div>
</template>

<style lang="scss" scoped>
.frame {
  height: calc(100vh - 88px);
  z-index: 998;

  .frame-iframe {
    width: 100%;
    height: 100%;
    overflow: hidden;
    border: 0;
    box-sizing: border-box;
  }
}
</style>
