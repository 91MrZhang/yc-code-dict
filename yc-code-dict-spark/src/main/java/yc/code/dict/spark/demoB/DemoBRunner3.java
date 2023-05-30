package yc.code.dict.spark.demoB;


import org.apache.spark.SparkConf;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.Serializable;
import java.util.Comparator;


/**
 * DemoBRunner
 * <p>
 * RDD和一些常用算子的基本使用
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
public class DemoBRunner3 {


    /*top算子*/
    public static void main(String[] args) {

        SparkConf sparkConf = new SparkConf();
        sparkConf.setMaster("local[*]");

        SparkSession session = SparkSession.builder()
                .config(sparkConf)
                .enableHiveSupport()
                .getOrCreate();

        String sql = "select * from charge_standard.user_test";
        session.sql(sql).toJavaRDD()
                .top(10, new CusComparator())
                .forEach(t -> {
                    Integer salary = t.getAs("salary");
                    System.out.println(salary);
                });
    }

    /* 因为是分布式任务,top算子的比较器需要带序列化的 */
    public static class CusComparator implements Serializable, Comparator<Row> {
        @Override
        public int compare(Row o1, Row o2) {
            Integer salary1 = o1.getAs("salary");
            Integer salary2 = o2.getAs("salary");
            return salary1 - salary2;
        }
    }
}
