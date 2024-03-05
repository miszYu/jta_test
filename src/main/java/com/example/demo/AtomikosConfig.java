package com.example.demo;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class AtomikosConfig {


        // 設定DataSource
        @Bean("myjdbc1JtaDataSource")
//        @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.myjdbc")
        public DataSource myjdbc1JtaDataSource() throws SQLException {
            MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
            mysqlXADataSource.setURL("jdbc:mysql://localhost:8080/myjdbc1?serverTimezone=Asia/Taipei&characterEncoding=utf-8");
            mysqlXADataSource.setUser("root");
            mysqlXADataSource.setPassword("springboot");
            mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

            Properties prop = new Properties();
            prop.put("user", "root");
            prop.put("password", "springboot");
            prop.put("url", "jdbc:mysql://localhost:8080/myjdbc1?serverTimezone=Asia/Taipei&characterEncoding=utf-8");

            AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
            atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
            atomikosDataSourceBean.setXaProperties(prop);

            atomikosDataSourceBean.setUniqueResourceName("myjdbc1JtaDataSource");
            atomikosDataSourceBean.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXAConnection");

            atomikosDataSourceBean.setMinPoolSize(3);
            atomikosDataSourceBean.setMaxPoolSize(25);
            atomikosDataSourceBean.setMaxLifetime(20000);
            atomikosDataSourceBean.setBorrowConnectionTimeout(30);

            atomikosDataSourceBean.setLoginTimeout(30);
            atomikosDataSourceBean.setMaintenanceInterval(60);
            atomikosDataSourceBean.setMaxIdleTime(60);

            return atomikosDataSourceBean;
    }

        @Bean("myjdbc1_jta")
        public NamedParameterJdbcTemplate myjdbcJdbcTemplate(
            @Qualifier("myjdbc1JtaDataSource") DataSource dataSource) {
            return new NamedParameterJdbcTemplate(dataSource);
        }

    @Bean("jtaTransactionManager")
    @Primary
    public JtaTransactionManager activitiTransactionManager() throws SystemException {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }

}
