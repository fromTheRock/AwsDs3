package com.dad.AwsDs3;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

//@Configuration
//@PropertySource("classpath:application.properties")
//@ConfigurationProperties(prefix = "aws")
public class AwsDs3ConfigurationProp {

	private static AwsDs3ConfigurationProp instance = null;
	public static AwsDs3ConfigurationProp getInstance() {
		if (instance == null) {
			instance = new AwsDs3ConfigurationProp();
			instance.getProperties();
		}
		return instance;
	}

	private String accessKey;
	private String privateKey;
	private String bucketName;


	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
    

	private void getProperties() {
	    try {
	        ClassPathResource resource;
	        resource = new ClassPathResource("config/app.properties");

	        Properties props = PropertiesLoaderUtils.loadProperties(resource);

//	        PropertiesFactoryBean factoryBean;
//	        factoryBean = new PropertiesFactoryBean();
//	        factoryBean.setSingleton(true); // optional depends on your use-case
//	        factoryBean.setLocation(resource);
//	        
//	        Properties properties;
//	        properties = factoryBean.getObject();

	        accessKey = props.getProperty("aws.accessKey");
			privateKey = props.getProperty("aws.privatKey");
			bucketName = props.getProperty("aws.bucketName");

	    } catch (BindException e) {
	        throw new IllegalArgumentException(e);

	    }
	    catch (Exception e) {
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    }
	}

}