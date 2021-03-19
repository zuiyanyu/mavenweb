import sys


pathList = sys.path
for path in pathList :
    print(path)

# TODO 1.一个模块被另一个程序第一次引入时，其主程序将运行。
# TODO 2.如果我们想在模块被引入时，模块中的某一程序块不执行，我们可以用__name__属性来使该程序块仅在该模块自身运行时执行。
if __name__ =="__main__":
    print("__name__.py 模块自身运行！")
else:
    print("模块被其他模块引入的时候执行，__name__ :"+ str(__name__)) # 打印的是 模块的全限定名  python基础.Python导包.__name__
