package sparkSql.sparkSqlApplications.a_商品订单

import org.apache.spark.sql.DataFrame
import sparkSql.sparkSqlApplications.a_商品订单.a_requirement.{createTab, spark}

object c_requirement  extends Load_Data {
    def main(args: Array[String]): Unit = {
        //TODO 1. 首先注册表临时表
        createTab()
        //TODO 2. 写SQL
        var sql =
            """select
              |t1.*
              |from(
              |     select
              |         c.theyear, a.itemid,
              |         sum(a.amount) as `商品年销售总额`
              |     from tbStockDetail a
              |     inner join tbStock b on a.ordernumber = b.ordernumber
              |     inner join tbDate  c  on b.dateid = c.dateid
              |     group by c.theyear,a.itemid
              |) t1
              |inner join (
              |     select
              |     t1.theyear, max(t1.`商品年销售总额`) as `最大的商品年售总额`
              |     from (
              |          select
              |             c.theyear, a.itemid,
              |             sum(a.amount) as `商品年销售总额`
              |         from tbStockDetail a
              |         inner join tbStock b on a.ordernumber = b.ordernumber
              |         inner join tbDate  c  on b.dateid = c.dateid
              |         group by c.theyear,a.itemid
              |     ) t1
              |     group by t1.theyear
              |) t2
              | on t1.theyear = t2.theyear and t1.`商品年销售总额` = t2.`最大的商品年售总额`
              | """.stripMargin

        //TODO 3.运行sql，得出结果
        val resultDF: DataFrame = spark.sql(sql)
        resultDF.show();

        /**
          * +-------+------+-------+
          * |theyear|itemid|商品年销售总额|
          * +-------+------+-------+
          * |   2018|item16|    700|
          * |   2017|item17|    600|
          * |   2019|item13|    300|
          * +-------+------+-------+
          */


    }
}
