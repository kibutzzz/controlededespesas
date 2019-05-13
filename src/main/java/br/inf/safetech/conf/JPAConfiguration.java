package br.inf.safetech.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {

	private static final String USERNAME = "leonardotmp";
	private static final String PASSWORD = "safetech";
	private static final String HOST = "172.20.1.40";
	private static final String PORT = "1521";
	private static final String SERVICE_NAME = "tsft";
	private static final String URL = "jdbc:oracle:thin:@" + HOST + ":" + PORT + ":" + SERVICE_NAME;
	private static final String DRIVER_CLASS_NAME = "oracle.jdbc.OracleDriver";

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(vendorAdapter);

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		dataSource.setUrl(URL);
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
