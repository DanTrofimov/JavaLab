package freemarker.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LogFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(
            Slf4jLogbackLogger.class);

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        logger.info("current address:  {}", req.getRequestURI());

        chain.doFilter(request, response);
    }

}
