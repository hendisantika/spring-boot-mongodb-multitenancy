package id.my.hendisantika.mongodbmultitenancy.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : mongodb-multitenancy
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 19/01/25
 * Time: 17.02
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "config.multitenant.mongo")
public class MultiTenantMongoProperties {

    private List<TenantProperties> properties;
}
