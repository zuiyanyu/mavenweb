CREATE TABLE `user` (
  `user_id` int(11) primary key  auto_increment  comment '主键：账号id' ,
  `user_name` varchar(200) DEFAULT null comment '用户名',
  `user_age` int(11) DEFAULT NULL comment '用户年龄',
  `birthday` datetime DEFAULT NULL comment '用户生日',
   address   varchar(250) default null  comment '用户地址'
) comment "用户账号表"

-- 用户购买的商品订单表
create table orders (
 order_id int(11)  primary key  auto_increment  comment '主键：订单id' ,
 user_id int(11)   not null comment '外键：用户id',
 numbers int(11)    not null comment '本单中订购的商品数量',
 create_time timestamp  not null default current_timestamp()  comment '下单时间'
)comment "用户购买的商品订单表"


-- 订单商品详情表
create table orderdetail (
 orderdetail_id int(11)  primary key  auto_increment  comment '主键：订单商品详情id' ,
 order_id int(11)   not null comment '外键：订单id',
 items_id int(11)   not null comment '外键：商品id',
 order_status varchar(30) not null default '1' comment '订单商品状态：0-订单已取消，1-待配送，2-配送中，3-已配送',
 order_action varchar(30)  not null default '1' comment '用户行为：1-正常配送，2-取消订单，3-延迟配送，4-催单',
 item_name   varchar(255) not null default '1' comment '购买时商品名称',
 item_price  double default null comment '购买时商品价格',
 create_time timestamp  not null default current_timestamp()  comment '下单时间'
)comment "订单商品详情表"

-- 商品表
create table items (
 item_id int(11)  primary key  auto_increment  comment '主键：商品id' ,
 item_name varchar(255) not null default '1' comment '当前商品名称',
 item_price double  not null default '1' comment '当前商品价格',
 create_time timestamp  not null default current_timestamp()  comment '下单时间'
)comment "商品表"


select * from orders ;

-- 目前有两个用户 买东西
insert into user ( user_id,user_name,user_age,birthday,address) values
(1,"张三",23,current_timestamp,"郑州"),
(2,"李四张三",24,current_timestamp,"北京")
-- 商铺的商品有四个
insert into items(item_id, item_name, item_price)values
(1,"苹果",2.2),
(2,"桃子",3.3),
(3,"樱桃",8.3),
(4,"梨子",1.4) ;

-- 一个用户下了两个订单  第一个订单有2个商品，第2个订单有三个商品
insert into orders(order_id,user_id,numbers) values
(1,1,2),
(2,1,3);

-- 订单详情：第一个订单和第二个订单
insert into orderdetail(orderdetail_id,order_id, items_id,item_name, item_price)values
-- 第1个订单
(1,1,1,"苹果",2.2),
(2,1,2,"桃子",3.2),
-- 第2个订单
(3,2,3,"樱桃",8.1),
(4,2,4,"梨子",1.4),
(5,2,1,"苹果",2.2)
;
