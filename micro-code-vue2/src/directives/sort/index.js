import Sortable from 'sortablejs'
export const sort = {
  bind(el, binding) {
    const option = binding.value
    new Sortable(el.querySelector(option.selector), option.option)
  }
}
