<script lang="ts" setup>
import {useRoute} from "vue-router";
import {emitter} from "@/utils/mitt";
import SidebarItem from "./sidebarItem.vue";
import {useNav} from "@/layout/default/hooks/useNav";
import {storageLocal} from "@pureadmin/utils";
import {computed, onBeforeMount, ref, watch} from "vue";
import {findRouteByPath, getParentPaths} from "@/router/utils";
import {usePermissionStoreHook} from "@/store/modules/permission";

const route = useRoute();
const showLogo = ref(
  storageLocal().getItem<StorageConfigs>("responsive-configure")?.showLogo ??
  true
);

const {routers, pureApp, isCollapse, menuSelect, toggleSideBar} =
  useNav();

const subMenuData = ref([]);

const menuData = computed(() => {
  return usePermissionStoreHook().wholeMenus;
});

function getSubMenuData(path: string) {
  // path的上级路由组成的数组
  const parentPathArr = getParentPaths(
    path,
    usePermissionStoreHook().wholeMenus
  );
  // 当前路由的父级路由信息
  const parenetRoute = findRouteByPath(
    parentPathArr[0] || path,
    usePermissionStoreHook().wholeMenus
  );
  if (!parenetRoute?.children) return;
  subMenuData.value = parenetRoute?.children;
}

getSubMenuData(route.path);

onBeforeMount(() => {
  emitter.on("logoChange", key => {
    showLogo.value = key;
  });
});

watch(
  () => [route.path, usePermissionStoreHook().wholeMenus],
  () => {
    getSubMenuData(route.path);
    menuSelect(route.path, routers);
  }
);
</script>

<template>
  <div
    v-loading="menuData.length === 0"
    :class="['sidebar-container', showLogo ? 'has-logo' : '']"
  >
    <el-scrollbar class="pc" wrap-class="scrollbar-wrapper">
      <el-menu
        :collapse="isCollapse"
        :collapse-transition="false"
        :default-active="route.path"
        class="outer-most select-none"
        mode="vertical"
        router
        unique-opened
        @select="indexPath => menuSelect(indexPath, routers)"
      >
        <sidebar-item
          v-for="routes in menuData"
          :key="routes.path"
          :base-path="routes.path"
          :item="routes"
          class="outer-most select-none"
        />
      </el-menu>
    </el-scrollbar>
    <leftCollapse
      :is-active="pureApp.sidebar.opened"
      @toggleClick="toggleSideBar"
    />
  </div>
</template>

<style scoped>
:deep(.el-loading-mask) {
  opacity: 0.45;
}
</style>
