// import './assets/main.npm css'

import 'bootstrap/dist/css/bootstrap.css'
import { createApp } from 'vue'
import App from './App.vue'

// router/index.js에서 생성한 라우터 객체를 app에 추가
// import router from './router'
import router from './router/index.js'

const app = createApp(App)
app.use(router)
app.mount('#app')

// createApp(App).mount('#app')
