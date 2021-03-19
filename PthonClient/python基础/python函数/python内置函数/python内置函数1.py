#有时候，我们需要对数据内置的类型进行转换，数据类型的转换，你只需要将数据类型作为函数名即可。

value ="1+1"
tmp =''
# 1. 将x转换为一个整数
tmp = int(value[0:1])
print(tmp)

#2. 将x转换到一个浮点数
tmp = float(value[0:1])
print(tmp)

# 3. 将对象 x 转换为字符串
tmp = str(12.0)
print(tmp)


#4. 将对象 x 转换为表达式字符串
tmp = repr(value )
print(tmp)

#TODO  5. eval(str)  用来计算在字符串中的有效Python表达式,并返回一个对象. 表达式中可以使用函数。
value ="1+1"
tmp = eval(value )
print(tmp) # 2

value ="float(1+1)"
tmp = eval(value )
print(tmp) # 2.0


#6. tuple( iterable )   将可迭代系列（如列表）转换为元组。
# iterable -- 要转换为元组的可迭代序列。
strVal = "abc"
tmp = tuple(strVal)
print(tmp) #('a', 'b', 'c')

list1= ['Google', 'Taobao', 'Runoob', 'Baidu']
tmp = tuple(list1)
print(tmp) # ('Google', 'Taobao', 'Runoob', 'Baidu')

dictVal = {'runoob': 'runoob.com', 'google': 'google.com'};
tmp = tuple(dictVal) # 将字典的key转为元组
print(tmp) # ('runoob', 'google')


#7. repr(object)  返回一个对象的 string 格式。 等价 str(object)
s = 'RUNOOB'
tmp = repr(s)
print(tmp) # 'RUNOOB'

dictVal = {'runoob': 'runoob.com', 'google': 'google.com'};
tmp = repr(dictVal)
print(tmp) # {'runoob': 'runoob.com', 'google': 'google.com'}
print(str(dictVal))

#8. list( seq ) 方法用于将元组或字符串转换为列表。
print("\n list( seq ) 将序列 seq 转换为一个列表")
strVal = "abc"
tmp = list(strVal)
print(tmp) #['a', 'b', 'c']

list1= ['Google', 'Taobao', 'Runoob', 'Baidu']
tmp = list(list1)
print(tmp) # ['Google', 'Taobao', 'Runoob', 'Baidu']

dictVal = {'runoob': 'runoob.com', 'google': 'google.com'};
tmp = list(dictVal) # 将字典的key转为list
print(tmp) # ['runoob', 'google']

tupleVal = ("a",2,"c")
tmp = list(tupleVal)
print(tmp) # ['a', 2, 'c']



#9.  class set([iterable])   set() 函数创建一个无序不重复元素集，可进行关系测试，删除重复数据，还可以计算交集、差集、并集等。
x = set('runoob')
print(x) # {'b', 'o', 'n', 'r', 'u'}   # 重复的被删除

y = set('google')
print(y) # {'g', 'l', 'e', 'o'}     # 重复的被删除

#  交集
print(x & y) # {'o'}

#  并集
print(x | y) # {'r', 'b', 'n', 'o', 'g', 'u', 'e', 'l'}

#  差集   x中存在，但是y中不存在
print(x - y) # {'n', 'u', 'r', 'b'}


#TODO 10. dict(d) 创建一个字典。d 必须是一个 (key, value)元组序列。
print("\n dict() 的使用")

# class dict(iterable, **kwarg)    iterable -- 可迭代对象。
tmp = dict([('one', 1), ('two', 2), ('three', 3)])# 可迭代对象方式来构造字典
print(str(tmp)) #{'one': 1, 'two': 2, 'three': 3}

# class dict(mapping, **kwarg)   mapping -- 元素的容器。

# class dict(**kwarg)   **kwargs -- 关键字
tmp = dict(a='a', b='b', t='t')     # 传入关键字
print(str(tmp)) # {'a': 'a', 'b': 'b', 't': 't'}


#
#
#
#
#
#