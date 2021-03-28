# TODO findall
#  在字符串中找到正则表达式所匹配的所有子串，并返回一个列表，如果没有找到匹配的，则返回空列表。
#  注意： match 和 search 是匹配一次 findall 匹配所有。

# TODO 语法格式为：
# re.findall(pattern, string, flags=0)
# 或
# pattern = re.compile(pattern,flags) #flags = re.I 等
# pattern.findall(string[, pos[, endpos]])

# TODO 参数：
# pattern 匹配模式。
# string 待匹配的字符串。
# pos 可选参数，指定字符串的起始位置，默认为 0。
# endpos 可选参数，指定字符串的结束位置，默认为字符串的长度。
# 查找字符串中的所有数字：

#实例
import re
#findall  在字符串中找到正则表达式所匹配的所有子串，并返回一个列表，如果没有找到匹配的，则返回空列表。
result1 = re.findall(r'\d+', 'runoob 123 google 456')

pattern = re.compile(r'\d+')  # 查找数字
result2 = pattern.findall('runoob 123 google 456')
result3 = pattern.findall('run88oob123google456', 0, 10) # pattern.findall 还能指定查找的范围

print(result1) #['123', '456']
print(result2)#['123', '456']
print(result3) #['88', '12']
