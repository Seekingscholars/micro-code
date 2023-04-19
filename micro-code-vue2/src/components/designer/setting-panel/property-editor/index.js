const requireComponent = require.context('./', true, /\w+\.vue$/) // 遍历当前目录和子目录

const comps = {}

requireComponent.keys().map(fileName => {
  const comp = requireComponent(fileName).default
  comps[comp.name] = comp
})

export default comps
