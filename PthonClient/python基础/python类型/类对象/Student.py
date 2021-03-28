class Student:
    #TODO 1. 定义私有属性,私有属性在类外部无法直接进行访问
    __weight = 0


    #TODO 2. 类的构造方法
    def __init__(self,name,age):
        """类的构造方法，类的实例化操作会自动调用 __init__() 方法"""
        """self代表类的实例，而 self.__class__代表类"""
        self.name = name   # 实例对象的成员变量
        self.age = age     # 实例对象的成员变量


    # TODO 3. 定义类方法
    def speak(self):
        """类的方法与普通的函数只有一个特别的区别——必须有一个额外的第一个参数名称： self。 """
        print("%s 说: 我 %d 岁。" % (self.name, self.age))

    def __str__ (self) ->str :   #->str 可以省略
        """ 类的toString方法 """
        ret_str = "name = %s , age = %d " % (self.name, self.age)
        return ret_str ;

# 实例化类
stu = Student('runoob',30)
print(stu) #name = runoob , age = 30
stu.speak() # runoob 说: 我 30 岁。
print("age = {0}".format(stu.age)) #age = 30
print("name = " + stu.name) #name = runoob