package br.inf.safetech.conf;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.inf.safetech.controllers.HomeController;
import br.inf.safetech.daos.UsuarioDAO;

@EnableWebMvc
@ComponentScan(basePackageClasses = {HomeController.class, UsuarioDAO.class})
@EnableTransactionManagement(proxyTargetClass=true)
public class AppWebConfiguration extends WebMvcConfigurerAdapter{

	/**
	 * metodo utilizado pelo spring para obter o resolver
	 * resolver busca os arquivos
	 * @return
	 */
	@Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
       
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");

        return resolver;
    }
	
	
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	} 
	
	
}
