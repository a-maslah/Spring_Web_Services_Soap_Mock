package com.example.demospringws.ws;


import com.sun.org.apache.xerces.internal.xni.grammars.XMLSchemaDescription;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
public class SpringWebServicesConfig {


    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> registrationBean(ApplicationContext context){
         MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();

         messageDispatcherServlet.setApplicationContext(context);
         messageDispatcherServlet.setTransformWsdlLocations(true);
         return new ServletRegistrationBean<>(messageDispatcherServlet,"/ws/*");
    }


    @Bean("banque")
    public DefaultWsdl11Definition defaultWsdl11Definition(){
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("BanqueService");
        definition.setLocationUri("/ws");
        definition.setTargetNamespace(BanqueSoapEndPoint.Name_SPACE);
        definition.setSchema(xsdSchema());
        return definition;
    }


    @Bean
    public XsdSchema xsdSchema(){
        SimpleXsdSchema xsdSchema = new SimpleXsdSchema();
        xsdSchema.setXsd(new ClassPathResource("contract.xsd"));
        return xsdSchema;
    }
}
