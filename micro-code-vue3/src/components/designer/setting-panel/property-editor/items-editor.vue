<template>
  <div>

    <el-table :data="optionModel.items" border row-key="value">
      <el-table-column label="排序" width="50px">
        <i class="drag-handler el-icon-sort" style="cursor: move"></i>
      </el-table-column>
      <el-table-column label="属性" prop="value">
        <template #default="scope">
          <el-input v-model="scope.row.value"></el-input>
        </template>
      </el-table-column>
      <el-table-column label="标签" prop="label">
        <template #default="scope">
          <el-input v-model="scope.row.label"></el-input>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="60px">
        <template #default="scope">
          <div class="operate">
            <i class="el-icon-delete" style="color: red;cursor: pointer" title="删除" @click="handleDelete(scope.row)"
            ></i>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <el-button link @click="addNewItem">添加一项+</el-button>

  </div>
</template>

<script>


export default {
  name: 'items-editor',
  props: {
    optionModel: Object
  },
  data() {
    return {
      modelForm: {
        value: '',
        label: '',
        disabled: false
      }
    }
  },
  methods: {
    /** 删除操作 */
    handleDelete(row) {
      this.$modal.confirm('是否确认删除？').then(() => {
        for (let i = 0; i < this.optionModel.items.length; i++) {
          if (this.optionModel.items[i].value === row.value) {
            this.optionModel.items.splice(i, 1)

            break
          }
        }
      })
    },
    addNewItem() {
      this.optionModel.items.push({
        value: '',
        label: '',
        disabled: false
      })

    }
  }
}
</script>

<style scoped>
.operate {
  display: flex;
  justify-content: space-between;
}
</style>
