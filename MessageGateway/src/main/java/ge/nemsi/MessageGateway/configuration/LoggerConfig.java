package ge.nemsi.MessageGateway.configuration;


import jakarta.servlet.*;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Luka Nemsitsveridze
 * @role Sets unique identifier for logging session
 */
@Component
public class LoggerConfig implements Filter {

    private static final String REQUEST_ID = "requestId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            ThreadContext.put(REQUEST_ID, UUID.randomUUID().toString());
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            ThreadContext.remove(REQUEST_ID);
        }
    }
}