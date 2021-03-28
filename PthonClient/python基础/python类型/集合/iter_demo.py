#TODO 迭代器有两个基本的方法：iter() 和 next()。

#TODO 01 遍历一个值
_list=[1,2,3,4]
_iter = iter(_list)  # 创建迭代器对象
print(next(_iter))  # 输出迭代器的下一个元素

print("=== iter的for 循环")
#方式1：for 循环
_list=[1,2,3,4]
_iter = iter(_list)
for item in _iter:
    print(item)

print("=== iter的while  循环")
#方式2：while 循环
import sys
list=[1,2,3,4]
iter = iter(list)

while True:
    try :
        print(next(iter))
    except StopIteration :
        sys.exit() ;

print("=========03 自定义迭代器")
# 创建一个迭代器
# 把一个类作为一个迭代器使用需要在类中实现两个方法 __iter__() 与 __next__() 。

# __iter__() 方法返回一个特殊的迭代器对象， 这个迭代器对象实现了 __next__() 方法并通过 StopIteration 异常标识迭代的完成。
# __next__() 方法（Python 2 里是 next()）会返回下一个迭代器对象。


# 创建一个返回数字的迭代器，初始值为 1，逐步递增 1：
class MyIter :
    def __iter__ (self):
        # print(' __iter__ 被调用了')
        self.num = 1
        return self

    def __next__(self):
        # print(' __next__ 被调用了')
        if self.num <= 20 :
            curNum = self.num
            self.num += 1
            return curNum
        else :
            raise StopIteration


myIter = MyIter()
iter = iter(myIter)

for ite in myIter :
    print(ite)

