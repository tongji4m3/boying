import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI, {Message} from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import VCharts from 'v-charts'
import './icons/iconfont/iconfont.css'
import axios from 'axios';


// 为了实现Class的私有属性
const showMessage = Symbol('showMessage')

/**
 *  重写ElementUI的Message
 *  single默认值true，因为项目需求，默认只弹出一个，可以根据实际需要设置
 */
class DonMessage
{
    success(options, single = true)
    {
        this[showMessage]('success', options, single)
    }

    warning(options, single = true)
    {
        this[showMessage]('warning', options, single)
    }

    info(options, single = true)
    {
        this[showMessage]('info', options, single)
    }

    error(options, single = true)
    {
        this[showMessage]('error', options, single)
    }

    [showMessage](type, options, single)
    {
        if (single)
        {
            // 判断是否已存在Message
            if (document.getElementsByClassName('el-message').length === 0)
            {
                Message[type](options)
            }
        } else
        {
            Message[type](options)
        }
    }
}

Vue.use(ElementUI);
Vue.use(VCharts)

//使用重写的Message防止出现多个message并存的情况
Vue.prototype.$message = new DonMessage()

Vue.config.productionTip = false

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')

//设置axios请求头加入token
axios.interceptors.request.use(config =>
    {
        console.log("token token")
        if (config.push === '/')
        {
        } else
        {
            if (window.sessionStorage.getItem('token'))
            {
                console.log("token here")
                config.headers.Authorization = `Bearer ${window.sessionStorage.getItem('token')}`;
            }
        }
        return config;
    },
    error =>
    {
        console.log("axios.interceptors.request", error)
    });
