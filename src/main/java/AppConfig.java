package main.java;


import main.java.controllers.HelloWorldController;
import main.java.controllers.HolaWorldController;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class AppConfig {
    public void configure(ServletContext servletContext) {
        ServletRegistration.Dynamic helloWorldController = servletContext.addServlet("HelloWorldController", new HelloWorldController());
        helloWorldController.addMapping("/hello");

        ServletRegistration.Dynamic holaWorldController = servletContext.addServlet("HolaWorldController", new HolaWorldController());
        holaWorldController.addMapping("/hola");
    }
}