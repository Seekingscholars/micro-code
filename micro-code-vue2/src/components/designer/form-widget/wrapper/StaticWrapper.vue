<template>
  <div class="widget-wrapper drag-handler">
    <div
      v-if="designer.design"
      v-show="!widget.options.hidden"
      :class="[selected ? 'selected' : '']"
      class="static-content-item"
      @click.stop="selectWidget(widget)"
    >
      <slot />
      <div
        v-if="!!designer.design"
        v-show="designer.selectedId === widget.id"
        class="widget-action"
      >
        <span
          class="widget-action-copy"
          title="复制"
        >
          <i
            class="el-icon-copy-document"
            @click.stop="cloneWidget"
          />
        </span>
        <span
          class="widget-action-delete"
          title="删除"
        >
          <i
            class="el-icon-delete"
            @click.stop="removeWidget"
          />
        </span>
      </div>
    </div>
    <slot v-else />
  </div>
</template>

<script>
export default {
  name: 'StaticWrapper',
  props: {
    widget: Object,
    designer: Object,
    parentWidget: Object,
    indexOfParentList: Number
  },
  computed: {
    selected() {
      return this.widget.id === this.designer.selectedId
    }
  },
  methods: {
    selectWidget(widget) {
      this.designer.setSelected(widget)
    },
    cloneWidget() {
      if (this.parentWidget) {
        const widgetList = this.parentWidget.widgetList
        const newCon = this.designer.cloneWidget(this.widget)
        debugger
        if (newCon.options) {
          newCon.options.name = newCon.id
        }
        widgetList.splice(this.indexOfParentList + 1, 0, newCon)
        this.selectWidget(newCon)
      }
    },
    removeWidget() {
      if (this.parentWidget) {
        const widgetList = this.parentWidget.widgetList
        let nextSelected = null
        if (this.indexOfParentList + 1 < widgetList.length) {
          nextSelected = widgetList[this.indexOfParentList + 1]
        } else if (this.indexOfParentList > 0) {
          nextSelected = widgetList[this.indexOfParentList - 1]
        } else if (this.parentWidget && this.parentWidget != this.designer) {
          nextSelected = this.parentWidget
        }
        this.$nextTick(() => {
          widgetList.splice(this.indexOfParentList, 1)
          this.selectWidget(nextSelected)
        })
      }
    }
  }
}
</script>

<style lang="scss" scoped>
//静态框
.widget-wrapper {
  position: relative;

  .widget-action {
    position: absolute;
    //bottom: -24px;
    bottom: 0;
    right: -2px;
    display: flex;
    justify-content: space-around;
    width: 60px;

    & > .widget-action-copy, & > .widget-action-delete {
      text-align: center;
      border-radius: 50%;
      font-size: 12px;
      padding: 5px;
      border: 1px solid;
      cursor: pointer;
      z-index: 1;
    }

    & > .widget-action-copy {
      right: 56px;
      border-color: #409EFF;
      color: #409EFF;
      background: #fff;

      &:hover {
        background: #409EFF;
        color: #fff;
      }
    }

    & > .widget-action-delete {
      right: 24px;
      border-color: #F56C6C;
      color: #F56C6C;
      background: #fff;

      &:hover {
        background: #F56C6C;
        color: #fff;
      }
    }
  }
}
</style>
