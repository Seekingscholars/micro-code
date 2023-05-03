<template>
  <div>
      <el-table v-sort="dragOption" :data="optionModel.items" border row-key="value">
        <el-table-column label="属性" prop="value">
          <template slot-scope="scope">
            <el-input v-model="scope.row.value"></el-input>
          </template>
        </el-table-column>
        <el-table-column label="标签" prop="label">
          <template slot-scope="scope">
            <el-input v-model="scope.row.label"></el-input>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="80px">
          <template slot-scope="scope">
            <div class="toolbar">
              <div
                class="drag-handler toolbar-button"
                style="cursor: move"
              >
                <i
                  class="el-icon-sort"
                  title="排序"
                />
              </div>
              <div
                class="toolbar-button"
                @click="handleDelete(scope.row)"
              >
                <i class="el-icon-delete" style="color: red;cursor: pointer" title="删除"></i>
              </div>
            </div>
          </template>
        </el-table-column>
      </el-table>
      <el-button type="text" @click="addNewItem">添加一项+</el-button>
  </div>
</template>

<script>
import { option } from '@/utils/dragOption'
export default {
  name: 'items-editor',
  props: {
    optionModel: Object
  },
  components: {},
  computed: {
    dragOption() {
      return option(this.optionModel.items)
    }
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
.toolbar {
  display: flex;
}
.toolbar-button {
  margin-right: 20px;
}
</style>
