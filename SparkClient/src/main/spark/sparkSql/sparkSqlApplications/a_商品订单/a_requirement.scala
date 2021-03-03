package sparkSql.sparkSqlApplications.a_商品订单

import org.apache.spark.sql.DataFrame

/**
  * 需求：计算所有订单中每年的销售单数、销售总额
  * 需求分析：
  * 1）按年分组，
  * 2）组内累加每个订单数得到销售总单数
  * 3）组内累加每个订单的售额额得到销售总额
  *
  * 实操演练：
  * 三个表连接后以count(distinct a.ordernumber)计销售单数，sum(b.amount)计销售总额
  */
object a_requirement extends Load_Data{
    def main(args: Array[String]): Unit = {
        //TODO 1. 首先注册表临时表
        createTab()
        //TODO 2. 写SQL
        var sql =
            """select
              |c.theyear
              |,count(distinct a.ordernumber ) as `销售单数`
              |,sum(a.amount) as `销售总额`
              |from tbStockDetail a
              |inner join tbStock b on a.ordernumber = b.ordernumber
              |inner join tbDate  c  on b.dateid = c.dateid
              |group by c.theyear
              |ORDER BY c.theyear
              |""".stripMargin
        //TODO 3.运行sql，得出结果
        val resultDF: DataFrame = spark.sql(sql)
        resultDF.show();
    }


}
