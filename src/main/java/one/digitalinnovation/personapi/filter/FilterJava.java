package one.digitalinnovation.personapi.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FilterJava implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Enumeration<String> headersNames = req.getHeaderNames();
        Map<String, String> mapHeaders = Collections.list(headersNames)
                .stream()
                .collect(Collectors.toMap(it -> it, req::getHeader));
        if (mapHeaders.get("authorization") != null && mapHeaders.get("authorization").equals("senha")) {
            chain.doFilter(request, response);
        }else{
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendError(403);
        }
    }
}
