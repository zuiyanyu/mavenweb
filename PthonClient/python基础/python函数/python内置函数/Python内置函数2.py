#TODO 1.  type  判断变量类型   type()不会认为子类是一种父类类型。
a = 111
print(type(a)) # <class 'int'>


#TODO 2. isinstance    isinstance()会认为子类是一种父类类型。
a = 111
tmp = isinstance(a, int)
print(tmp) # True


#TODO 3. isinstance 和 type 的区别在于：
# type()不会认为子类是一种父类类型。
# isinstance()会认为子类是一种父类类型。
class A: pass ;
class B(A): pass ;  # 类B继承了A

print(  type(A()) == A  ) # True
print(  isinstance(A(), A)  ) # True

print(  type(B()) == A  ) # False
print(  isinstance(B(), A)  ) # True

print(A)         # <class '__main__.A'>
print(type(A())) # <class '__main__.A'>
print(type(A))   # <class 'type'>
print(type )     # <class 'type'>
