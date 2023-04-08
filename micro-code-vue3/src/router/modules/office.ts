const Layout = () => import("@/layout/index.vue");

export default {
  path: "/office",
  name: "office",
  component: Layout,
  meta: {
    icon: "homeFilled",
    title: "我的待办",
    rank: 2
  },
  children: [
    {
      path: "/office",
      name: "office",
      component: () => import("@/views/welcome/index.vue"),
      meta: {
        title: "我的待办"
      }
    }
  ]
} as RouteConfigsTable;
