package scala集合.Array集合

import scala.collection.mutable.{ArrayBuffer, Builder}

/**
  * 1. 使用可变集合，必须导包 scala.collection.mutable._
  * 2. 可变数组： ArrayBuffer
  *
  */
object ArrayBufferTest {
    def printBuffer[T](buffer:ArrayBuffer[T]): Unit ={
        for (elem <- buffer) {
            print(elem +"\t")
        };println;
    }
    def main(args: Array[String]): Unit = {
//        arrayBufferGetElement
//        arrayBufferAddElement
        arrayBufferAddElement
    }
    //TODO 4. 可变数组的功能
    def arrayBUfferFunction(): Unit ={
        println("======TODO 4. 可变数组的功能========")
        val arrayBuffer =ArrayBuffer[String]("a","b","c")
        //为可变数组添加一个元素到末尾
        arrayBuffer.append("e")
        this.printBuffer(arrayBuffer)

        //使用索引 查询一个元素
        println("arrayBuffer(0)="+arrayBuffer(0))

        //在指定索引位置 插入元素
        arrayBuffer.insert(3,"d")
        this.printBuffer(arrayBuffer);

        //将指定所以位置的元素删除
        arrayBuffer.remove(0)
        this.printBuffer(arrayBuffer)

        //从指定索引位置开始，向后删除指定数量的元素
        arrayBuffer.remove(0,2)
        this.printBuffer(arrayBuffer)

        println("==============")
        // 将一个元素添加到可变数组后生成一个新数组，原始数组元素不变
        //TODO  无论对于可变数组还是非可变数组，  :+ ， +：  都是生成一个新的数组
        val newBuffer = arrayBuffer :+ ("f")

        this.printBuffer(arrayBuffer) //  d	e
        this.printBuffer(newBuffer)  //d	e	f

        val newBuffer2 ="a" +: arrayBuffer
        this.printBuffer(arrayBuffer)//d e
        this.printBuffer(newBuffer2)  // a	d	e

        println(arrayBuffer == newBuffer2)  //false

        println("================")
        //将一个元素添加到当前可变数组中
        arrayBuffer += "f"
        this.printBuffer(arrayBuffer)


        arrayBuffer += "d"
        this.printBuffer(arrayBuffer) // d	e	f	d
        //将可变数组中第一个元素 给删除 (如果数组中有多个重复元素)
        arrayBuffer -= "d"
        this.printBuffer(arrayBuffer)// e	f	d

        //两种方式 修改一个元素的值
        arrayBuffer.update(2,"g");
        this.printBuffer(arrayBuffer)//e	f	g
        arrayBuffer(2)="gg";
        this.printBuffer(arrayBuffer)//e	f	g


        //可变数组的数据操作都是对同一个数组做操作
        val arrayBuffer0: ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 4)
        val ints:ArrayBuffer[Int] = arrayBuffer0 :+ 5
        println(arrayBuffer0==ints) //false

        val arrayBuffer1:ArrayBuffer[Int] = arrayBuffer0 += 5
        println(arrayBuffer0 == ints) // true

    }


    //TODO 3. 可变数组的添加方式
    def arrayBufferAddElement(): Unit ={
        println("======TODO 3. 可变数组的添加方式 =======")
        val buffer =ArrayBuffer[String]()

        //方式1
        buffer.insert(0,"a")

        for (elem <- buffer) {
            print(elem +"\t")
        };println;


        //方式2
        buffer += "b"
        this.printBuffer(buffer)

    }
    //TODO 2. 可变数组的遍历方式
    def arrayBufferGetElement(): Unit ={
        println("===========TODO 2. 可变数组的遍历方式==============")
        val buffer =ArrayBuffer(1,2,3,4)
        //方式1
        for (elem <- buffer) {
            print(elem +"\t")
        };println;


        //方式1
        buffer.foreach((x:Int)=>{print(x+"\t")})
        buffer.foreach((x)=>{print(x+"\t")})
        buffer.foreach(x=>{print(x+"\t")})
        buffer.foreach(x=>print(x+"\t"))

        buffer.foreach(println)

    }
    //TODO 1. 可变数组的声明方式
    def arrayBufferDefine(): Unit ={
        println("=======TODO 1. 可变数组的声明方式==========")
        //声明方式1
        val buffer = new ArrayBuffer[Int]()
        buffer.append();
        buffer.append(1)
        buffer += 2
        println(buffer.toString()) //ArrayBuffer(1, 2)

        //声明方式2
        val builder:Builder[Int, ArrayBuffer[Int]] = ArrayBuffer.newBuilder[Int]
        val buffer2: ArrayBuffer[Int] = builder.result()
        buffer2.append(1)
        buffer2.append(2)
        println(buffer2.toString()) //ArrayBuffer(1, 2)

        //声明方式3  构造器声明
        val buffer3 =  ArrayBuffer.apply[Int](1,2,3,4)
        //简写2
        val buffer3_1 = ArrayBuffer.apply(1,2,3,4)  //
        //简写2
        val buffer3_2 = ArrayBuffer(1,2,3,4);

    }

}
