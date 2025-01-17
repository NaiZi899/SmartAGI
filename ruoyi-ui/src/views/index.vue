<template>
  <div class="app-container">
    <div class="centered-title">
      <h1 :style="titleStyle" contenteditable="true">第八组智慧助农</h1>
    </div>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      titleStyle: {
        color: '#8CC152', // 葱绿色
        fontFamily: 'Arial, sans-serif', // 默认字体样式
        fontSize: '50px', // 字体大小改为50px
        fontWeight: 'bold' // 默认字体粗细
      }
    };
  }
};
</script>

<style scoped lang="scss">
.app-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  margin: 0;
  padding: 0;
}

.centered-title {
  text-align: center;
}

h1 {
  margin: 0;
  padding: 0;
}
</style>
