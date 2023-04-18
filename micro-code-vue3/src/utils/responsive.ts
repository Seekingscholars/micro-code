// 响应式storage
import {App} from "vue";
import Storage from "responsive-storage";

const nameSpace = "responsive-";

export const injectResponsiveStorage = (app: App) => {
  const configObj = Object.assign({
    // layout模式以及主题
    layout: {
      layout: "vertical",
      theme: "default",
      darkMode: false,
      sidebarStatus: true,
      epThemeColor: "#409EFF"
    },
    configure: {
      grey: false,
      weak: false,
      hideTabs: false,
      showLogo: true,
      showModel: "smart",
      multiTagsCache: false
    }
  });

  app.use(Storage, {nameSpace, memory: configObj});
};
