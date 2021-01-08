

const APIRootUrl = 'http://47.103.203.188:8000/';
// const APIRootUrl = 'http://localhost:8000/';


module.exports = {

    // 测试
    // TestUrl: Url + 'movie/actors',


    // 登录注册
    LoginUrl: APIRootUrl + 'user/usernameLogin',
    LoginTel1Url:APIRootUrl + 'user/telephoneLogin',
    LoginTel2Url:APIRootUrl + 'user/authCodeLogin',
    getAuthCodeUrl: APIRootUrl + 'user/getAuthCode',
    registerUrl: APIRootUrl + 'user/register',

    // 分类相关
    getCategoryListUrl: APIRootUrl + 'category/categoryList',
    getCategoryMapUrl: APIRootUrl + 'category/categoryMap',
    getCategoryNameUrl: APIRootUrl + 'category/category',
    searchUrl: APIRootUrl + 'show/search',
    SearchShowUrl: APIRootUrl + 'show/search',
    getParentCategoryUrl: APIRootUrl + 'category/getParentCategory',

    // 个人信息
    getUserInfoUrl: APIRootUrl + 'user/info',
    getUserUrl: APIRootUrl + 'user/info',
    updateUserInfoUrl: APIRootUrl + 'user/update',

    // 收货地址
    getAddressListUrl: APIRootUrl + 'address/list',
    getAddressUrl: APIRootUrl + 'address',
    getDefaultAddressUrl: APIRootUrl + 'address/getDefault',
    addAddressUrl: APIRootUrl + 'address/add',
    deleteAddressUrl: APIRootUrl + 'address/delete',
    updateAddressUrl: APIRootUrl + 'address/update',
    setDefaultAddressUrl: APIRootUrl + 'address/setDefault',

    // 联系人相关
    getFrequentListUrl: APIRootUrl + 'frequent/list',
    addFrequentUrl: APIRootUrl + 'frequent/add',
    deleteFrequentUrl: APIRootUrl + 'frequent/delete',
    updateFrequentUrl: APIRootUrl + 'frequent/update',
    getDefaultFrequentUrl: APIRootUrl + 'frequent/getDefault',
    getFrequentUrl: APIRootUrl + 'frequent',
    setDefaultFrequentUrl: APIRootUrl + 'frequent/setDefault',

    //订单相关
    getOrderListUrl: APIRootUrl + 'order/list',
    getShowName: APIRootUrl + 'show/detail',
    getOrderDetailsUrl: APIRootUrl +'order',
    getAddressUrl: APIRootUrl +'address',
    getShowDetails: APIRootUrl +'show/detail',
    deleteUserOrder: APIRootUrl +'order/delete',
    refundUserOrder: APIRootUrl +'order/cancel',

    //评论相关
    addReviewUrl: APIRootUrl +'review/add',

    //演出相关
    getShowSessionUrl: APIRootUrl +'session/sessionList',
    getShowClassUrl: APIRootUrl + 'class/classList',
    buyTicketUrl: APIRootUrl + 'order/add',
}