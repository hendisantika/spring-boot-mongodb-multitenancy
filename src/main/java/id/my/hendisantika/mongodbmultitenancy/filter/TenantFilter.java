package id.my.hendisantika.mongodbmultitenancy.filter;

import id.my.hendisantika.mongodbmultitenancy.context.TenantContext;
import id.my.hendisantika.mongodbmultitenancy.exception.TenantNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * Project : mongodb-multitenancy
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 19/01/25
 * Time: 16.58
 * To change this template use File | Settings | File Templates.
 */
@AllArgsConstructor
@Slf4j
@Component
public class TenantFilter extends OncePerRequestFilter {

    private static final String TENANT_ID_HEADER = "X-Tenant";

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
            throws IOException, ServletException {
        final String tenant = request.getHeader(TENANT_ID_HEADER);

        if (StringUtils.hasText(tenant)) {
            TenantContext.setTenantId(tenant);
        } else {
            throw new TenantNotFoundException("Tenant not found.");
        }
        filterChain.doFilter(request, response);
    }
}
