package com.faculdade.vicioapostas.config;

import com.faculdade.vicioapostas.controller.ApoioSoapService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.xml.ws.Endpoint;

@Configuration
public class SoapConfig {
    
    @Autowired
    private Bus bus;
    
    @Autowired
    private ApoioSoapService apoioSoapService;
    
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, apoioSoapService);
        endpoint.publish("/apoio");
        return endpoint;
    }
}

