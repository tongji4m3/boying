import Vue from 'vue'
import VueRouter from 'vue-router'
import PageContainer from "@/components/PageContainer.vue"
import LogIn from '@/views/LogIn.vue';
import Home from "@/views/Home.vue"
import Show from "@/views/Show.vue"
import Category from "@/views/Category.vue"
import AddShow from "@/views/AddShow.vue"
import Order from "@/views/Order.vue"
import User from "@/views/User.vue"
import UpdateShow from '@/views/UpdateShow.vue';

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: '#',
        redirect: '/login',
    },
    {
        path: '/login',
        name: 'login',
        component: LogIn
    },

    {
        path: '/main',
        name: '首页',
        component: PageContainer,
        children: [
            {
                path: '/home',
                name: '首页',
                component: Home
            },
            {
                path: '/show',
                name: '演出',
                component: Show
            },
            {
                path: '/category',
                name: '演出目录',
                component: Category
            },
            {
                path: '/addshow',
                name: "添加演出",
                component: AddShow
            },
            {
                path: '/order',
                name: '订单列表',
                component: Order
            },
            {
                path: '/user',
                name: '用户列表',
                component: User
            },
            {
                path: '/UpdateShow',
                name: 'UpdateShow',
                component: UpdateShow
            },
        ]
    },

    //   {
    //     path: '/about',
    //     name: 'About',
    //     // route level code-splitting
    //     // this generates a separate chunk (about.[hash].js) for this route
    //     // which is lazy-loaded when the route is visited.
    //     component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    //   }
]

const router = new VueRouter({
    routes
});

//挂载路由导航守卫
router.beforeEach((to, from, next) =>
{
    if (to.path === '/login')
    {
        window.sessionStorage.clear();
        return next();
    }
    //获取token
    const tokenStr = window.sessionStorage.getItem('token');
    //无token强制跳转到登录页面
    if (!tokenStr) return next('/login');
    next();
})

export default router
