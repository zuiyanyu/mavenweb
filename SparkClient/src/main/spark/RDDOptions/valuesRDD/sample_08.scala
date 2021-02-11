package RDDOptions.valuesRDD

/**
  * 作用：以指定的随机种子随机抽样出数量为fraction的数据，withReplacement表示是抽出的数据是否放回，true为有放回的抽样，false为无放回的抽样，seed用于指定随机数生成器种子。
  * withReplacement：是否放回抽样
  * fraction : 元素取舍标准(如果给元素打的分数小于这个数，就不抽样，否则就抽样此元素)
  * seed : 给每个元素打分的算法使用的种子值
  */
object sample_08 extends SC{

    def main(args: Array[String]): Unit = {
        test01
    }
    //TODO 需求：创建一个RDD（1-10），从中选择放回和不放回抽样
    def test01(): Unit ={
        val rdd = sc.parallelize(1 to 10)

        //3）放回抽样（泊松算法）  0.4 表示 放弃40%的数据，抽样60%的数据。
        //数据倾斜的时候，可以用来了解数据结构，来判断倾斜的原因
        var sample1 = rdd.sample(true,0.4,2)
        listPrint( sample1.collect()) // [ 1,2,2 ]  可以看到有2两个重复值 ，元素出现的次数越多，说明此元素可能越多，数据倾斜度越大

        //4)不放回抽样（伯努利算法）
        var sample2 = rdd.sample(false,0.2,3)
        listPrint( sample2.collect())//[ 1,9 ]  抽样结果不会有重复值
    }
}
