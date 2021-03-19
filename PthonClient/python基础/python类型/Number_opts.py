# python中数字有四种类型：整数、布尔型、浮点数和复数。
# int (整数), 如 1, 只有一种整数类型 int，表示为长整型，没有 python2 中的 Long。
# bool (布尔), 如 True。
# float (浮点数), 如 1.23、3E-2
# complex (复数), 如 1 + 2j、 1.1 + 2.2j


#TODO Python可以同时为多个变量赋值
a, b = 1, 2

# TODO 1. 除法
tmp = 2 / 4  # 除法，得到一个浮点数
print(tmp)  #0.5

tmp = 2 // 4  # 除法，得到一个整数
print(tmp) #0

#TODO 2. 取余
tmp = 2 % 4 # 取余
print(tmp)  # 2

#TODO 3. 乘方
tmp = 2 ** 5 # 乘方
print(tmp) #32

#TODO 4. 在混合计算时，Python会把整型转换成为浮点数。
tmp = 1.1 + 2
print(tmp) #3.1