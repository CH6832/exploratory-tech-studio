package com.example.amazonwebshopclone.integration;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Defines Apache Camel routes for integration purposes.
 */
@Component
public class CamelRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:input?noop=true")
                .to("file:output");
    }
}
