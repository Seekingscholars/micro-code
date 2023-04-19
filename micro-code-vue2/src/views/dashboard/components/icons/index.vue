<template>
  <div class="icons-container">
    <div class="color-list">
      <div
        v-for="item in colors"
        :key="item"
        :style="{backgroundColor:item}"
        class="color-item"
        @click="()=>handleColorClick(item)"
      >
        <svg-icon v-show="model.color===item" class-name="colorIcon" icon-class="check" />
      </div>
    </div>
    <div class="grid">
      <div
        v-for="item of pageSvgIcons"
        :key="item"
        :class="{'icon-item':true,'icon-item-selected':model.imageName===item}"
        @click="()=>handleItemClick(item)"
      >
        <svg-icon :icon-class="item" class-name="disabled" />
      </div>
    </div>
    <div class="footer">
      <el-pagination
        :current-page.sync="currentPage"
        :page-size="pageSize"
        :total="total"
        layout="prev, pager, next"
        small
      />
    </div>
  </div>
</template>

<script>
import svgIcons from './svg-icons'

export default {
  name: 'Icons',
  props: {
    model: Object
  },
  data() {
    return {
      svgIcons,
      colors: ['#1E90FF', '#FF8C00', '#BDB76B', '#FF6347',
        '#6495ED', '#8A2BE2', '#008B8B', '#778899'],
      currentPage: 1,
      pageSize: 56
    }
  },
  computed: {
    total() {
      return svgIcons.length
    },
    pageSvgIcons() {
      return svgIcons.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    }
  },
  methods: {
    handleColorClick(item) {
      this.model.color = item
    },
    handleItemClick(item) {
      this.model.imageName = item
    }
  }
}
</script>

<style lang="scss" scoped>
.icons-container {
  padding: 10px;
  width: 340px;
}

.color-list {
  width: 100%;
  display: flex;
}

.color-item {
  width: 30px;
  height: 30px;
  margin-right: 10px;
  border-radius: 50%;
  text-align: center;
  line-height: 30px;
  cursor: pointer;
}

.colorIcon {
  color: #ffffff;
}

.grid {
  display: flex;
  flex-wrap: wrap;
}

.icon-item {
  margin-right: 10px;
  margin-top: 10px;
  font-size: 20px;
  width: 30px;
  height: 30px;
  color: #24292e;
  cursor: pointer;
  border: 1px solid #f1f2f3;
  text-align: center;
}

.icon-item-selected {
  border: 1px solid #0db3a6;
}

.footer {
  display: flex;
  justify-content: flex-end;
}
</style>
