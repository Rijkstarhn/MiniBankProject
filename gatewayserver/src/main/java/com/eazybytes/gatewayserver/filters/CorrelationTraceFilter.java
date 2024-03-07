package com.eazybytes.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@Configuration
public class CorrelationTraceFilter {
    private static final Logger logger = LoggerFactory.getLogger(CorrelationTraceFilter.class);

    @Autowired
    FilterUtility filterUtility;

    @Bean
    @Order(1)
    public GlobalFilter correlationIDFilter() {
        return ((exchange, chain) -> {
            logger.debug("The pre global filter is called!");
            HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
            if (isCorrelationIdPresent(requestHeaders)) {
                logger.debug("eazyBank-correlation-id found in RequestTraceFilter : {}",
                        filterUtility.getCorrelationId(requestHeaders));
            } else {
                String correlationID = generateCorrelationId();
                filterUtility.setCorrelationId(exchange, correlationID);
                logger.debug("eazyBank-correlation-id generated in RequestTraceFilter : {}", correlationID);
            }
            return chain.filter(exchange).then(Mono.fromRunnable(
                () -> {
                    String correlationId = filterUtility.getCorrelationId(requestHeaders);
                    logger.debug("Updated the correlation id to the outbound headers: {} in post global filter", correlationId);
                    exchange.getResponse().getHeaders().add(filterUtility.CORRELATION_ID, correlationId);
                }
            ));
        });
    }

    private Boolean isCorrelationIdPresent(HttpHeaders httpHeaders) {
        return filterUtility.getCorrelationId(httpHeaders) != null;
    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }
}
