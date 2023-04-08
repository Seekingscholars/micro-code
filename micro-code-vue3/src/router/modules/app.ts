const Layout = () => import("@/layout/index.vue");

export default {
  path: "/",
  name: "App",
  component: Layout,
  redirect: "/app",
  meta: {
    icon: "homeFilled",
    title: "我的应用",
    rank: 1
  },
  children: [
    {
      path: "/app",
      name: "我的应用",
      component: () => import("@/views/welcome/index.vue"),
      meta: {
        title: "我的应用"
      }
    }
  ]
} as RouteConfigsTable;
