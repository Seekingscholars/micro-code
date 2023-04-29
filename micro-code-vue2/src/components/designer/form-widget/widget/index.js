import Vue from 'vue'

const install = () => {
  const requireComponent = require.context('./', false, /\w+\.vue$/)
  requireComponent.keys().map(fileName => {
    const comp = requireComponent(fileName).default
    Vue.component(comp.name, comp)
  })
}
install()
