<template>
  <el-collapse v-model="activeNames" class="widget-collapse">
    <el-collapse-item
      v-for="(item, index) in widgetList"
      :key="item.type + index"
      :name="item.type"
      :title="item.name"
    >
      <template #title>
        <div class="title">{{ item.name }}</div>
      </template>
      <draggable
        :clone="cloneWidget"
        :group="{ name: 'dragGroup', pull: 'clone', put: false }"
        :list="item.children"
        :move="checkMove"
        :sort="false"
        ghost-class="ghost"
        item-key="type"
        tag="ul"
      >
        <template #item="{ element: ctn }">
          <li :key="ctn.type + index" :title="ctn.name" class="widget-item">
            <span>{{ ctn.name }}</span>
          </li>
        </template>
      </draggable>
    </el-collapse-item>
  </el-collapse>
</template>

<script>
import Draggable from "@/components/draggable";
import {deepClone, generateId} from "@/utils/util";
import widgetJson from "./widget.json";

export default {
  name: "WidgetPanel",
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
    };
  },
  mounted() {
    this.loadWidgets();
  },
  methods: {
    loadWidgets() {
      this.widgetList = widgetJson;
      this.activeNames = widgetJson.map(widget => widget.type);
    },

    cloneWidget(widget) {
      let newWidget = deepClone(widget);
      let tempId = generateId();
      newWidget.id = newWidget.type.replace(/-/g, "") + tempId;
      newWidget.options.name = newWidget.id;
      return newWidget;
    },

    checkMove(evt) {
      return true;
    }
  }
};
</script>

<style scoped>
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
</style>
