package sparkSql.sparkSqlApplications.a_商品订单

import org.apache.spark.sql.DataFrame
import sparkSql.sparkSqlApplications.a_商品订单.a_requirement.{createTab, spark}

/**
  * 需求：计算所有订单每年最大金额订单的销售额
  * 需求分析：
  * 方案2：
  * 1. 按订单分组，统计每个订单的销售总额
  * select
  *         a.ordernumber
  * , sum(a.amount) as  amount
  * from tbStockDetail a
  *
  * 2. 结果根据订单id进行关联出年，然后
  * 根据年进行分组，组内进行求最大订单销售额
  *
  * 整合SQL：
  * select
  * c.theyear
  * max(a.amount) as "最大订单售总额"
  * from (
  * select
  *         a.ordernumber
  * , sum(a.amount) as  amount
  * from tbStockDetail a
  * ) a
  * inner join tbStock b on a.ordernumber = b.ordernumber
  * inner join tbDate  c  on b.dateid = c.dateid
  * group by c.theyear,a.ordernumber
  */
object b_requirement  extends Load_Data{
    def main(args: Array[String]): Unit = {
        //TODO 1. 首先注册表临时表
        createTab()
        //TODO 2. 写SQL   as `最大订单售总额`
        var sql =
            """select
              |c.theyear
              |,max(a.amount)  as `最大订单售总额`
              |from (
              |     select
              |        t.ordernumber
              |        , sum(t.amount) as  `amount`
              |     from tbStockDetail t
              |     group by t.ordernumber
              |)a
              |inner join tbStock b on a.ordernumber = b.ordernumber
              |inner join tbDate  c  on b.dateid = c.dateid
              |group by c.theyear """.stripMargin

        //TODO 3.运行sql，得出结果
        val resultDF: DataFrame = spark.sql(sql)
        resultDF.show();

        /**
          * +-------+-------+
          * |theyear|最大订单售总额|
          * +-------+-------+
          * |   2018|    700|
          * |   2019|    300|
          * |   2017|    600|
          * +-------+-------+
          */
    }
 }

