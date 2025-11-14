package com.example.sparkmysql.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class SparkConfig {

	@Value("${spark.app-name}")
	private String appName;

	@Value("${spark.master}")
	private String master;

	@Value("${spark.mysql.url}")
	private String mysqlUrl;

	@Value("${spark.mysql.user}")
	private String mysqlUser;

	@Value("${spark.mysql.password}")
	private String mysqlPassword;

	@Value("${spark.mysql.driver}")
	private String mysqlDriver;

	@Bean
	public SparkConf sparkConf() {
		return new SparkConf().setAppName(appName).setMaster(master).set("spark.driver.extraJavaOptions",
				"--add-opens=java.base/sun.util.calendar=ALL-UNNAMED " + "--add-opens=java.base/java.util=ALL-UNNAMED "
						+ "--add-opens=java.base/java.lang=ALL-UNNAMED")
				.set("spark.executor.extraJavaOptions",
						"--add-opens=java.base/sun.util.calendar=ALL-UNNAMED "
								+ "--add-opens=java.base/java.util=ALL-UNNAMED "
								+ "--add-opens=java.base/java.lang=ALL-UNNAMED")
				.set("spark.driver.host", "localhost").set("spark.sql.warehouse.dir", "file:///tmp/spark-warehouse-2");
	}

	@Bean(destroyMethod = "close")
	public JavaSparkContext javaSparkContext(SparkConf sparkConf) {
		return new JavaSparkContext(sparkConf);
	}

	@Bean
	public SparkSession sparkSession(JavaSparkContext jsc) {
		return SparkSession.builder().sparkContext(jsc.sc()).getOrCreate();
	}

//    @Bean
//    public SparkSession sparkSession(SparkConf sparkConf) {
//        return SparkSession.builder()
//                .config(sparkConf)
//                .getOrCreate();
//    }

	public String getMysqlUrl() {
		return mysqlUrl;
	}

	public String getMysqlUser() {
		return mysqlUser;
	}

	public String getMysqlPassword() {
		return mysqlPassword;
	}

	public String getMysqlDriver() {
		return mysqlDriver;
	}
}
