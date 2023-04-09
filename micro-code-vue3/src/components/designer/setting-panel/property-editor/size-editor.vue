<template>
  <el-form-item label="组件大小">
    <el-select v-model="optionModel.size" style="width: 100%">
      <el-option
        v-for="item in widgetSizes"
        :key="item.value"
        :label="item.label"
        :value="item.value"
      />
    </el-select>
  </el-form-item>
</template>

<script>
export default {
  name: "SizeEditor",
  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  },
  data() {
    return {
      widgetSizes: [
        { label: "default", value: "" },
        { label: "large", value: "large" },
        { label: "small", value: "small" }
      ]
    };
  }
};
</script>

<style scoped></style>
