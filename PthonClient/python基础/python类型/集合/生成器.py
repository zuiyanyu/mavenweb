import sys

print("=========04 生成器")
#04 生成器
def fibonaci(n):
    a,b,counter = 0,1,0 ;
    while True :  # n是控制循环次数的
        if counter > n :
            return ;
        yield a   #每次循环暂停，然后返回a的值
        a,b,counter = b,a+b,counter+1 ;  #修改 变量值


f = fibonaci(10)  #f 是一个迭代器，由生成器返回生成

#使用方式1
for ite in f :
    print(ite ,end =" ")

#使用方式2
while True :
    try :
        print(next(f),end=" ")
    except StopIteration:
        sys.exit()