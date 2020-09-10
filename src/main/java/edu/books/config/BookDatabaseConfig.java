package edu.books.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"edu.books.repository"}
        ,entityManagerFactoryRef = "bookEntityManager",
        transactionManagerRef = "bookTransactionManager"
)
public class BookDatabaseConfig {

    @Autowired
    private Environment env;
    
    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSourceProperties meatboxDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Primary
    @Bean(name="bookDataSource")
    public DataSource meatboxDataSource() {
        DataSourceProperties meatboxDataSourceProperties = meatboxDataSourceProperties();
        return DataSourceBuilder.create()
                .driverClassName(meatboxDataSourceProperties.getDriverClassName())
                .url(meatboxDataSourceProperties.getUrl())
                .username(meatboxDataSourceProperties.getUsername())
                .password(meatboxDataSourceProperties.getPassword())
                .build();
    }


    @Primary
    @Bean(name ="bookEntityManager")
    public LocalContainerEntityManagerFactoryBean meatboxEntityManager() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(meatboxDataSource());
        factory.setPackagesToScan(new String[]{"edu.books.domain"});
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
        factory.setJpaProperties(jpaProperties);
        return factory;
    }


    @Primary
    @Bean(name = "bookTransactionManager")
    public PlatformTransactionManager meatboxTransactionManager() {
        EntityManagerFactory factory = meatboxEntityManager().getObject();
        return new JpaTransactionManager(factory);
    }
}
