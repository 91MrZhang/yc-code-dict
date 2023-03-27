package yc.code.dict.ck.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.jdbc-url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.hikari.auto-commit:true}")
    private Boolean  autoCommit;
    @Value("${spring.datasource.hikari.idle-timeout:60000}")
    private Integer  idleTimeout;
    @Value("${spring.datasource.hikari.connection-timeout:3999999}")
    private Integer  connectionTimeout;
    @Value("${spring.datasource.hikari.max-lifetime:0}")
    private Integer  maxLifetime;
    @Value("${spring.datasource.hikari.minimum-idle:16}")
    private Integer  minimumIdle;
    @Value("${spring.datasource.hikari.maximum-pool-size:32}")
    private Integer  maximumPoolSize;


    @Bean(name = "dataSource")
    public HikariDataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(jdbcUrl);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driverClassName);
        ds.setAutoCommit(autoCommit);
        ds.setIdleTimeout(idleTimeout);
        ds.setConnectionTimeout(connectionTimeout);
        ds.setMaxLifetime(maxLifetime);
        ds.setMinimumIdle(minimumIdle);
        ds.setMaximumPoolSize(maximumPoolSize);
        return ds;
    }


    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate(HikariDataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


}
