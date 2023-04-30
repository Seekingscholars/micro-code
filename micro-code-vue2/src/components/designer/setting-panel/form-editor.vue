<script>
import PropertyEditors from './property-editor'
import collapseJson from './collapse.json'
export default {
  name: 'FormEditor',
  props: {
    designer: Object
  },
  data() {
    return {
      collapseList: [],
      widgetActiveCollapseNames: []
    }
  },
  created() {
    if (this.designer.collapseJson) {
      this.collapseList = [...collapseJson, ...this.designer.collapseJson]
    } else {
      this.collapseList = collapseJson
    }
    this.widgetActiveCollapseNames = this.collapseList.map(collapse => collapse.type)
  },
  methods: {
    getPropEditor(propName) {
      const editorName = `${propName}-editor`
      const ownPropEditorName = `${this.designer.selectedWidget.type}-${editorName}`
      return PropertyEditors[ownPropEditorName] || this.$root.$options.components[ownPropEditorName] || PropertyEditors[editorName] || this.$root.$options.components[editorName]
    }
  },
  render() {
    const children = []
    for (const item of this.collapseList) {
      const prop = this.designer.selectedWidget[item.type]
      if (prop) {
        const Components = []
        for (const propName of Object.keys(this.designer.selectedWidget[item.type])) {
          const Component = this.getPropEditor(propName)
          if (Component != null) {
            Components.push(<Component designer={this.designer}
              option-model={this.designer.selectedWidget[item.type]}
              selected-widget={this.designer.selectedWidget} />)
          }
        }

        const child = <el-collapse-item
          name={item.type}
          title={item.name}
        >
          {Components}
        </el-collapse-item>
        children.push(child)
      }
    }
    return <el-collapse v-model={this.widgetActiveCollapseNames} class='setting-collapse'>
      {children}
    </el-collapse>
  }
}
</script>
