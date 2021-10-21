module.exports = {
  devServer: {
    port: 3000,
    proxy: {
      "/": {
        target: "http://localhost:8080",
        changeOrigin: true,
      },
    },
  },
};
