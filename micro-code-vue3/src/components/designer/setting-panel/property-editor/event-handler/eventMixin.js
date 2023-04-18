export default {
  created() {
  },
  methods: {
    editEventHandler(eventName, eventParams) {
      this.dispatch("SettingPanel", "editEventHandler", [
        eventName,
        [...eventParams]
      ]);
    }
  }
};
