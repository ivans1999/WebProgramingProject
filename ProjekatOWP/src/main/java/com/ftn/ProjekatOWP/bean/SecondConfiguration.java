package com.ftn.ProjekatOWP.bean;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


@Configuration
public class SecondConfiguration implements WebMvcConfigurer{

	
		@Bean(name= {"messageSource"})
	    public ResourceBundleMessageSource messageSource() {

	    	ResourceBundleMessageSource source = new ResourceBundleMessageSource();
	       
	        source.setBasenames("messages/messages");
	      
	        source.setUseCodeAsDefaultMessage(true);
	        source.setDefaultEncoding("UTF-8");
	        
	        source.setDefaultLocale(Locale.ENGLISH);
	        return source;
	    }
		
		
		
		@Bean
		public LocaleResolver localeResolver() {
		    SessionLocaleResolver slr = new SessionLocaleResolver();
		    
		    
		    slr.setDefaultLocale(Locale.forLanguageTag("en"));
		    return slr;
		}
		
		
		@Bean
		public LocaleChangeInterceptor localeChangeInterceptor() {
		    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		    lci.setParamName("locale");
		    return lci;
		}
		
		
		
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
		    registry.addInterceptor(localeChangeInterceptor());
		}
		
}
