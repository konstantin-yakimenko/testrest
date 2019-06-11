package com.testres.testrest.util;

import org.slf4j.MDC;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author konst
 */
public class NumRequestLoggingFilter extends CommonsRequestLoggingFilter {

    private final AtomicLong requestId = new AtomicLong(0L);

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        MDC.put("requestId", String.valueOf(requestId.incrementAndGet()));
        super.beforeRequest(request, message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        String messagePayload = getMessagePayload(request);
        if (messagePayload != null && !messagePayload.isEmpty()) {
            if (messagePayload.contains("password"))
                return;
            logger.debug("After request " + messagePayload.replaceAll("\\r\\n|\\r|\\n", ""));
        } else {
            super.afterRequest(request, message);
        }
    }
}
