export const option = function(
  list,
  handle = '.drag-handler',
  selector = 'tbody'
) {
  return {
    selector: selector,
    option: {
      animation: 150,
      handle: handle,
      onEnd: evt => {
        const oldCol = {}
        Object.assign(oldCol, list[evt.oldIndex])
        const oldIndex = evt.oldIndex
        const newIndex = evt.newIndex
        list.splice(oldIndex, 1)
        list.splice(newIndex, 0, oldCol)
      }
    }
  }
}
