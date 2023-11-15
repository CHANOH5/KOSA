// import './assets/main.css'
import 'bootstrap/dist/css/bootstrap.css'

import { createApp } from 'vue'
import App from './App.vue'

import router from './router'

const app = createApp(App)
app.use(router)
//전역변수 선언
app.config.globalProperties.backURL='http://192.168.1.22:8888/back2'
app.mount('#app')

// createApp(App).mount('#app')
