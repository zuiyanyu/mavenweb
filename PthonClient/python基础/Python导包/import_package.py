# # TODO 导包方式1   只能 "全限定名模块名.函数名" 的方式调用模块中的方法或变量
# import python基础.Python导包.User
# python基础.Python导包.User.printinfo();
#
#
# # TODO 导包方式2   可使用  "模块名.函数名"  方式调用函数
# from python基础.Python导包 import User
# User.printinfo()
#
# # TODO 导包方式3 给模块取别名。 可使用  "模块别名.函数名"  方式调用函数
# from python基础.Python导包 import User as UserInfo
# UserInfo.printinfo()

#TODO 导包方式4  导入某个模块中的一个函数。  可以直接使用函数名 方式调用函数
#TODO 同样的，这种方法会导入子模块User,可以使用 User.printinfo()
# from python基础.Python导包.User import printinfo
# printinfo()

#TODO 导包方式4  导入某个模块中的所有函数 和 变量 .
#TODO 当变量有冲突的时候，最后导入的包中的变量会覆盖前面包中相同名的变量。
from python基础.Python导包.Student import *  #  Student 中定义了全局变量 : a= 200
from python基础.Python导包.User import *     #  Student 中定义了全局变量 : a= 20
printinfo()
print("a= " + str(a)) # 当多个被导入的模块有相同函数名，或者变量名，使用最有一个包中的函数名，变量名


#这个可以认为是python的构造方法中的语句体
print("__name__Test.py 中运行")


#这个等价 java类中的main方法。
if __name__ =="__main__":
    print("__name__Test.py 模块自身运行！")