# TODO re.split
#  split 方法按照能够匹配的子串将字符串分割后返回列表

#TODO 语法：
# re.split(pattern, string[, maxsplit=0, flags=0])

#TODO 参数：
# 参数	描述
# pattern	匹配的正则表达式
# string	要匹配的字符串。
# maxsplit	分隔次数，maxsplit=1 分隔一次，默认为 0，不限制次数。
# flags	标志位，用于控制正则表达式的匹配方式，如：是否区分大小写，多行匹配等等。参见：正则表达式修饰符 - 可选标志

#实例01
import re

#TODO 根据非数字、字母、下划线的字符进行分割
_list = re.split('\W+', 'runoob1； runoob2| runoob3.runoob4.')
print(_list) #['runoob1', 'runoob2', 'runoob3', 'runoob4', '']

# 不理解
_list = re.split('(\W+)', ' runoob1； runoob2| runoob3.runoob4.')
""" ['', ' ', 'runoob1', ', ', 'runoob2', '| ', 'runoob3', '；', 'runoob4', '.', ''] """
print(_list)

_list = re.split('\W+', ' runoob, runoob, runoob.', 1)
print(_list) #['', 'runoob, runoob, runoob.']

# TODO 对于一个找不到匹配的字符串而言，split 不会对其作出分割
_list = re.split('a+', 'hello world')
print(_list) # ['hello world']