package yc.code.dict.spark.demoC;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;


/**
 * DemoCRunner
 *
 * 线上Spark部署
 * 1、注释掉 sparkConf.setMaster("local[*]");
 * 2、pom文件打包时，相关依赖不要打进去
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
public class DemoCRunner {


    public static void main(String[] args) {

        SparkConf sparkConf = new SparkConf();
        //sparkConf.setMaster("local[*]");

        SparkSession session = SparkSession.builder()
                .config(sparkConf)
                .enableHiveSupport()
                .getOrCreate();

        String sql = "select * from charge_standard.dwd_conf_gantryinfo";

        Dataset<Row> res = session.sql(sql);
        res.show();
    }
}
