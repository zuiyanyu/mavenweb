# 检索和替换
# Python 的re模块提供了re.sub用于替换字符串中的匹配项。

# 语法：
# re.sub(pattern, repl, string, count=0, flags=0)

# 参数：
# pattern : 正则中的模式字符串。
# repl :    TODO 替换的字符串，也可为一个函数。
# string :  要被查找替换的原始字符串。
# count :   模式匹配后替换的最大次数，默认 0 表示替换所有的匹配。
# flags :   编译时用的匹配模式，数字形式。
# 前三个为必选参数，后两个为可选参数。

#TODO 实例01
import re

phone = "2004-959-559 # 这是一个电话号码"

# 删除注释
num = re.sub(r'#.*$', "", phone)
print("电话号码 : ", num) #电话号码 :  2004-959-559

# 移除非数字的内容
num = re.sub(r'\D', "", phone)
print("电话号码 : ", num)  #电话号码 :  2004959559

# TODO 实例01  repl 参数是一个函数
# 将匹配的数字乘于 2
def double(matchedObj):
    # matchedObj = <re.Match object; span=(1, 3), match='23'>
    # matchedObj = <re.Match object; span=(4, 5), match='4'>
    # matchedObj = <re.Match object; span=(8, 11), match='567'>
    print("matchedObj = " + str(matchedObj))

    """
    matchedObj.group(1) = 23
    matchedObj.group(1) = 4
    matchedObj.group(1) = 567
    """
    print("matchedObj.group(1) = " + matchedObj.group(1))
    value = int(matchedObj.group('value')) # 等价于 matchedObj.group(1)
    return str(value * 2)

"""
执行流程：
   1. valueStr 使用正则匹配出结果，获取一个Match object
   2. 将 Match object 传递给函数，函数获取匹配的结果进行处理。
   3. 函数返回值替换这个的匹配结果，得到一个新的字符串。
   4. 继续往下匹配和替换。
"""
valueStr = 'A23G4HFD567'
# ?P<value>  给匹配的结果取一个名字：value
newValue = re.sub('(?P<value>\d+)', double, valueStr)
print("newValue = "+str(newValue)) #newValue = A46G8HFD1134