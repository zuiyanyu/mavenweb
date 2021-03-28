from collections import namedtuple

#TODO 定义一个namedtuple .  类似于 scala中的样例类 case class User(name,age,height)
User = namedtuple("User",["name","age","height"])

#TODO 几种使用方式

#指定参数名称 ：方式1
user_tuple = User(name='zhangsan',age=20,height=170)
print(user_tuple.name,user_tuple.age,user_tuple.height)

# 不指定参数名称：方式2
user_tuple = User('zhangsan',20,170)
print(user_tuple.name,user_tuple.age,user_tuple.height)

# 使用字典进行赋值：方式3（类似方式1）
UserInfo_dict = {
    'name':'zhangsan',
    'age':20,
    'height':170
}
user_tuple = User(**UserInfo_dict) # 必须带 ** ，表示入参是一个字典
print(user_tuple.name,user_tuple.age,user_tuple.height)

# 使用元组赋值：方式4 （类似方式2）
userInfo_fromMysql_tuple = ("zhangsan",20,170)
user_tuple = User(*userInfo_fromMysql_tuple) #必须加上 * ，表示入参是一个元组
print(user_tuple.name,user_tuple.age,user_tuple.height)

#TODO  User表的数据全部取出，然后加一个列
# (应用场景：User表从库中取出，然后加一个列后再存到数据库表中)
# 定义一个新的namedtuple,多了一列 address
NewUser = namedtuple("NewUser",["name","age","height","address"])

#数据库返回形式为元组：方式5 （结合方式4）
userInfo_fromMysql_tuple = ("zhangsan",20,170)
newUser = NewUser(*userInfo_fromMysql_tuple,"北京")  #记得加*
print(newUser)

#数据库返回形式为字典：方式6（结合方式3）
UserInfo_dict = {
    'name':'zhangsan',
    'age':20,
    'height':170
}
newUser = NewUser(**UserInfo_dict,address="北京")
print(newUser)