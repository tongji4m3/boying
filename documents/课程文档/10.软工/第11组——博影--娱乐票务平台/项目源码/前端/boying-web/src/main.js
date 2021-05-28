import Vue from 'vue'
import App from './Index.vue'
import router from './router'
import store from './store'
//引入阿里图标
import './icons/iconfont/iconfont.css'
// md5加密
import md5 from 'js-md5'
// ElementUI https://element.eleme.cn/2.0/#/zh-CN/component/installation
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
//全局api
import api from './assets/config/api.js'
//全局axios配置
import axios from 'axios'

Vue.prototype.$md5 = md5

Vue.use(ElementUI);

Vue.prototype.$api = api


Vue.prototype.$http = axios
axios.defaults.headers['Content-Type'] = 'application/json';

//设置axios请求头加入token
axios.interceptors.request.use(config =>
    {
        if (config.push === '/')
        {
        } else
        {
            if (window.sessionStorage.getItem('token'))
            {
                config.headers.Authorization = `Bearer ${window.sessionStorage.getItem('token')}`;
            }
        }
        return config;
    },
    error =>
    {
        console.log("axios.interceptors.request", error)
    });

Vue.config.productionTip = false

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
