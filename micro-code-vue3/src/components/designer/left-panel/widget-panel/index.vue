<template>
  <el-collapse v-model="activeNames" class="widget-collapse">
    <el-collapse-item
      v-for="(item, index) in widgetList"
      :key="item.type + index"
      :name="item.type"
      :title="item.name"
    >
      <draggable
        item-key="type"
        :clone="cloneWidget"
        :group="{ name: 'dragGroup', pull: 'clone', put: false }"
        :list="item.children"
        :move="checkMove"
        :sort="false"
        ghost-class="ghost"
        tag="ul"
      >
        <template #item="{ element: ctn }">
          <li :key="ctn.type + index" :title="ctn.name" class="widget-item">
            <span
              ><svg-icon :icon-class="ctn.icon" class-name="color-svg-icon" />{{
                ctn.name
              }}</span
            >
          </li>
        </template>
      </draggable>
    </el-collapse-item>
  </el-collapse>
</template>

<script>
import widgetJson from "./widget.json";
import Draggable from "vuedraggable";
import { deepClone, generateId } from "@/utils/util";

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
      // newWidget.options.label = newWidget.options.label || newWidget.type.toLowerCase()
      return newWidget;
    },

    checkMove(evt) {
      return true;
    }
  }
};
</script>

<style lang="scss" scoped>
.no-bottom-margin ::v-deep .el-tabs__header {
  margin-bottom: 0;
}

.indent-left-margin {
  ::v-deep .el-tabs__nav {
    margin-left: 20px;
  }
}

.el-collapse-item ::v-deep ul > li {
  list-style: none;
}

.widget-collapse {
  border-top-width: 0;

  ::v-deep .el-collapse-item__header {
    margin-left: 8px;
    font-style: italic;
    font-weight: bold;
  }

  ::v-deep .el-collapse-item__content {
    padding-bottom: 6px;

    ul {
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
        background: #ebeef5;
        outline: 1px solid #409eff;
      }
    }
  }
}

.el-card.ft-card {
  border: 1px solid #8896b3;
}

.ft-card {
  margin-bottom: 10px;

  .bottom {
    margin-top: 10px;
    line-height: 12px;
  }

  .ft-title {
    font-size: 13px;
    font-weight: bold;
  }

  .right-button {
    padding: 0;
    float: right;
  }

  .clear-fix:before,
  .clear-fix:after {
    display: table;
    content: "";
  }

  .clear-fix:after {
    clear: both;
  }
}
</style>
