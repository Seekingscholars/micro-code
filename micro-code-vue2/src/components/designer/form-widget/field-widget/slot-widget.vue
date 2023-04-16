<template>
  <div :class="[!!designState ? 'slot-wrapper-design' : 'slot-wrapper-render']">
    <!-- -->
    <slot :name="field.options.name" v-bind:formModel="formModel"></slot>
    <!-- -->
    <!-- slot :name="field.options.name"></slot -->
    <div v-if="!!designState" class="slot-title">{{ field.options.label }}</div>
  </div>
</template>

<script>
import emitter from '@/utils/emitter'

import fieldMixin from './fieldMixin'

export default {
  name: 'slot-widget',
  componentName: 'FieldWidget',
  mixins: [emitter, fieldMixin],
  props: {
    field: Object,
    parentWidget: Object,
    parentList: Array,
    indexOfParentList: Number,
    designer: Object

  },

  created() {

  }

}
</script>

<style lang="scss" scoped>
.slot-wrapper-design {
  width: 100%;
  min-height: 26px;
  background: linear-gradient(45deg, #ccc 25%, #eee 0, #eee 50%, #ccc 0, #ccc 75%, #eee 0);
  background-size: 20px 20px;
  text-align: center;

  .slot-title {
    font-size: 13px;
  }
}

.slot-wrapper-render {

}

</style>
