<template>
  <div class="widget-wrapper drag-handler">
    <div
      v-if="designer.design"
      v-show="!widget.options.hidden"
      :class="[selected ? 'selected' : '']"
      class="static-content-item"
      @click.stop="selectWidget(widget)"
    >
      <slot></slot>
      <div
        v-if="!!designer.design"
        v-show="designer.selectedId === widget.id"
        class="widget-action"
      >
        <span class="widget-action-copy widget-action-item" title="复制">
          <copyIcon @click.stop="cloneWidget"/>
        </span>
        <span class="widget-action-delete widget-action-item" title="删除">
          <removeIcon @click.stop="removeWidget"/>
        </span>
      </div>
    </div>
    <slot v-else></slot>
  </div>
</template>

<script>
import copyIcon from "@/assets/svg/copy.svg?component";
import removeIcon from "@/assets/svg/remove.svg?component";
export default {
  name: "StaticWrapper",
  components:{copyIcon,removeIcon},
  props: {
    widget: Object,
    designer: Object,
    parentWidget: Object,
    indexOfParentList: Number
  },
  computed: {
    selected() {
      return this.widget.id === this.designer.selectedId;
    }
  },
  methods: {
    selectWidget(widget) {
      this.designer.setSelected(widget);
    },
    cloneWidget() {
      if (!!this.parentWidget) {
        const widgetList = this.parentWidget.widgetList;
        let newCon = this.designer.cloneWidget(this.widget);
        widgetList.splice(this.indexOfParentList + 1, 0, newCon);
        this.selectWidget(newCon);
      }
    },
    removeWidget() {
      if (!!this.parentWidget) {
        const widgetList = this.parentWidget.widgetList;
        let nextSelected = null;
        if (this.indexOfParentList + 1 < widgetList.length) {
          nextSelected = widgetList[this.indexOfParentList + 1];
        } else if (this.indexOfParentList > 0) {
          nextSelected = widgetList[this.indexOfParentList - 1];
        } else if (this.parentWidget && this.parentWidget != this.designer) {
          nextSelected = this.parentWidget;
        }
        this.$nextTick(() => {
          widgetList.splice(this.indexOfParentList, 1);
          this.selectWidget(nextSelected);
        });
      }
    }
  }
};
</script>

<style lang="scss" scoped>
//静态框
.widget-wrapper {
  position: relative;

  .widget-action {
    position: absolute;
    bottom: 0;
    right: -2px;
    display: flex;
    justify-content: flex-end;
    & > .widget-action-item{
      margin-right: 10px;
      text-align: center;
      border-radius: 50%;
      padding: 5px;
      border: 1px solid;
      cursor: pointer;
      z-index: 1;
    }

    & > .widget-action-copy {
      right: 56px;
      border-color: #1296db;
      color: #1296db;
      background: #fff;

      &:hover {
        //background: #409eff;
        fill: #fff;
      }
    }

    & > .widget-action-delete {
      right: 24px;
      border-color: #d81e06;
      color: #d81e06;
      background: #fff;

      &:hover {
        //background: #d81e06;
        fill: #fff;
      }
    }
  }
}

.static-content-item {
  min-height: 20px;
  align-items: center; /* 垂直居中 */
  padding: 10px;
}

.static-content-item.selected {
  background-color: #eaecfd;
}

</style>
