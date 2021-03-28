# TODO 字典是一种映射类型，字典用 { } 标识，它是一个无序的 键(key) : 值(value) 的集合。


#TODO  ================1. 五种构建Dict的方式=====================
#构造方式1：创建空字典使用 { }
dic = {}

#构造方式2：
dic = {'name': 'runoob','code':1, 'site': 'www.runoob.com'}

#构造方式3：构造函数 dict() 直接从键值对元组列表中构建字典。
dic = dict([('Runoob', 1), ('Google', 2), ('Taobao', 3)])
print(dic)  #{'Runoob': 1, 'Google': 2, 'Taobao': 3}

#构造方式4：字典推导可以用来创建任意键和值的表达式词典：
dic = {x: x**2 for x in (2, 4, 6)}
print(type(dic)) #<class 'dict'>
print("方式4："+str(dic)) #{2: 4, 4: 16, 6: 36}

_set = { x**2 for x in (2, 4, 6)}
print(type(_set)) #<class 'set'>
print("_set = "+str(_set))

_tup = (x**2 for x in (2, 4, 6))
print(type(_tup)) #<class 'generator'>
print("_tup = "+str(_tup))#_tup = <generator object <genexpr> at 0x000001C7F5472228>


#构造方式5：如果关键字只是简单的字符串，使用关键字参数指定键值对有时候更方便：
dic =dict(Runoob=1, Google=2, Taobao=3)
print(dic) #{'Runoob': 1, 'Google': 2, 'Taobao': 3}

print(" ================2. Dict的赋值和获取方式=====================")
tinydict = {'name': 'runoob','code':1, 'site': 'www',2:"two"}

#获取某个key对应的value值
print (tinydict['name'])      # 输出键为 'one' 的值    key 为字符串要带引号
print (tinydict[2])           # 输出键为 2 的值
#print (tinydict["2"])        # 异常

#修改某个key的值
tinydict['name'] = "name"
tinydict[2] =  "2"

# 输出完整的字典
print (tinydict)

# TODO 输出所有键
print (tinydict.keys())

# TODO 输出所有值
print (tinydict.values())

print(" ================ . Dict的遍历方式=====================")
knights = {'gallahad': 'the pure', 'robin': 'the brave',2:3}

#先遍历key ，然后根据key获取value
for key in knights :
    print("key = "+str(key) + " ;value = "+ str(knights[key]) +"  keyType =" + str(type(key)))

print(type(knights.items())) #<class 'dict_items'>
#模式匹配，同时匹配出key ，value
for key,value in knights.items() :
    print("key = "+str(key) + " ;value = "+ str(value)  )