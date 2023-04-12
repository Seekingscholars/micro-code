import Sortable from "sortablejs";
const dragOptions = [
  {
    selector: "thead tr", // add drag support for column
    option: {
      // sortablejs's option
      animation: 150,
      onEnd: (evt) => {
        // let oldCol = {};
        // Object.assign(oldCol, columns.value[evt.oldIndex]);
        // columns.value.splice(evt.oldIndex, 1); // 因为新增了数据，所以要移除原来的列的index要在原来的基础上
        // setTimeout(() => {
        //   columns.value.splice(evt.newIndex, 0, oldCol); // 把原来的列数据添加到新的位置，然后再从原位置移除它，触发table的重绘
        // }, 30);
        // console.log(evt.oldIndex, evt.newIndex);
      }
    }
  },
  {
    selector: "tbody", // add drag support for row
    option: {
      // sortablejs's option
      animation: 150,
      onEnd: (evt) => {
        // console.log(evt.oldIndex, evt.newIndex);
      }
    }
  }
];
export const draggable = {
  mounted(el, binding) {
    const options = binding.value || dragOptions;
    for (let oi = 0; oi < options.length; oi++) {
      const o = options[oi];
      new Sortable(el.querySelector(o.selector), o.option);
    }
  }
};
