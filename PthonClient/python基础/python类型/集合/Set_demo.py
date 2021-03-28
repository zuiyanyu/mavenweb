# 1. 基本功能是进行成员关系测试和删除重复元素。
# 2. 可以使用大括号 { } 或者 set() 函数创建集合，
# 3. 注意：创建一个空集合必须用 set() 而不是 { }，因为 { } 是用来创建一个空字典。

print("=== set的创建")
#方式1：创建一个空set集合
_set = set()

#使用{} 创建一个set集合
_set = {"a","b",3}

_set = set("abcd")
print("_set = "+str(_set)) #_set = {'b', 'c', 'a', 'd'}

print("===set添加元素")
_set.add("fg")
print("_set = "+str(_set)) #_set ={'c', 'b', 'a', 'd', 'fg'}

print("===set的元素访问")


print("===set的成员测试")
# 成员测试
if 'Rose' in _set :
    print('Rose 在集合中')
else :
    print('Rose 不在集合中')

print("===set可以进行集合运算")
# set可以进行集合运算
a = set('abracadabra')
b = set('alacazam')

print(a)
print(a - b)  # a 和 b 的差集
print(a | b)  # a 和 b 的并集
print(a & b)  # a 和 b 的交集
print(a ^ b)  # a 和 b 中不同时存在的元素