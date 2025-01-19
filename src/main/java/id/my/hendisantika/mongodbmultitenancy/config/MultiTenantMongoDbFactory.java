package id.my.hendisantika.mongodbmultitenancy.config;

import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

/**
 * Created by IntelliJ IDEA.
 * Project : mongodb-multitenancy
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 19/01/25
 * Time: 17.01
 * To change this template use File | Settings | File Templates.
 */
public class MultiTenantMongoDbFactory extends SimpleMongoClientDatabaseFactory {

    public static final String DEFAULT_DB_INSTACE = "test";
    private final MultiTenantMongoConfig multiTenantMongoConfig;
}
