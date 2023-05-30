package yc.code.dict.spark.demoB;


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.*;
import java.util.stream.Collectors;


/**
 * DemoBRunner
 * <p>
 * RDD和一些常用算子的基本使用
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
public class DemoBRunner5 {


    /*flatmap算子*/
    //主要是展平操作
    public static void main(String[] args) {

        SparkConf sparkConf = new SparkConf();
        sparkConf.setMaster("local[*]");

        SparkSession session = SparkSession.builder()
                .config(sparkConf)
                .enableHiveSupport()
                .getOrCreate();

        String sql = "select * from charge_standard.user_test";

        //假设100条数据，将一个属性当作key，下面的方法还会返回100条
        JavaPairRDD<Integer, Row> rowsByAgeRdd1 = session.sql(sql).toJavaRDD().mapToPair(t -> new Tuple2<>(t.getAs("age"), t));

        //100条数据，group的话，那会就会远远小于100条，因为有一部分都合并在了Iterable, 所以我们这group在拆一下，体现一下flatmap效果
        JavaPairRDD<Integer, Iterable<Row>> age = session.sql(sql).toJavaRDD().groupBy(t -> t.getAs("age"));
        JavaPairRDD<Integer, Row> rowsByAgeRdd2 = age.flatMapToPair(t -> {
            List<Tuple2<Integer, Row>> list = new ArrayList<>();
            t._2.forEach(x -> list.add(new Tuple2<>(t._1, x)));
            return list.iterator();
        });

    }
}
