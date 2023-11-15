import { createRouter, createWebHistory } from 'vue-router'
// @은 'src/' 별칭을 의미합니다
import Home from '@/pages/Home.vue'
import Login from '@/pages/Login.vue'
import Signup from '@/pages/Signup.vue'
// import productlist from '@/pages/productlist.vue'

	const router = createRouter({
        history: createWebHistory(),
        routes: [
            { path: '/', component: Home },
            { path: '/login', component: Login },
            { path: '/signup', component: Signup },
        ]
})

export default router;