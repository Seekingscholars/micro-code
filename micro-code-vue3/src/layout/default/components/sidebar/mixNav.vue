<script lang="ts" setup>
import Notice from "../notice/index.vue";
import {useNav} from "@/layout/default/hooks/useNav";
import {nextTick, onMounted, ref, toRaw, watch} from "vue";
import {useRenderIcon} from "@/components/ReIcon/src/hooks";
import {findRouteByPath, getParentPaths} from "@/router/utils";
import {usePermissionStoreHook} from "@/store/modules/permission";
import LogoutCircleRLine from "@iconify-icons/ri/logout-circle-r-line";
import Setting from "@iconify-icons/ri/settings-3-line";

const menuRef = ref();
const defaultActive = ref(null);

const {
  route,
  device,
  routers,
  logout,
  onPanel,
  menuSelect,
  resolvePath,
  username,
  avatarsStyle
} = useNav();

function getDefaultActive(routePath) {
  const wholeMenus = usePermissionStoreHook().wholeMenus;
  /** 当前路由的父级路径 */
  const parentRoutes = getParentPaths(routePath, wholeMenus)[0];
  defaultActive.value = findRouteByPath(
    parentRoutes,
    wholeMenus
  )?.children[0]?.path;
}

onMounted(() => {
  getDefaultActive(route.path);
});

nextTick(() => {
  menuRef.value?.handleResize();
});

watch(
  () => [route.path, usePermissionStoreHook().wholeMenus],
  () => {
    getDefaultActive(route.path);
  }
);
</script>

<template>
  <div
    v-if="device !== 'mobile'"
    v-loading="usePermissionStoreHook().wholeMenus.length === 0"
    class="horizontal-header"
  >
    <el-menu
      ref="menuRef"
      :default-active="defaultActive"
      class="horizontal-header-menu"
      mode="horizontal"
      router
      @select="indexPath => menuSelect(indexPath, routers)"
    >
      <el-menu-item
        v-for="route in usePermissionStoreHook().wholeMenus"
        :key="route.path"
        :index="resolvePath(route) || route.redirect"
      >
        <template #title>
          <div
            v-if="toRaw(route.meta.icon)"
            :class="['sub-menu-icon', route.meta.icon]"
          >
            <component
              :is="useRenderIcon(route.meta && toRaw(route.meta.icon))"
            />
          </div>
          <span class="select-none">{{ route.meta.title }}</span>
          <FontIcon
            v-if="route.meta.extraIcon"
            :icon="route.meta.extraIcon.name"
            :svg="route.meta.extraIcon.svg ? true : false"
            height="30px"
            style="position: absolute; right: 10px"
            width="30px"
          />
        </template>
      </el-menu-item>
    </el-menu>
    <div class="horizontal-header-right">
      <!-- 通知 -->
      <Notice id="header-notice"/>
      <!-- 退出登录 -->
      <el-dropdown trigger="click">
        <span class="el-dropdown-link navbar-bg-hover select-none">
          <img
            :style="avatarsStyle"
            src="https://avatars.githubusercontent.com/u/44761321?v=4"
          />
          <p v-if="username" class="dark:text-white">{{ username }}</p>
        </span>
        <template #dropdown>
          <el-dropdown-menu class="logout">
            <el-dropdown-item @click="logout">
              <IconifyIconOffline
                :icon="LogoutCircleRLine"
                style="margin: 5px"
              />
              退出系统
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
      <span
        class="set-icon navbar-bg-hover"
        title="打开项目配置"
        @click="onPanel"
      >
        <IconifyIconOffline :icon="Setting"/>
      </span>
    </div>
  </div>
</template>

<style lang="scss" scoped>
:deep(.el-loading-mask) {
  opacity: 0.45;
}

.logout {
  max-width: 120px;

  ::v-deep(.el-dropdown-menu__item) {
    min-width: 100%;
    display: inline-flex;
    flex-wrap: wrap;
  }
}
</style>
