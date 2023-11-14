import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/pages/Home.vue'
import About from '@/pages/About.vue'
import Members from '@/pages/Members.vue'
import MemberInfo from '@/pages/MemberInfo.vue'

	const router = createRouter({
        history: createWebHistory(),
        routes: [
            { path: '/', component: Home },
            { path: '/about', component: About },
            { path: '/members', component: Members },
            // 동적 라우팅 방법
            { path: '/members/:id', component: MemberInfo},
            // { path: '/members/1', component: MemberAladin },
            // { path: '/members/2', component: MemberBBBB },
            // { path: '/members/3', component: MemberCCCC },
        ]
})

export default router;