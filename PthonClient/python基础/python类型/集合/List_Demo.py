#1.列表是写在方括号 [] 之间、用逗号分隔开的元素列表。
# 2.和字符串一样，列表同样可以被索引和截取，列表被截取后返回一个包含所需元素的新列表。
#    与Python字符串不一样的是，列表中的元素是可以改变的：
# 3.List可以使用 + 操作符进行拼接。
# 4. .列表截取的语法格式如下：
# 变量[头下标:尾下标]  或者 变量[头下标:尾下标:步长]
# 索引值以 0 为开始值，-1 为从末尾的开始位置。


_list = [ 'abcd', 786 , 2.23, 'runoob', 70.2 ]
tinylist = [123, 'runoob']

print("=========list的访问")
print (_list)            # 输出完整列表
print (_list[0])         # 输出列表第一个元素
print (_list[1:3])       # 从第二个开始输出到第三个元素
print (_list[2:])        # 输出从第三个元素开始的所有元素
print (tinylist * 2)    # 输出两次列表

#TODO List可以使用 + 操作符进行拼接。
print (_list + tinylist) # 连接列表  #['abcd', 786, 2.23, 'runoob', 70.2, 123, 'runoob']


# TODO   如果第三个参数(步长)为负数表示逆向读取，以下实例用于翻转字符串：
list2 = [1,2,3,4]

 # list[0]=1, list[1]=2 ，而 -1 表示最后一个元素 list[-1]=4 ( 与 list[3]=4 一样)
print(list2[-1::-1] )  #[4, 3, 2, 1]
print(list2[3::-1] )   #[4, 3, 2, 1]


print("=========list的赋值")
_list[0] = 9
_list[2:5] = [13, 14, 15]
_list[2:5] = []   # 将对应的元素值设置为 []


len = len(_list)
print("len = " + str(len) + "_list = "+str(_list))


print("=========list集合的常用方法")
#把一个元素添加到列表的结尾
_list =[11]
_list.append(30)
print(_list)

#删除列表中值为 x 的第一个元素,如果没有这样的元素，就会返回一个错误。
_list.remove(30)
print(_list)

# 合并两个列表的元素到list ,等价于 list + L
L =[20]
_list.extend(L)
print(_list)

print("====del 语句")
a = [-1, 1, 66.25, 333, 333, 1234.5]

del a[0] #
del a[2:4]
del a[:]
print(str(a))

del a # 这是直接删除这个变量


matrix = [
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9, 10, 11, 12],
]
newList = [[row[i] for row in matrix] for i in range(4)]
print(newList) #[[1, 5, 9], [2, 6, 10], [3, 7, 11], [4, 8, 12]]

newList = [row[i] for row in matrix for i in range(4)]
print(newList) #[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]

newList =[]
for i in range(4) :
    tmpList = []
    for row in matrix:
        tmpList.append(row[i])

    newList.append(tmpList)

print(newList) #[[1, 5, 9], [2, 6, 10], [3, 7, 11], [4, 8, 12]]


newList =[]
for i in range(4) :
    newList.append([row[i] for row in matrix])

print(newList) #[[1, 5, 9], [2, 6, 10], [3, 7, 11], [4, 8, 12]]



