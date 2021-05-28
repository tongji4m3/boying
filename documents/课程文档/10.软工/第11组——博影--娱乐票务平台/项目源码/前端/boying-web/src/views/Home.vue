<template>
    <div>
        <el-container v-loading="loading">
            <el-backtop :bottom="60" :right="60">
            </el-backtop>
            <el-header v-show="currentUser.userId !== ''">
                <div></div>
                <div class="out-button">
                    <el-dropdown>
            <span class="el-dropdown-link" style="margin-right: 15px">
              <el-badge is-dot class="item">
                <el-avatar :src="currentUser.icon"></el-avatar>
              </el-badge>
              <i class="el-icon-arrow-down el-icon--right"></i>
            </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item
                                    class="navigation-text"
                                    id="username-div"
                            ></el-dropdown-item>
                            <el-dropdown-item
                                    class="navigation-text"
                                    @click.native="toSelfInformation"
                            ><i class="myicon myiconxiangqing"></i>我的信息
                            </el-dropdown-item
                            >
                            <el-dropdown-item class="navigation-text" @click.native="toOrder"
                            ><i class="myicon myicon74wodedingdan"></i>我的订单
                            </el-dropdown-item
                            >
                            <el-dropdown-item
                                    class="navigation-text"
                                    id="exit-div"
                                    divided
                                    @click.native="logout"
                            ><i class="myicon myicontuichu"></i>退出
                            </el-dropdown-item
                            >
                        </el-dropdown-menu>
                    </el-dropdown>
                </div>
            </el-header>
            <el-main style="padding: 0">
                <el-card shadow="never" style="width: 80%; margin: auto">
                    <!--            走马灯-->
                    <el-carousel indicator-position="outside" height="320px">
                        <el-carousel-item>
                            <img
                                    src="https://i.loli.net/2020/12/20/jI9Pqw5HUVEbSsr.jpg"
                                    height="400*width"
                                    width="1500*width"
                                    alt=""
                            />
                        </el-carousel-item>
                        <el-carousel-item>
                            <img
                                    src="https://i.loli.net/2020/12/20/phOzri862tuXZ9V.jpg"
                                    height="400*width"
                                    width="1500*width"
                            />
                        </el-carousel-item>
                        <el-carousel-item>
                            <img
                                    src="https://i.loli.net/2020/12/20/PgD9QxLN2JtnuXS.jpg"
                                    height="400*width"
                                    width="1500*width"
                            />
                        </el-carousel-item>
                        <el-carousel-item>
                            <img
                                    src="https://i.loli.net/2020/12/20/7AxHqzCvWItlSRj.jpg"
                                    height="400*width"
                                    width="1500*width"
                            />
                        </el-carousel-item>
                    </el-carousel>
                    <!--            种类分类-->
                    <el-card shadow="never">
                        <el-row>
                            <el-col
                                    :span="3"
                                    v-for="category in categoryList"
                                    :key="category.id"
                                    @click.native="search(category.id)"
                            >
                                <!--                    <el-card class="myCard" :body-style="{ padding: '20px'}" shadow="hover">-->
                                <!--                        {{category}}-->
                                <!--                    </el-card>-->
                                <div style="text-align: center">
                                    <div class="categoryCol">
                                        <i
                                                :class="iconList[category.id - 1]"
                                                style="font-size: 25px"
                                        ></i>
                                    </div>
                                    <br/>
                                    <div class="categoryCol">{{ category.name }}</div>
                                </div>
                            </el-col>
                        </el-row>
                    </el-card>
                    <br/>
                    每个分类
                    <div v-for="(childrenList, i) in showList" :key="i">
                        <el-card shadow="never">
                            <el-link
                                    :underline="false"
                                    @click="search(categoryList[i].id)"
                                    style="font-size:20px"
                            ><i
                                    :class="iconList[i]"
                                    style="font-size: 20px; margin-right: 10px"
                            ></i
                            >{{ categoryList[i].name }}：
                            </el-link
                            >
                            <el-row :gutter="30">
                                <el-col :span="6">
                                    <el-card
                                            shadow="hover"
                                            v-if="childrenList"
                                            @click.native="BuyShow(childrenList[0].id)"
                                            class="bigPosterCard"
                                            :body-style="{ padding: '23px' }"
                                    >
                                        <el-tooltip
                                                class="item"
                                                effect="light"
                                                :content="childrenList[0].name"
                                                placement="bottom"
                                        >
                                            <img
                                                    width="200"
                                                    height="280"
                                                    :src="childrenList[0].poster"
                                                    class="image"
                                                    style="margin: auto"
                                            />
                                        </el-tooltip>
                                    </el-card>
                                </el-col>
                                <el-col :span="18">
                                    <el-row :gutter="10" class="el-row">
                                        <el-col
                                                :span="8"
                                                v-for="(show, j) in getSix(childrenList)"
                                                :key="j"
                                        >
                                            <el-tooltip
                                                    class="item"
                                                    effect="light"
                                                    :content="show.name"
                                                    placement="bottom"
                                            >
                                                <el-card
                                                        class="el-card"
                                                        :body-style="{ padding: '10px' }"
                                                        shadow="hover"
                                                        @click.native="BuyShow(show.id)"
                                                >
                                                    <el-col :span="10">
                                                        <img
                                                                width="100"
                                                                height="140"
                                                                :src="show.poster"
                                                                class="image"
                                                        />
                                                    </el-col>
                                                    <el-col :span="14">
                                                        <div class="showName">
                                                            {{
                                                            show.name.length >= 12
                                                            ? show.name.substring(0, 12) + "..."
                                                            : show.name
                                                            }}
                                                        </div>
                                                        <div class="showAddress">
                                                            <br/>
                                                            {{ show.city }}
                                                            <br/><br/>
                                                            {{ show.startTime.substring(0, 10) }}~{{
                                                            show.endTime.substring(0, 10)
                                                            }}
                                                            <br/>
                                                            <br/>
                                                        </div>

                                                        ¥{{ show.minPrice }}~¥{{ show.maxPrice }}
                                                    </el-col>
                                                </el-card>
                                            </el-tooltip>
                                        </el-col>
                                    </el-row>
                                </el-col>
                            </el-row>
                        </el-card>
                        <br/>
                    </div>
                </el-card>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "Home",
        data()
        {
            return {
                loading: true,
                categoryCommand: 0,
                categoryList: [],
                showList: [],
                translateList: [],
                //图标集
                iconList: [
                    "myicon myiconchangge",
                    "myicon myiconhuajugeju",
                    "myicon myicontiyu",
                    "myicon myiconertongqinzi28",
                    "myicon myiconzhanlanxiuxian28",
                    "myicon myiconyinlehui",
                    "myicon myiconquyuanzatan28",
                    "myicon myiconerciyuan",
                ],
                // translateList: ["演唱会","话剧歌剧","体育","儿童亲子","展览休闲","音乐会","曲苑杂坛","二次元"],

                currentUser: {
                    userId: "",
                    icon: "",
                },
                categoryForm: {
                    categoryId: "",
                    parentId: "",
                    name: "",
                    weight: "",
                    description: "",
                },

                searchForm: {
                    categoryId: "",
                    pageNum: 1,
                    pageSize: 6,
                },
            };
        },
        created()
        {
            this.getCategoryList();
            this.getUser();
            setTimeout(() =>
            {
                this.loading = false;
            }, 500);
        },
        methods: {
            getSix(list)
            {
                if (!list)
                {
                    return [];
                }
                let result = [];
                result.length = list.length - 1;
                for (let i = 1; i < list.length; i++)
                {
                    result[i - 1] = list[i];
                }
                // console.log(list);
                // console.log(result);
                return result;
            },
            BuyShow(id)
            {
                this.$router.push({
                    path: "/showDetails",
                    query: {showId: id},
                });
            },
            search(id)
            {
                this.$router.push({
                    path: "/search",
                    query: {id: id},
                });
            },
            toOrder()
            {
                this.$router.push("/order");
            },
            logout()
            {
                this.$router.push("/login");
                this.$message.success("退出成功");
            },
            toSelfInformation()
            {
                this.$router.push("/selfInformation");
            },
            async getCategoryList()
            {
                let result = await this.$http.post(this.$api.getCategoryListUrl);
                // console.log(result)
                this.categoryList = result.data.data;
                // console.log(this.categoryList)
                this.showList.length = this.categoryList.length;
                this.translateList.length = this.categoryList.length;
                for (var i = 0; i < this.categoryList.length; i++)
                {
                    if (!(await this.getShow(this.categoryList[i].id)))
                    {
                        this.showList.length = i;
                        this.translateList.length = i;
                        break;
                    }
                    this.showList[i] = await this.getShow(this.categoryList[i].id);
                    // this.showList[i].name = this.categoryList[i].name;
                }
                // console.log(this.translateList);
                this.$forceUpdate();
            },
            async getShow(id)
            {
                let result = await this.$http.post(
                    this.$api.SearchShowUrl,
                    JSON.stringify({
                        categoryId: id,
                        pageNum: 1,
                        pageSize: 7,
                        sort: 2,
                    })
                );

                // console.log(result);
                this.$forceUpdate();
                if (result.data.code === 200)
                {
                    // for (let i = 0; i < result.data.data.list.length; i++)
                    // {
                    //     result.data.data.list.length[i].dayEnd=result.data.data.list.length[i].dayEnd.substring(0,10);
                    //     result.data.data.list.length[i].dayStart=result.data.data.list.length[i].dayStart.substring(0,10);
                    // }
                    console.log(result);
                    return result.data.data.list;
                } else return [];
            },

            async getUser()
            {
                try
                {
                    const res = await axios.post(this.$api.getUserUrl);
                    // console.log(res);
                    if (res.data.code === 200)
                    {
                        this.currentUser = res.data.data;
                    } else
                    {
                        this.currentUser.userId = "";
                    }
                } catch (err)
                {
                    // console.log(err);
                    this.$message.error("获取用户信息失败");
                }
            },
        },
    };
</script>

<style scoped>
    .home-container {
        height: 100%;
    }

    #username-div {
        font-size: 20px;
        font-weight: bold;
    }

    #exit-div {
        text-align: center;
        font-weight: bold;
    }

    .el-header {
        background-color: rgb(43, 43, 43);
        display: flex;
        justify-content: space-between;
        margin-left: 0;
        padding-left: 0;
        width: 100%;
    }

    .out-button {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .el-icon-arrow-down {
        font-size: 12px;
    }

    .el-carousel__item h3 {
        color: #475669;
        font-size: 18px;
        opacity: 0.75;
        line-height: 300px;
        margin: 0;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n + 1) {
        background-color: #d3dce6;
    }

    .categoryCol {
        cursor: pointer; /*鼠标悬停变小手*/
        background-clip: padding-box;
    }

    .showName {
        font-size: 18px;
    }

    .showAddress {
        font-size: 12px;
    }

    .myCard {
        cursor: pointer; /*鼠标悬停变小手*/
    }

    .bigPosterCard {
        cursor: pointer; /*鼠标悬停变小手*/
    }

    .el-row {
        min-height: 100%;
        height: 100%;
        margin-bottom: 20px;
        display: flex;
        flex-wrap: wrap;
    }

    .el-row .el-card {
        min-width: 100%;
        height: 100%;
        margin-right: 20px;
        transition: all 0.5s;
        cursor: pointer;
    }
</style>
