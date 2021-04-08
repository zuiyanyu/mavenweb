#  -*-coding:UTF-8  -*-

# 1） Python 没有单独的字符类型，一个字符就是长度为1的字符串。
# 2） Python 中的字符串有两种索引方式，从左往右以0开始，从右往左以-1开始。
# 3） Python 中的字符串 使用单引号 或者双引号 括起来都行。
name='Runoob'

#1.  +  字符串连接
print(name + '你好')         # 连接字符串
print(str)                 # 输出字符串


#2. *  重复输出字符串
print(name * 2)             # 输出字符串两次


#3. []  索引获取某个字符串
print(name[0])              # 输出字符串第一个字符


#4. [ : ] 截取字符串 ，左闭右开 原则。
print(name[0:-1])           # 输出第一个到倒数第二个的所有字符
print(name[2:5])            # 输出从第三个开始到第五个的字符
print(name[2:])             # 输出从第三个开始后的所有字符
print(name[1:5:2])          # 输出从第二个开始到第五个且每隔两个的字符

#5. in  成员运算符 ，如果字符串中包含给定的字符，返回true .
value = 'b'
if value in name:
    # str( )是python自带函数，是python保留的关键字，定义变量时应该避免使用str作为变量名
    # 否则会报异常： TypeError: 'str' object is not callable
    print("存在:"+ str(value))

# 6. not in  成员运算符 ，如果字符串中不包含给定的字符，返回true .
value ="a"
if value not in name  :
    print("不存在:"+ str(value))

# 7. r/R   原始字符串。  保留字符串的原始面貌
print( r" 原始字符串:\t abc") # 原始字符串:\t abc
print( " 转义字符串:\t abc") #  转义字符串:	 abc

#8. 反斜杠(\) 作用有两个 。 1. 转义字符 。 2. 表示字符串换行
value = "a" \
        "b" \
        "c"
print(value) #abc

# 9. """...""" 或者 '''...''' 跨越多行  . 会保持原始字符串的样貌，但是会进行转义
value ="""a
    b\t c
d"""
print(value)
# 输出样式：
# abc
# a
#     b  c
# d

# r"""...""" 或者 r'''...'''，可以既保持字符串原貌，并且不进行转义
value =r"""a
    b\t c
d"""
print(value)
# 输出样式：
# abc
# a
#     b\t c
# d