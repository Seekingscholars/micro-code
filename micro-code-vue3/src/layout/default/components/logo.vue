<script lang="ts" setup>
import {useNav} from "@/layout/default/hooks/useNav";

const props = defineProps({
  collapse: Boolean
});

const {title} = useNav();
</script>
<template>
  <div :class="{ collapses: props.collapse }" class="sidebar-logo-container">
    <transition name="sidebarLogoFade">
      <router-link
        v-if="props.collapse"
        key="props.collapse"
        :title="title"
        class="sidebar-logo-link"
        to="/"
      >
        <FontIcon icon="team-iconlogo" style="width: 35px; height: 35px" svg/>
        <span class="sidebar-title">{{ title }}</span>
      </router-link>
      <router-link
        v-else
        key="expand"
        :title="title"
        class="sidebar-logo-link"
        to="/"
      >
        <FontIcon icon="team-iconlogo" style="width: 35px; height: 35px" svg/>
        <span class="sidebar-title">{{ title }}</span>
      </router-link>
    </transition>
  </div>
</template>

<style lang="scss" scoped>
.sidebar-logo-container {
  position: relative;
  width: 300px;
  height: 48px;
  text-align: center;
  overflow: hidden;

  .sidebar-logo-link {
    padding-left: 10px;
    display: flex;

    .sidebar-title {
      display: block;
      width: 160px;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
      text-align: left;
      color: #1890ff;
      font-weight: 600;
      font-size: 20px;
      margin-top: 10px;
      font-family: Avenir, Helvetica Neue, Arial, Helvetica, sans-serif;
    }
  }

  .collapses {
    .sidebar-logo {
      margin-right: 0;
    }
  }
}
</style>
