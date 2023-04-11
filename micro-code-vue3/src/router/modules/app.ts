export default {
  path: "/app",
  name: "app",
  redirect: "/app/design",
  meta: {
    icon: "homeFilled",
    title: "表单设计",
    rank: 1
  },
  children: [
    {
      path: "/app/design",
      name: "app-design",
      component: () => import("@/views/app/design/index.vue"),
      meta: {
        icon: "homeFilled",
        title: "表单设计"
      }
    },
    {
      path: "/app/preview",
      name: "app-preview",
      component: () => import("@/views/app/preview/index.vue"),
      meta: {
        icon: "homeFilled",
        title: "表单设计"
      }
    }
  ]
} as RouteConfigsTable;
