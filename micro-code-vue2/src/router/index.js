import Vue from 'vue'
import Router from 'vue-router'
/* Layout */
import Layout from '@/layout'

Vue.use(Router)

export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '工作台', icon: 'home-filled' }
    }]
  },
  {
    path: '/datasource',
    component: Layout,
    children: [{
      path: '/datasource',
      name: 'datasource',
      component: () => import('@/views/datasource/index'),
      meta: { title: '数据源', icon: 'coin' }
    }]
  },
  {
    path: '/app/overview/:appId',
    name: 'app',
    component: () => import('@/views/app/index'),
    meta: {
      icon: '',
      title: '我的应用'
    },
    hidden: true
  },
  {
    path: '/app/design/:formId',
    name: 'app-design',
    component: () => import('@/views/app/design/index'),
    meta: {
      icon: '',
      title: '表单设计'
    },
    hidden: true
  },
  {
    path: '/app/preview/:formId',
    name: 'app-preview',
    component: () => import('@/views/app/preview/index'),
    meta: {
      icon: '',
      title: '表单预览'
    },
    hidden: true
  },
  {
    path: '/app/open/:formId',
    name: 'app-open',
    component: () => import('@/views/app/open/index'),
    meta: {
      icon: '',
      title: '表单应用'
    },
    hidden: true
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
