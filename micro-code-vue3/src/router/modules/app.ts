const Layout = () => import("@/layout/index.vue");

export default {
  path: "/app",
  name: "App",
  component: Layout,
  redirect: "/app/design",
  meta: {
    icon: "homeFilled",
    title: "我的应用",
    rank: 1
  },
  children: [
    {
      path: "/app/design",
      name: "app-design",
      component: () => import("@/views/app/design/index.vue"),
      meta: {
        title: "表单设计"
      }
    },
    {
      path: "/app/preview",
      name: "app-preview",
      component: () => import("@/views/app/preview/index.vue"),
      meta: {
        title: "表单预览"
      }
    }
  ]
} as RouteConfigsTable;
