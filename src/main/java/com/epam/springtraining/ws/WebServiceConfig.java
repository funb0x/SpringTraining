package com.epam.springtraining.ws;

import com.epam.springtraining.app.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
@ComponentScan("com.epam.springtraining.ws")
@Import(AppConfig.class)
public class WebServiceConfig extends WsConfigurerAdapter {

//    @Bean(name = "SpringTraining")
//    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
//        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
//        wsdl11Definition.setPortTypeName("service");
//        wsdl11Definition.setLocationUri("/ws");
//        wsdl11Definition.setTargetNamespace("http://www.models.beans/SpringTrainingWs");
//        wsdl11Definition.setSchema(schema);
//        return wsdl11Definition;
//    }
//
//    @Bean
//    public XsdSchema schema() {
//        return new SimpleXsdSchema(new ClassPathResource("ws/schema1.xsd"));
//    }
}