<template>
    <el-container class="index-container">
        <!--        上边框-->
        <el-header v-show="!isLogin || !isHome">
            <div></div>
            <!--            开启了路由模式-->

            <el-menu
                    v-if="!isLogin || !isHome"
                    router
                    class="el-menu-demo"
                    mode="horizontal"
                    background-color="rgb(43,43,43)"
                    text-color="rgb(255,255,255)"
                    active-text-color="#0099FF"
                    default-active="/Welcome"
            >
                <el-menu-item index="/home"><i class="el-icon-s-home"></i>首页</el-menu-item>
                <el-menu-item index="/login" v-if="!isLogin"><i class="myicon myicondenglu-1"></i> 登录</el-menu-item>
                <el-menu-item index="/register" v-if="!isLogin"><i class="myicon myiconzhuce"></i> 注册</el-menu-item>
            </el-menu>
        </el-header>
        <!--        主体区域-->
        <el-main>
            <!--            路由占位符-->
            <router-view style="align-items: center"></router-view>
        </el-main>
        <el-footer>
            <!--            <div id="foot">-->
            <!--                <span id="shineText">石稼晟 1851632 19946254167 1171011192</span>-->
            <!--            </div>-->
        </el-footer>
    </el-container>
</template>

<script>
    export default {
        data()
        {
            return {
                isLogin: false,
                isHome: false,
                token: window.sessionStorage.getItem("token"),
            };
        },
        created()
        {
        },
        mounted()
        {
            this.listenPage();
            this.token = window.sessionStorage.getItem("token");
            console.log(this.token);
            if (this.token != null)
            {
                this.isLogin = true;
            } else
            {
                this.isLogin = false;
            }
        },

        watch: {
            $route(to, from)
            {
                console.log(to.path);
                if (to.path === "/home")
                {
                    this.isHome = true;
                } else
                {
                    this.isHome = false;
                }
                this.token = window.sessionStorage.getItem("token");
                console.log(this.token);
                if (this.token != null)
                {
                    this.isLogin = true;
                } else
                {
                    this.isLogin = false;
                }
            },
        },
        methods: {
            listenPage()
            {
                if (this.$route.path == '/home')
                {
                    this.isHome = true;
                }
                window.onbeforeunload = function (e)
                {
                    e = e || window.event;
                    if (e)
                    {
                        e.returnValue = "关闭提示";
                    }
                    return "关闭提示";
                };
            },
        },
    };
</script>

<style scoped>
    #username-div {
        font-size: 20px;
        font-weight: bold;
        font-family: 楷体;
    }

    .index-container {
        height: 100%;
    }

    .el-header {
        /*background-color: rgb(255,255,255);*/
        background-color: rgb(43, 43, 43);
        display: flex;
        justify-content: space-between;
        margin-left: 0;
        /* padding-left: 0; */
    }

    .el-main {
        /*background-color: rgb(239,239,239);*/
        background-color: rgb(255, 255, 255);
        margin: 0;

        padding: 0;
    }

    #foot {
        height: 200px;
        background: rgb(0, 0, 0);
        position: relative;
    }

    #shineText {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        padding: 30px 78px;
        color: #ffffff;
        background: linear-gradient(to right, #4d4d4d 0, #fff 10%, #4d4d4d 20%);
        background-position: 0;
        -webkit-background-clip: text;
        /*background-clip: text;*/
        -webkit-text-fill-color: transparent;
        animation: shine 5.5s linear infinite;
        font-weight: 500;
        font-size: 26px;
        white-space: nowrap;
    }
</style>
