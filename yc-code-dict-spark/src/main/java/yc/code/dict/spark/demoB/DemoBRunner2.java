package yc.code.dict.spark.demoB;


import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;


/**
 * DemoBRunner
 * <p>
 * RDD和一些常用算子的基本使用
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
public class DemoBRunner2 {


    /*打印算子*/
    public static void main(String[] args) {

        SparkConf sparkConf = new SparkConf();
        sparkConf.setMaster("local[*]");

        SparkSession session = SparkSession.builder()
                .config(sparkConf)
                .enableHiveSupport()
                .getOrCreate();

        String sql = "select * from charge_standard.user_test limit 1";
        session.sql(sql).show();
    }

}
