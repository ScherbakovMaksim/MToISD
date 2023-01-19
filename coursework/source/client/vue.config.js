const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  runtimeCompiler: true,

  devServer: {
    port: 3000,
    proxy: {   
        '/server': {  
            target: 'http://127.0.0.1:8080/coursework/api/v1', 
            changeOrigin: true,  
            pathRewrite: {
                '^/server': '' 
            }
        }
    }
  }
})
