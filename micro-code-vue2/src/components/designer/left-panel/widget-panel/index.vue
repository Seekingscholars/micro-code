<template>
  <el-collapse v-model="activeNames" class="widget-collapse">
    <el-collapse-item v-for="(item,index) in widgetList" :key="item.type+index" :name="item.type" :title="item.name">
      <template slot="title">
        <div class="title">{{ item.name }}</div>
      </template>
      <draggable
        :clone="cloneWidget"
        :group="{name: 'dragGroup', pull: 'clone', put: false}"
        :list="item.children"
        :move="checkMove"
        :sort="false"
        ghost-class="ghost"
        tag="ul"
      >
        <li v-for="(ctn,index) in item.children" :key="ctn.type+index" :title="ctn.name" class="widget-item">
          <span>{{ ctn.name }}</span>
        </li>
      </draggable>
    </el-collapse-item>
  </el-collapse>
</template>

<script>
import widgetJson from './widget.json'
import Draggable from 'vuedraggable'
import { deepClone, generateId } from '@/utils/util'

export default {
  name: 'WidgetPanel',
  components: {
    Draggable
  },
  props: {
    designer: Object
  },
  data() {
    return {
      activeNames: [],
      widgetList: []
    }
  },
  mounted() {
    this.loadWidgets()
  },
  methods: {
    loadWidgets() {
      this.widgetList = widgetJson
      this.activeNames = widgetJson.map(widget => widget.type)
    },

    cloneWidget(widget) {
      const newWidget = deepClone(widget)
      const tempId = generateId()
      newWidget.id = newWidget.type.replace(/-/g, '') + tempId
      newWidget.options.name = newWidget.id
      // newWidget.options.label = newWidget.options.label || newWidget.type.toLowerCase()
      return newWidget
    },

    checkMove(evt) {
      return true
    }
  }

}
</script>

<style lang="scss" scoped>
.title {
  margin-left: 8px;
  font-weight: bold;
}

.widget-item {
  display: inline-block;
  height: 28px;
  line-height: 28px;
  width: 115px;
  float: left;
  margin: 2px 6px 6px 0;
  cursor: move;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
  background: #f1f2f3;
}

.widget-item:hover {
  background: #eaecfd;
}
::v-deep ul {
  padding-left: 10px; /* 重置IE11默认样式 */
  margin: 0; /* 重置IE11默认样式 */
  margin-block-start: 0;
  margin-block-end: 0.25em;
  padding-inline-start: 10px;

  &:after {
    content: "";
    display: block;
    clear: both;
  }

  .widget-item {
    display: inline-block;
    height: 28px;
    line-height: 28px;
    width: 115px;
    float: left;
    margin: 2px 6px 6px 0;
    cursor: move;
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
    background: #f1f2f3;
  }

  .widget-item:hover {
    background: #EBEEF5;
    outline: 1px solid #409EFF;
  }
}

</style>
