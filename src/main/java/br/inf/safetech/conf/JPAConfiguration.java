package br.inf.safetech.conf;

import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {

	@Value("${database.user}")
	String user;
	@Value("${database.password}")
	String password;
	@Value("${database.host}")
	String host;
	@Value("${database.port}")
	String port;
	@Value("${database.serviceName}")
	String serviceName;	
	
	private static String url; 
	private static final String DRIVER_CLASS_NAME = "oracle.jdbc.OracleDriver";

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws IOException {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(vendorAdapter);

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
			
		
		url = "jdbc:oracle:thin:@" + host + ":" + port + ":" + serviceName;
		System.out.println(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		dataSource.setUrl(url);
		dataSource.setDriverClassName(DRIVER_CLASS_NAME);

		factoryBean.setDataSource(dataSource);

		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		
//		TODO resolver problemas de encoding
// 		props.setProperty("hibernate.connection.characterEncoding", "UTF-8");
//		props.setProperty("hibernate.connection.CharSet", "utf-8");
//		props.setProperty("hibernate.connection.useUnicode", "true");

		factoryBean.setJpaProperties(props);

		factoryBean.setPackagesToScan("br.inf.safetech.model");

		return factoryBean;

	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

}
