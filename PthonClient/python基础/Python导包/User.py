import sys


#TODO 这个可以认为是python的构造方法中的语句体 ，但是定义的变量又是全局变量
pathList = sys.path
for path in pathList :
    print(path)

#全局 变量
a = 20

def printinfo():
    #局部变量
    a = 30
    print("a = "+ str(a))

# TODO 1.一个模块被另一个程序第一次引入时，其主程序将运行。
# TODO 2.如果我们想在模块被引入时，模块中的某一程序块不执行，我们可以用__name__属性来使该程序块仅在该模块自身运行时执行。
#这个等价 java类中的main方法。
if __name__ =="__main__":
    print("__name__.py 模块自身运行！")
else:
    print("模块被其他模块引入的时候执行，__name__ :"+ str(__name__)) # 打印的是 模块的全限定名  python基础.Python导包.__name__
