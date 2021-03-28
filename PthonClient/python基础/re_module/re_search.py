#TODO re.search
# 1. 函数功能：扫描整个字符串并返回第一个成功的匹配。
# 2. 函数语法：
#       re.search(pattern, string, flags=0)
# 3. 函数参数说明：
# 4.    参数	描述
#       pattern	匹配的正则表达式
#       string	要匹配的字符串。
#       flags	标志位，用于控制正则表达式的匹配方式，如：是否区分大小写，多行匹配等等。参见：正则表达式修饰符 - 可选标志
# 5. 返回结果：匹配成功re.search方法返回一个匹配的对象，否则返回None。
# 6. 结果获取：我们可以使用group(num) 或 groups() 匹配对象函数来获取匹配表达式。
#       匹配对象方法	描述
#       group(num=0)	匹配的整个表达式的字符串，group() 可以一次输入多个组号，在这种情况下它将返回一个包含那些组所对应值的元组。
#       groups()	    返回一个包含所有小组字符串的元组，从 1 到 所含的小组号。
# 7. re.match与re.search的区别
# re.match 只匹配字符串的开始，如果字符串开始不符合正则表达式，则匹配失败，函数返回 None，而 re.search 匹配整个字符串，直到找到一个匹配。

#实例01
import re
#扫描 整个字符串 并返回第一个成功的匹配。
print(re.search('www', 'www.runoob.com').span())  # 在起始位置匹配  (0, 3)
print(re.search('com', 'www.runoob.com').span())  # 不在起始位置匹配 (11, 14)

#实例02
line = "Cats are smarter than dogs"

# .* 表示任意匹配除换行符（\n、\r）之外的任何单个或多个字符
# (.*?) 表示"非贪婪"模式，只保存第一个匹配到的子串
# re.M : 多行匹配，影响 ^ 和 $
# re.I : 使匹配对大小写不敏感
searchObj = re.search(r'(.*) are (.*?) .*', line, re.M | re.I)
if searchObj:
    print("searchObj.group() : ",  searchObj.group()) #searchObj.group() :  Cats are smarter than dogs
    print("searchObj.group(0) : ", searchObj.group())#searchObj.group() :  Cats are smarter than dogs
    print("searchObj.group(1) : ", searchObj.group(1))#searchObj.group(1) :  Cats
    print("searchObj.group(2) : ", searchObj.group(2))#searchObj.group(2) :  smarter
else:
    print("Nothing found!!")