<template>
  <EventEditor :designer="designer" event-name="change" event-param="value"></EventEditor>
</template>

<script>
import EventEditor from './event-editor'
export default {
  name: 'change-editor',
  components: { EventEditor },
  props: {
    designer: Object
  }
}
</script>

<style scoped>

</style>
