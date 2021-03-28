#TODO 1.re 模块使 Python 语言拥有全部的正则表达式功能。
#TODO 2.compile 函数根据一个模式字符串和可选的标志参数生成一个正则表达式对象. 该对象拥有一系列方法用于正则表达式匹配和替换。
#TODO    re 模块也提供了与这些方法功能完全一致的函数，这些函数使用一个模式字符串做为它们的第一个参数。

#TODO re.match函数
#  1.函数功能
#     re.match 尝试从字符串的起始位置匹配一个模式，如果不是起始位置匹配成功的话，match()就返回none。
#  2. 函数语法：
#     re.match(pattern, string, flags=0)
#  3.函数参数说明：
#       参数	    描述
#       pattern	    匹配的正则表达式
#       string	    要匹配的字符串。
#       flags	    标志位，用于控制正则表达式的匹配方式，如：是否区分大小写，多行匹配等等。参见：正则表达式修饰符 - 可选标志
#  4. 函数返回值：  匹配成功re.match方法返回一个匹配的对象，否则返回None。
#  5. 获取匹配的结果：我们可以使用group(num) 或 groups() 匹配对象函数来获取匹配表达式
#  6. 匹配对象方法
#     group(num=0)  ：匹配的整个表达式的字符串，group() 可以一次输入多个组号，在这种情况下它将返回一个包含那些组所对应值的元组。
#     groups()      ：返回一个包含所有小组字符串的元组，从 1 到 所含的小组号。
#

#实例 01
import re
# re.match 尝试从字符串的起始位置匹配一个模式，如果不是起始位置匹配成功的话，match()就返回none。
print(re.match('www', 'www.runoob.com').span())  # 在起始位置匹配  #(0, 3)
print(re.match('com', 'www.runoob.com'))         # 不在起始位置匹配 #None


#实例 02
line = "Cats are smarter than dogs"
# .* 表示任意匹配除换行符（\n、\r）之外的任何单个或多个字符
# (.*?) 表示"非贪婪"模式，只保存第一个匹配到的子串
# re.M : 多行匹配，影响 ^ 和 $
# re.I : 使匹配对大小写不敏感
matchObj = re.match(r'(.*) are (.*?) .*', line, re.M | re.I)

#匹配成功re.match方法返回一个匹配的对象，否则返回None。
if matchObj:
    #我们可以使用group(num) 或 groups() 匹配对象函数来获取匹配表达式
    #group()或group(0) 匹配的整个表达式的字符串
    print("matchObj.group() : ", matchObj.group())   #   matchObj.group() :  Cats are smarter than dogs
    print("matchObj.group(0) : ", matchObj.group(0))  #  matchObj.group(0) :  Cats are smarter than dogs
    print("matchObj.group(1) : ", matchObj.group(1))  #  matchObj.group(1) :  Cats
    print("matchObj.group(2) : ", matchObj.group(2))  #  matchObj.group(2) :  smarter
else:
    print("No match!!")