package com.clinikktv.service;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Enabling the component scan and entity scan of classes in the below mentioned "com.upgrad.quora.service" and "com.upgrad.quora.service.entity" packages respectively.
 */
@Configuration
@ComponentScan("com.clinikktv.service")
@EntityScan("com.clinikktv.service.entity")
@EnableAutoConfiguration
public class ServiceConfiguration {
}
