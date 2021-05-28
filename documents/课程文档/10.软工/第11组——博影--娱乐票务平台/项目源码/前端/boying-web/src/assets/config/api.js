const APIRootUrl = 'http://101.132.157.174:8000/';


module.exports = {
    // 登录注册
    LoginUrl: APIRootUrl + 'user/usernameLogin',
    LoginTel1Url: APIRootUrl + 'user/telephoneLogin',
    LoginTel2Url: APIRootUrl + 'user/authCodeLogin',
    getAuthCodeUrl: APIRootUrl + 'user/getAuthCode',
    registerUrl: APIRootUrl + 'user/register',

    // 个人信息
    getUserInfoUrl: APIRootUrl + 'user/info',
    getUserUrl: APIRootUrl + 'user/info',
    updateUserInformationUrl: APIRootUrl + 'user/update',
    updatePasswordInfoUrl: APIRootUrl + 'user/updatePassword',

    // 分类相关
    getCategoryListUrl: APIRootUrl + 'category/categories',
    getCategoryNameUrl: APIRootUrl + 'category/details',

    //演出相关
    SearchShowUrl: APIRootUrl + 'show/search',
    getShowName: APIRootUrl + 'show/detail',

    //订单相关
    getOrderListUrl: APIRootUrl + 'order/list',
    getOrderDetailsUrl: APIRootUrl + 'order/details',
    getShowDetails: APIRootUrl + 'show/detail',
    deleteUserOrder: APIRootUrl + 'order/delete',
    refundUserOrder: APIRootUrl + 'order/cancel',
    buyTicketUrl: APIRootUrl + 'order/add',

    //演出座次相关
    getShowSeatUrl: APIRootUrl + 'seat/detail',
    getOrderSeatUrl: APIRootUrl + 'order/tickets',
}
