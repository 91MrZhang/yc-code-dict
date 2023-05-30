package yc.code.dict.spark.demoB;


import org.apache.spark.SparkConf;
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
public class DemoBRunner4 {


    /* mapPair算子、reduce算子*/
    //求出年龄分布中，哪个年龄的人数最少
    public static void main(String[] args) {

        SparkConf sparkConf = new SparkConf();
        sparkConf.setMaster("local[*]");

        SparkSession session = SparkSession.builder()
                .config(sparkConf)
                .enableHiveSupport()
                .getOrCreate();

        String sql = "select * from charge_standard.user_test";

        //count方法
        List<Tuple2<Integer, Long>> ageSumList = session.sql(sql).toJavaRDD()
                .mapToPair(row -> new Tuple2<Integer, Long>(row.getAs("age"), 1L))
                .countByKey()
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .map(t -> (new Tuple2<>(t.getKey(), t.getValue())))
                .collect(Collectors.toList());
        System.out.println("min:" + ageSumList.get(0));
        System.out.println("max:" + ageSumList.get(ageSumList.size() - 1));


        List<Tuple2<Integer, Integer>> ageSumList2 = session.sql(sql).toJavaRDD()
                .mapToPair(t -> new Tuple2<Integer, Integer>(t.getAs("age"), 1))
                .reduceByKey(Integer::sum)
                .collect()
                .stream()
                .sorted(Comparator.comparing(t -> t._2))
                .collect(Collectors.toList());
        System.out.println("min:" + ageSumList2.get(0));
        System.out.println("max:" + ageSumList2.get(ageSumList2.size() - 1));
    }

}
