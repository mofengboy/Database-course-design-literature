<template>
    <div>
        <el-container class="container">
            <el-header>
                <el-card>
                    <div class="title">
                        <i class="el-icon-bangzhu"></i> 文学论坛
                    </div>
                    <div class="topMenu">
                        <div v-if="!isLogin">
                            <el-button class="topMenu-item" v-on:click="registerFormVisible=true">注册
                            </el-button>
                            <el-button class="topMenu-item" v-on:click="changeLoginFormVisible">登录</el-button>
                        </div>
                        <div v-if="isLogin">
                            <el-button class="topMenu-item">{{accessName}}</el-button>
                            <el-tag>{{userInfo.username}}</el-tag>
                        </div>
                    </div>
                </el-card>
            </el-header>
            <el-container>
                <el-aside class="aside">
                    <el-card>
                        <el-button class="publish-button" type="primary" v-on:click="publishFormVisible=true">发布主题
                        </el-button>
                        <div v-on:click="openSection(0)"><i class="el-icon-collection-tag aside-list"> 全部主题</i></div>
                        <div v-on:click="openSection(1)"><i class="el-icon-collection-tag aside-list"> 茶余饭后</i></div>
                        <div v-on:click="openSection(2)"><i class="el-icon-collection-tag aside-list"> 风花雪月</i></div>
                        <div v-on:click="openSection(3)"><i class="el-icon-collection-tag aside-list"> 校园故事</i></div>
                    </el-card>
                </el-aside>
                <el-main class="main">
                    <el-card>
                        <div v-if="!isArticle" v-for="article in articleList">
                            <div>
                                <el-card class="main-item">
                                    <div v-on:click="openArticle(article.id)">
                                        <i class="el-icon-chat-line-square content">{{article.articleTitle}}</i>
                                        <p>{{article.registerTime}}</p></div>
                                    <div v-if="accessId!==1" v-on:click="deleteArticle(article.id)">
                                        <el-tag>删除</el-tag>
                                    </div>
                                </el-card>
                            </div>
                        </div>
                        <div v-if="isArticle" class="article">
                            <h2 class="article_title">{{articleDetail.title}}</h2>
                            <p class="article_time">作者：{{articleDetail.author}} 发表时间：{{articleDetail.time}}</p>
                            <p>{{articleDetail.content}}</p>
                            <div>
                                <p>评论：</p>
                                <div v-for="review in reviewList">
                                    <el-tag style="width: 100%;margin-top: 10px">
                                        {{review.username}}在{{review.registerTime}}说：{{review.reviewContent}}
                                    </el-tag>
                                </div>
                                <div>
                                    <el-form>
                                        <el-form-item label="输入您的留言">
                                            <el-input type="textarea" v-model="reviewForm.content"></el-input>
                                        </el-form-item>
                                    </el-form>
                                    <div slot="footer" class="dialog-footer">
                                        <el-button @click="reviewForm={}">重置</el-button>
                                        <el-button type="primary" @click="publishReview()">发布</el-button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </el-card>
                </el-main>
            </el-container>
        </el-container>
        <div>
            <el-dialog title="登录" :visible.sync="loginFormVisible">
                <el-form :model="form">
                    <el-form-item label="电子邮件">
                        <el-input v-model="form.email" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="pass">
                        <el-input type="password" v-model="form.password" autocomplete="off"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="reset()">重置</el-button>
                    <el-button type="primary" @click="login()">登录</el-button>
                </div>
            </el-dialog>
        </div>
        <div>
            <el-dialog title="注册" :visible.sync="registerFormVisible">
                <el-form :model="form">
                    <el-form-item label="电子邮件">
                        <el-input v-model="form.email" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="用户名">
                        <el-input v-model="form.username" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="性别">
                        <el-radio v-model="form.sex" label=1>男</el-radio>
                        <el-radio v-model="form.sex" label=-1>女</el-radio>
                    </el-form-item>
                    <el-form-item label="密码" prop="pass">
                        <el-input type="password" v-model="form.password" autocomplete="off"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="reset()">重置</el-button>
                    <el-button type="primary" @click="register()">登录</el-button>
                </div>
            </el-dialog>
        </div>
        <div>
            <el-dialog title="发布文章" :visible.sync="publishFormVisible">
                <el-form :model="publishForm">
                    <el-form-item label="文章标题">
                        <el-input v-model="publishForm.title" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="分类">
                        <el-radio v-model="publishForm.section" label=1>茶余饭后</el-radio>
                        <el-radio v-model="publishForm.section" label=2>风花雪月</el-radio>
                        <el-radio v-model="publishForm.section" label=3>校园故事</el-radio>
                    </el-form-item>
                    <el-form-item label="文章内容">
                        <el-input type="textarea" v-model="publishForm.content"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="publishForm={}">重置</el-button>
                    <el-button type="primary" @click="publishArticle()">发布</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import Article from "./components/Article";

    var Qs = require('qs');
    export default {
        name: "Index",
        components: {Article},
        data() {
            return {
                token: "",
                url: "http://localhost:8080",
                articleList: [],
                loginFormVisible: false,
                registerFormVisible: false,
                form: {},
                isLogin: false,
                accessName: "注册会员",
                accessId: 3,
                userInfo: {},
                isArticle: false,
                articleDetail: {},
                publishFormVisible: false,
                publishForm: {},
                reviewList: {},
                reviewForm: {}
            }
        },
        mounted() {
            let _this = this;
            let data = Qs.stringify({
                    startId: 0,
                    counts: 20
                }
            );
            axios.post(_this.url + '/article/getArticleList', data).then(function (res) {
                console.log(res);
                _this.articleList = res.data.content;
            }).catch(function (e) {
                console.log(e);
            })
        },
        methods: {
            deleteArticle:function(articleId){
                let _this = this;
                let data = Qs.stringify({
                        token: _this.token,
                        articleId: articleId,
                    })
                ;
                axios.post(_this.url + '/article/deleteArticle', data).then(function (res) {
                    console.log(res);
                    if(res.data.content){
                        _this.$message({
                            message: '恭喜你，成功删除',
                            type: 'success'
                        });
                    }else {
                        _this.$message({
                            message: '您没有删除权限',
                        });
                    }

                }).catch(function (e) {
                    console.log(e);
                })
            },
            changeLoginFormVisible: function () {
                this.loginFormVisible = true;
            },
            reset: function () {
                this.form = {};
            },
            publishReview: function () {
                let _this = this;
                let data = Qs.stringify({
                        token: _this.token,
                        articleId: _this.articleDetail.articleId,
                        content: _this.reviewForm.content
                    })
                ;
                axios.post(_this.url + '/article/publishReview', data).then(function (res) {
                    console.log(res);
                    _this.$message({
                        message: '恭喜你，留言成功',
                        type: 'success'
                    });
                }).catch(function (e) {
                    console.log(e);
                })
                _this.reviewForm = {};
            },
            openSection: function (id) {
                let _this = this;
                if (id == 0) {
                    let data = Qs.stringify({
                            startId: 0,
                            counts: 20
                        }
                    );
                    axios.post(_this.url + '/article/getArticleList', data).then(function (res) {
                        console.log(res);
                        _this.articleList = res.data.content;
                    }).catch(function (e) {
                        console.log(e);
                    })
                } else {
                    let data = Qs.stringify({
                            sectionId: id,
                            startId: 0,
                            counts: 20
                        }
                    );
                    axios.post(_this.url + '/article/getArticleListBySectionId', data).then(function (res) {
                        console.log(res);
                        _this.articleList = res.data.content;
                    }).catch(function (e) {
                        console.log(e);
                    })
                }
                _this.isArticle = false;
            },
            publishArticle: function () {
                let _this = this;
                let data = Qs.stringify({
                        token: _this.token,
                        sectionId: _this.publishForm.section,
                        articleTitle: _this.publishForm.title,
                        articleContent: _this.publishForm.content
                    }
                );
                axios.post(_this.url + '/article/publishArticle', data).then(function (res) {
                    console.log(res);
                    _this.$message({
                        message: '恭喜你，文章发布成功',
                        type: 'success'
                    });
                    _this.publishFormVisible = false;
                }).catch(function (e) {
                    console.log(e);
                })
            },
            openArticle: function (id) {
                let _this = this;
                let data = Qs.stringify({
                        id: id,
                    }
                );
                axios.post(_this.url + '/article/getArticle', data).then(function (res) {
                    console.log(res);
                    _this.articleDetail.articleId = res.data.content.id;
                    _this.articleDetail.title = res.data.content.articleTitle;
                    _this.articleDetail.time = res.data.content.registerTime;
                    _this.articleDetail.authorId = res.data.content.userId;
                    _this.articleDetail.content = res.data.content.articleContent;
                    _this.isArticle = true;
                }).catch(function (e) {
                    console.log(e);
                });
                let data1 = Qs.stringify({
                        id: _this.articleDetail.authorId,
                    }
                );
                axios.post(_this.url + '/user/getUserInfoById', data1).then(function (res) {
                    console.log(res);
                    // 获取用户信息
                    _this.articleDetail.author = res.data.content[0].username;

                }).catch(function (e) {
                    console.log(e);
                });

                // 获取评论
                let data2 = Qs.stringify({
                        id: id,
                    }
                );

                axios.post(_this.url + '/article/getReviewList', data2).then(function (res) {
                    console.log(res);
                    _this.reviewList = res.data.content;
                }).catch(function (e) {
                    console.log(e);
                })

            },
            getUserInfo: function () {
                let _this = this;
                let data = Qs.stringify({
                        token: _this.token,
                    }
                );
                axios.post(_this.url + '/user/getUserInfo', data).then(function (res) {
                    console.log(res);
                    // 获取用户信息
                    _this.userInfo = res.data.content[0];
                    _this.accessId = res.data.content[1].accessId;
                    _this.isLogin = true;
                    if (3 == res.data.content[1].accessId) {
                        _this.accessName = "管理员"
                    }
                    if (2 == res.data.content[1].accessId) {
                        _this.accessName = "版主"
                    }

                }).catch(function (e) {
                    console.log(e);
                })
            },
            login: function () {
                let _this = this;
                let data = Qs.stringify({
                        email: this.form.email,
                        password: this.form.password
                    }
                );
                axios.post(_this.url + '/user/login', data).then(function (res) {
                    console.log(res);
                    if (res.data.statusCode == 1) {
                        _this.$message({
                            message: '恭喜你，登录成功',
                            type: 'success'
                        });
                        _this.loginFormVisible = false;
                        _this.isLogin = true;
                        _this.token = res.data.content;
                        // 获取用户信息
                        _this.getUserInfo();
                        _this.form = {};
                    } else {
                        _this.$message({
                            message: '密码错误',
                        });
                    }


                }).catch(function (e) {
                    _this.$message({
                        message: '密码错误',
                    });
                    console.log(e);
                })
            },
            register: function () {
                let _this = this;
                let data = Qs.stringify({
                        email: this.form.email,
                        username: this.form.username,
                        sex: this.form.sex,
                        password: this.form.password
                    }
                );
                axios.post(_this.url + '/user/register', data).then(function (res) {
                    console.log(res);
                    _this.$message({
                        message: '恭喜你，注册成功',
                        type: 'success'
                    });
                    _this.registerFormVisible = false;
                }).catch(function (e) {
                    console.log(e);
                })
            }
        }
    }
</script>

<style scoped>
    .container {

    }

    .title {
        float: left;
        font-size: 25px;
    }

    .topMenu {
        margin-top: 0;
        margin-bottom: 10px;
        float: right;
    }

    .topMenu-item {
        margin-right: 20px;
        width: 80px;
        text-align: center;
    }

    .aside {
        margin: 20px;
        /*float: left;*/
        /*width: 100%;*/
    }

    .publish-button {
        width: 100%;
    }

    .aside-list {
        margin-top: 20px;
        display: block;
    }

    .main {
        /*width: 0%;*/
        /*float: right;*/
    }

    .content {
        font-size: 25px;
    }

    .main-item {
        margin-bottom: 10px;
    }

    .article {

    }

    .article_title {
        text-align: center;
    }

    .article_time {
        text-align: center;
        font-size: 80%;
    }
</style>
