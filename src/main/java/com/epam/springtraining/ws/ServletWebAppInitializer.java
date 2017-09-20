package com.epam.springtraining.ws;

import com.epam.springtraining.app.AppConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by Анна on 18.09.2017.
 */
public class ServletWebAppInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(WebServiceConfig.class);

        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setContextClass(AnnotationConfigWebApplicationContext.class);
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);


        ServletRegistration.Dynamic dynamic = servletContext.addServlet(MessageDispatcherServlet.class.getName(), servlet);
        dynamic.addMapping("/ws/*");
    }
}
