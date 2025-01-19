package id.my.hendisantika.mongodbmultitenancy.config;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by IntelliJ IDEA.
 * Project : mongodb-multitenancy
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 19/01/25
 * Time: 16.59
 * To change this template use File | Settings | File Templates.
 */
@ConditionalOnProperty(value = "config.multitenant.mongo.enabled", havingValue = "true")
@EnableConfigurationProperties(value = MultiTenantMongoProperties.class)
@AutoConfigureBefore(MongoAutoConfiguration.class)
@Configuration
@EnableMongoRepositories(basePackages = {"com.sample"})
@EntityScan(basePackages = {"com.sample"})
public class MultiTenantMongoAutoConfiguration {
    @Bean
    public MultiTenantMongoConfig multiTenantMongoConfig(final MultiTenantMongoProperties multiTenantMongoProperties) {
        return new MultiTenantMongoConfig(multiTenantMongoProperties);
    }
}
