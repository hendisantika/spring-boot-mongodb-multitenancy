package id.my.hendisantika.mongodbmultitenancy.config;

import com.mongodb.client.MongoDatabase;
import id.my.hendisantika.mongodbmultitenancy.context.TenantContext;
import id.my.hendisantika.mongodbmultitenancy.exception.TenantNotFoundException;
import org.springframework.dao.DataAccessException;
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

    public MultiTenantMongoDbFactory(final MultiTenantMongoConfig multiTenantMongoConfig, final MultiTenantMongoConfig.TenantMongoClient tenantMongoClient) {
        super(tenantMongoClient.getMongoClient(), tenantMongoClient.getDatabase());
        this.multiTenantMongoConfig = multiTenantMongoConfig;
    }

    @Override
    public MongoDatabase getMongoDatabase() throws DataAccessException {
        final String tenant = TenantContext.getTenantId();
        MongoDatabase database = null;
        if (tenant != null) {
            final MultiTenantMongoConfig.TenantMongoClient tenantMongoClient = multiTenantMongoConfig.getMultiTenantConfig().get(tenant);
            if (tenantMongoClient == null) {
                throw new TenantNotFoundException("Tenant " + tenant + " is not configured");
            }
            database = tenantMongoClient.getMongoClient().getDatabase(tenantMongoClient.getDatabase());
        } else {
            database = getMongoClient().getDatabase(DEFAULT_DB_INSTACE);
        }
        return database;
    }
}
