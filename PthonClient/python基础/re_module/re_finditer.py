#TODO re.finditer
# 和 findall 类似，在字符串中找到正则表达式所匹配的所有子串，并把它们作为一个迭代器返回。

#TODO 语法
# re.finditer(pattern, string, flags=0)

#TODO 参数：
# 参数	描述
# pattern	匹配的正则表达式
# string	要匹配的字符串。
# flags	标志位，用于控制正则表达式的匹配方式，如：是否区分大小写，多行匹配等等。参见：正则表达式修饰符 - 可选标志

#实例01
import re

#和 findall 类似，在字符串中找到正则表达式所匹配的 所有子串，并把它们作为一个迭代器返回。
matchObjIter = re.finditer(r"\d+", "12a32bc43jf3")
for matchObj in matchObjIter:
    print("matchObj = "+str(matchObj)) # matchObj = <re.Match object; span=(0, 2), match='12'>
    print(matchObj.group())  #12

print("================")
#实例02
value = r"select * from table where age = 20"
parttern = re.compile(r"\s*(?P<ageName>age)\s*=\s*(?P<ageValue>[0-9]+)")
finditerRes = parttern.finditer(value)
for matchedObj in finditerRes :
    resDic = matchedObj.groupdict()
    print("ageName = " + resDic['ageName'])   #ageName = age
    print("ageValue = " + resDic['ageValue']) #ageValue = 20
    print(resDic); #{'ageName': 'age', 'ageValue': '20'}