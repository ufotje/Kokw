package be.kokw.config;

import be.kokw.config.logging.ExceptionWriter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.StringWriter;

/**
 * Created by Daniel Demesmaecker.
 */

@Configuration
public class AppJavaConfig {

    @Bean
    @Scope("prototype")
    public ExceptionWriter exceptionWriter() {
        return new ExceptionWriter(new StringWriter());
    }
}