<template>
    <DesignWidget :designer="this.formJson"></DesignWidget>
</template>
<script>
import DesignWidget from '../designer/form-widget/DesignWidget'

export default {
  name: 'VFormRender',
  componentName: 'VFormRender',
  components: {
    DesignWidget
  },
  props: {
    formJson: {
      type: Object
    }
  },
  provide(){
    return{
      $model:this.dataModel
    }
  },
  data(){
    return{
      dataModel:{}
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
