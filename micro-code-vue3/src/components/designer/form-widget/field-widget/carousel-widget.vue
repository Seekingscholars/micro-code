<template>
  <el-carousel :interval="4000" height="200px" type="card">
    <el-carousel-item v-for="item in 6" :key="item">
      <h3 class="medium">{{ item }}</h3>
    </el-carousel-item>
  </el-carousel>
</template>
<script>
import RenderWidget from '../RenderWidget.vue'

export default {
  name: 'carousel-widget',
  componentName: 'ContainerWidget',
  components: {
    RenderWidget
  },
  props: {
    field: Object,
    designer: Object
  },
  data() {
    return {
      activeNames: ['1']
      //
    }
  },
  computed: {},
  watch: {},
  created() {
  },
  mounted() {
  }

}
</script>

<style lang="scss" scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>
