<template>
    <div style="align-items: center">
        <el-card style="width: 80%; margin: auto">
            <div>
                搜索：
                <el-input v-model="rsearch" style="width: 20%" @change="getShow()">
                    <i slot="prefix" class="el-input__icon el-icon-search"></i>
                </el-input>
            </div>
            <div>
                <el-divider></el-divider>
                城市：
                <el-radio-group v-model="rcity" @change="getShow()">
                    <el-radio-button label="全国"></el-radio-button>
                    <el-radio-button label="上海"></el-radio-button>
                    <el-radio-button label="北京"></el-radio-button>
                    <el-radio-button label="广州"></el-radio-button>
                    <el-radio-button label="深圳"></el-radio-button>
                </el-radio-group>
            </div>
            <div>
                <el-divider></el-divider>
                分类：
                <el-radio-group v-model="rcategory" @change="getShow()">
                    <el-radio-button :label="0">全部</el-radio-button>
                    <el-radio-button
                            v-for="category in categoryList"
                            :key="category.name"
                            :label="category.id"
                    >
                        {{ category.name }}
                    </el-radio-button>
                </el-radio-group>
            </div>
            <!--            <div>-->
            <!--                <el-divider></el-divider>-->
            <!--                时间：-->
            <!--                <el-date-picker-->
            <!--                    v-model="rtime"-->
            <!--                    type="date"-->
            <!--                    placeholder="选择日期"-->
            <!--                    @change="getShow()"-->
            <!--                    value-format="yyyy-MM-dd HH:mm:ss"-->
            <!--                    :picker-options="pickerOptions"-->
            <!--                >-->
            <!--                </el-date-picker>-->
            <!--            </div>-->
        </el-card>
        <br/>
        <el-card style="width: 80%; margin: auto">
            <div v-for="show in showList">
                <el-card @click.native="BuyShow(show.id)">
                    <el-row :gutter="2">
                        <el-col :span="3">
                            <el-image :src="show.poster"></el-image>
                        </el-col>
                        <el-col :span="21">
                            <div class="showName">
                                {{ "\xa0\xa0\xa0\xa0" + show.name }}
                            </div>

                            <br/>
                            <div class="showAddress">
                                {{ "\xa0\xa0\xa0\xa0\xa0\xa0\xa0" + show.address
                                }}{{ "\xa0\xa0\xa0\xa0" + show.city }} <br/><br/>
                                {{
                                "\xa0\xa0\xa0\xa0\xa0\xa0\xa0" +
                                show.startTime.substring(0, 10)
                                }}~{{ show.endTime.substring(0, 10) }}
                                <br/><br/><br/><br/><br/><br/><br/>
                            </div>
                            <div class="showName">
                                {{ "\xa0\xa0\xa0\xa0\xa0\xa0¥" + show.minPrice }}~¥{{ show.maxPrice }}
                            </div>

                        </el-col>
                    </el-row>
                </el-card>
                <br/>
            </div>
            <!--            分页区域-->
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="pageNumber"
                    :page-sizes="[1, 2, 5, 10]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="totalCount"
            >
            </el-pagination>
        </el-card>
    </div>
</template>

<script>
    export default {
        name: "Search",
        data()
        {
            return {
                rsearch: "",
                rcity: "全国",
                rcategory: 0,
                rtime: "",
                rsort: 0,
                categoryCommand: 0,
                categoryList: [],
                showList: [],
                // categoryList: ["演唱会","话剧歌剧","体育","展览休闲","音乐会","曲苑杂坛","舞蹈芭蕾","二次元"]

                totalCount: 0,
                pageNumber: 1,
                pageSize: 5,
                pickerOptions: {
                    disabledDate(time)
                    {
                    },
                    shortcuts: [
                        {
                            text: "今天",
                            onClick(picker)
                            {
                                picker.$emit("pick", new Date());
                            },
                        },
                        {
                            text: "明天",
                            onClick(picker)
                            {
                                const date = new Date();
                                date.setTime(date.getTime() + 3600 * 1000 * 24);
                                picker.$emit("pick", date);
                            },
                        },
                        {
                            text: "一周前",
                            onClick(picker)
                            {
                                const date = new Date();
                                date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                                picker.$emit("pick", date);
                            },
                        },
                    ],
                },
            };
        },
        created()
        {
            if (this.$route.query != null)
                this.rcategory = this.$route.query.id;
            this.getCategoryList();
            this.getShow();
        },
        methods: {
            BuyShow(id)
            {
                // console.log("id is:" + id)
                this.$router.push({
                    path: "/showDetails",
                    query: {showId: id},
                });
            },
            // 获取主分类
            async getCategoryList()
            {
                let result = await this.$http.post(this.$api.getCategoryListUrl);
                // console.log(result)
                this.categoryList = result.data.data;
                // console.log(this.categoryList)
            },
            // 展示搜索结果
            async getShow()
            {
                // console.log(this.rcategory);
                var city;
                // 设置城市
                if (this.rcity === "全国") city = "";
                else city = this.rcity;

                let result = await this.$http.post(
                    this.$api.SearchShowUrl,
                    JSON.stringify({
                        categoryId: this.rcategory,
                        date: this.rtime,
                        sort: this.rsort,
                        pageNum: this.pageNumber,
                        pageSize: this.pageSize,
                        city: city,
                        keyword: this.rsearch,
                    })
                );
                if (result.data.code === 200)
                {
                    this.showList = result.data.data.list;
                    this.totalCount = result.data.data.total;
                } else
                {
                    this.showList = [];
                    this.totalCount = 0;
                }
                // console.log("pageSize:" + this.pageSize);
                // console.log("pageNumber:" + this.pageNumber);
                console.log(result.data.data);
            },
            //监听pageSize改变的事件
            async handleSizeChange(newSize)
            {
                this.pageSize = newSize;
                this.pageNumber = 1;
                // console.log("pageSize:"+this.pageSize);
                await this.getShow();
            },
            //监听pageNum改变的事件
            async handleCurrentChange(newPage)
            {
                this.pageNumber = newPage;
                // console.log("pageNumber:"+this.pageNumber);
                await this.getShow();
            },
        },
    };
</script>

<style scoped>
    #set1 {
        justify-content: center;
        align-items: center;
    }

    .showName {
        font-size: 18px;
    }

    .showAddress {
        font-size: 12px;
    }
</style>
