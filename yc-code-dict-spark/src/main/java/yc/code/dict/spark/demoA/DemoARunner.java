package yc.code.dict.spark.demoA;


import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import yc.code.dict.spark.tool.RandomTool;

import java.util.ArrayList;
import java.util.List;


/**
 * DemoARunner
 * <p>
 * 本地Spark Debug 远程HIVE
 * 1、从CDH平台搞来resources中的几个文件
 * 2、sparkConf.setMaster("local[*]");
 * 3、HIVE建表、插入数据(非分区表)
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
public class DemoARunner {


    public static void main(String[] args) {

        SparkConf sparkConf = new SparkConf();
        sparkConf.setMaster("local[*]");

        SparkSession session = SparkSession.builder()
                .config(sparkConf)
                .enableHiveSupport()
                .getOrCreate();

        String tableCreateSql = "" +
                "CREATE TABLE IF NOT EXISTS " +
                "charge_standard.user_test (" +
                "id string, " +
                "name string, " +
                "age int, " +
                "sex int, " +
                "phone string, " +
                "salary int, " +
                "address string, " +
                "email string, " +
                "rate double, " +
                "createtime string) STORED AS PARQUET";
        //建表
        session.sql(tableCreateSql);
        //插入
        String insertSql = genInsertSql(50000);
        System.out.println(insertSql);
        session.sql(insertSql);
    }

    public static String genInsertSql(int mockNum) {
        List<RandomTool.UserInfo> list = new ArrayList<>();
        for (int i = 0; i < mockNum; i++) {
            list.add(RandomTool.genUserInfo());
        }
        String key = "" +
                "insert into charge_standard.user_test " +
                "values ";
        List<String> insertContent = new ArrayList<>();
        list.forEach(t -> {
            String content = "('{id}','{name}',{age},{sex},'{phone}',{salary},'{address}','{email}',{rate},'{createtime}')";
            content = content.replace("{id}", t.getId()).
                    replace("{name}", t.getName()).
                    replace("{age}", String.valueOf(t.getAge())).
                    replace("{sex}", String.valueOf(t.getSex())).
                    replace("{phone}", t.getPhone()).
                    replace("{salary}", String.valueOf(t.getSalary())).
                    replace("{address}", t.getAddress()).
                    replace("{email}", String.valueOf(t.getEmail())).
                    replace("{rate}", String.valueOf(t.getRate())).
                    replace("{createtime}", t.getCreatetime());
            insertContent.add(content);
        });
        String value = String.join(",", insertContent);
        return key + value;
    }


}
