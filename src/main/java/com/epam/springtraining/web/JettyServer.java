package com.epam.springtraining.web;

import com.epam.springtraining.security.SecurityWebInitializer;
import com.epam.springtraining.ws.ServletWebAppInitializer;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.annotations.ClassInheritanceHandler;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.util.ConcurrentHashSet;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.WebApplicationInitializer;

public class JettyServer {

    public static void main(String[] args) throws Exception {

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setResourceBase("resource");
        webAppContext.setConfigurations(new Configuration[] {
                //new WebXmlConfiguration(),
                new AnnotationConfiguration() {
                    @Override
                    public void preConfigure(WebAppContext context) {
                        ClassInheritanceMap map = new ClassInheritanceMap();
                        map.put(WebApplicationInitializer.class.getName(), new ConcurrentHashSet<String>() {{
                            add(WebInitializer.class.getName());
                            add(SecurityWebInitializer.class.getName());
                            add(ServletWebAppInitializer.class.getName());
                        }});
                        context.setAttribute(CLASS_INHERITANCE_MAP, map);
                        _classInheritanceHandler = new ClassInheritanceHandler(map);
                    }
                }
        });

        Server server = new Server(8080);
        server.setHandler(webAppContext);
        server.start();
        server.join();
    }
}