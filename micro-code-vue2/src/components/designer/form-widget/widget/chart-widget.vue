<template>
  <div ref="chart" :style="style" />
</template>

<script>
import fieldMixin from './fieldMixin'
import * as echarts from 'echarts'
import { wrapWith }from '../../designer'
export default {
  name: 'chart-widget',
  componentName: 'FieldWidget',
  mixins: [fieldMixin],
  computed: {
    style() {
      return {
        height: this.field.options.height + 'px'
      }
    },
    dataList() {
      const data = this.field.options.data
      let dataList
      if (data) {
        const customFn = new Function(wrapWith('return ' + data))
        dataList = customFn.call(this)
      }
      dataList = dataList || []
      const option = {
        tooltip: {
          trigger: 'axis'
        }
      }
      if ('chart-title' in this.field) {
        option.title = {
          show: !this.field['chart-title'].hidden,
          text: this.field['chart-title'].title,
          left: this.field['chart-title'].left,
          top: this.field['chart-title'].top,
          right: this.field['chart-title'].right,
          bottom: this.field['chart-title'].bottom,
          textStyle: {
            color: this.field['chart-title'].color,
            fontSize: this.field['chart-title'].fontSize
          }
        }
      }
      if ('chart-legend' in this.field) {
        option.legend = {
          show: !this.field['chart-legend'].hidden,
          orient: this.field['chart-legend'].orient,
          icon: this.field['chart-legend'].icon,
          left: this.field['chart-legend'].left,
          top: this.field['chart-legend'].top
        }
        if (this.field.options.items) {
          option.legend.data = this.field.options.items.map(item => {
            return {
              name: item.label
            }
          })
        }
      }
      if ('chart-xAxis' in this.field) {
        option.xAxis = {
          show: !this.field['chart-xAxis'].hidden,
          type: 'category',
          axisLabel: {
            rotate: this.field['chart-xAxis'].rotate,
            color: this.field['chart-xAxis'].color
          },
          data: dataList.map(data => data[this.field.options.dimension])
        }
      }
      if ('chart-yAxis' in this.field) {
        option.yAxis = {
          show: !this.field['chart-yAxis'].hidden,
          type: 'value',
          axisLabel: {
            rotate: this.field['chart-yAxis'].rotate,
            color: this.field['chart-yAxis'].color
          }
        }
      }
      if ('items' in this.field.options) {
        option.series = this.field.options.items.map(item => {
          return {
            name: item.label,
            data: dataList.map(data => data[item.value]),
            type: item.type,
            itemStyle: {
              color: item.color
            },
            label: {
              show: !this.field['chart-label'].hidden,
              position: this.field['chart-label'].position,
              rotate: this.field['chart-label'].rotate
            }
          }
        })
      }
      if (this.field.options.flip) {
        const data = option.xAxis.data
        const type = option.xAxis.type
        delete option.xAxis.data
        option.xAxis.type = option.yAxis.type
        option.yAxis.type = type
        option.yAxis.data = data
      }
      if ('type' in this.field.options) {
        option.series = [
          {
            data: dataList,
            type: this.field.options.type
          }
        ]
      }
      return {
        dataList, option
      }
    }
  },
  watch: {
    dataList: {
      handler(model) {
        this.$nextTick(()=> this.draw(model.dataList, model.option))
      },
      immediate: true
    }
  },
  methods: {
    draw(dataList, option) {
      if (!dataList) {
        return
      }
      const chart = echarts.init(this.$refs.chart)
      option && chart.setOption(option)
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
