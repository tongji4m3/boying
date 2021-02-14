```
Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0b25namk0bTMiLCJjcmVhdGVkIjoxNjExOTk5NjMzMzQ0LCJleHAiOjE2MTI2MDQ0MzN9.3Wg_-vc0FHk4jWWhj3hgxwjM_DwRlDDRpEQ3is0qoeUPKn3LU1iY9TD8IT0gjq-LQ22VUZre4mTaEbmSmEakYg
```



```
47.103.203.188 部署数据库
101.132.157.174 部署Jar包1
47.100.47.244  ABC123456 部署Jar包2
47.100.88.187 sjs@2021 部署Nginx服务器 
```



```
先利用/usr/local/nginx/sbin/nginx -t测试配置文件修改是否正常
/usr/local/nginx/sbin/nginx -s reload重新加载
./nginx -s reload
ps -aux | grep nginx
./nginx -s stop
vim /usr/local/nginx/conf/nginx.conf

```





# 活动整合方式

1. 用户进入订单页面，只能通过list获取到所有的订单列表，然后再进行查看详情、完成、取消、删除操作
2. 所以那些其他的操作不需要再判断订单是否存在，订单是否被用户删除
3. 但是需要判断是否被管理员删除，可能前端没设置





```java
 查询演出信息时，在List<BoyingSeatModel> getShowSeatList(Integer showId);等得到座次的方法中，BoyingSeatModel聚合了一个BoyingPromoModel，如果promoModel不为空，则表示其拥有还未结束的秒杀活动

BoyingPromoModel boyingPromoModel = boyingPromoService.getPromo(seatDO.getId());
 //存在秒杀活动，而且是未开始或者是正在进行中的
 if (boyingPromoModel != null && boyingPromoModel.getStatus() != 3) {
 boyingSeatModel.setBoyingPromoModel(boyingPromoModel);
 }
 
 
 BoyingPromoServiceImpl的getPromo(Integer seatId)将数据库中对应座次的秒杀活动取出来，如果不存在则返回null，并且转换为BoyingPromoModel（多了一个status字段表示秒杀活动状态 1表示还未开始，2表示进行中，3表示已结束）
 
 
  在返回给前端时，如果该演出座次有秒杀活动则返回给前端，没有则不返回
if (boyingSeatModel.getBoyingPromoModel() != null) {
	//有正在进行或即将进行的秒杀活动
	boyingSeatVO.setPromoStatus(boyingSeatModel.getBoyingPromoModel().getStatus());
    boyingSeatVO.setPromoId(boyingSeatModel.getBoyingPromoModel().getId());
    boyingSeatVO.setStartTime(boyingSeatModel.getBoyingPromoModel().getStartTime());
    boyingSeatVO.setPromoPrice(boyingSeatModel.getBoyingPromoModel().getPrice());
} 
else {
	boyingSeatVO.setPromoStatus(0);
}
```





# 安全权限

+ 用户管理
+ 角色管理
+ 菜单管理
+ 用户授权
+ 用户角色





在暑假的时候做了一个基本的商品后台权限管理系统，之后又做了博影项目，就把权限管理系统给重构并整合变成一个项目了