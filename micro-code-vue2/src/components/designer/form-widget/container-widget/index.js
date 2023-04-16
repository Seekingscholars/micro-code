import Vue from 'vue'

const requireComponent = require.context('./', false, /\w+\.vue$/)

/* 全局注册！！ */
requireComponent.keys().map(fileName => {
  const comp = requireComponent(fileName).default
  Vue.component(comp.name, comp)
})
