#TODO 元祖分 Tuple 和 NamedTuple
# string、list 和 tuple 都属于 sequence（序列）。
# tuple比list好的地方，为 Immutable的重要性：
# 1.性能优化：当tuple的元素全部为immutable的tuple，会作为常量在编译时候确定，因此速度更快。
# 2.线程安全
# 3.可以作为dictdekey 。(tuple元素全部为不可变的时候)
# 4. 拆包特性

# 1、与字符串一样，元组的元素不能修改。
# 元组写在小括号 () 里，元素之间用逗号隔开
# 元组与字符串类似，可以被索引且下标索引从0开始，-1 为从末尾开始的位置。也可以进行截取
# 2、元组也可以被索引和切片，方法一样。
# 3、注意构造包含 0 或 1 个元素的元组的特殊语法规则。
# 4、元组也可以使用+操作符进行拼接

print("==== tuple的创建")
# 方式1：构造包含 0 个或 1 个元素的元组比较特殊，所以有一些额外的语法规则：
tup1 = ()    # 空元组
tup2 = (20,) # TODO 一个元素，需要在元素后添加逗号

tup3 = (12345, 54321, 'hello!')

tup4 = 12345, 54321, 'hello!'  # 元组由若干逗号分隔的值组成  #在输入时可能有或没有括号， 不过括号通常是必须的

tup3 = 12345,(54321, 'hello!')  #

print(tup4) #(12345, 54321, 'hello!')

print("==== tuple的元素内容的更新")
#Tuple中的元素地址不可变，但是元素的内容是可以改变的
name_tuple =('boddy1',[])

name_tuple[1].append("zhangsan")
print(name_tuple)  # ('boddy1', ['zhangsan'])




print("====tuple的遍历")
#可迭代 iterable
name_list = ('zhangsan','lizi')
for name in name_list :
    print(name)

#元祖Tuple是不可变(Immutable)的
#TODO a.其中的元素全部为不可变的时候，tuple可以作为字典的key的时候
_dict ={}
_tuple = ('abc',123)
_dict[_tuple] = 'ok'
print(_dict) #{('abc', 123): 'ok'}

#TODO b.其中的元素有可变的时候，tuple不可以作为字典的key的时候
_list =[1,2,3,4]
_tuple = (_list,'abc')
#dict[tuple] = 'fail'   # 报异常：TypeError: unhashable type: 'list'

print("=====Tuple的拆包 ")
name_tuple =("body",29,175)

#TODO 拆包用法1
name ,age , height = name_tuple
print(name,age,height)  # body 29 175

# 等价于：(显然拆包方式用的更方便)
name = name_tuple[0]
age = name_tuple[1]
height = name_tuple[2]
print(name,age,height)  # body 29 175

#TODO 拆包用法2
name ,age , height = name_tuple
name ,*other = name_tuple
print(name,other)  # body [29, 175]
