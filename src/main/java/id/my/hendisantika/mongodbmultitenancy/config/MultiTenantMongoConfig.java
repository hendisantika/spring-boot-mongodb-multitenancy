package id.my.hendisantika.mongodbmultitenancy.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * Project : mongodb-multitenancy
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 19/01/25
 * Time: 17.00
 * To change this template use File | Settings | File Templates.
 */
@RequiredArgsConstructor
public class MultiTenantMongoConfig {
    @Getter
    @AllArgsConstructor
    @ToString
    public static class TenantMongoClient {
        private MongoClient mongoClient;
        private String database;
    }

    private final MultiTenantMongoProperties multiTenantMongoProperties;
    private TreeMap<String, TenantMongoClient> multiTenantConfig;

    @PostConstruct
    public void multiTenantMongoConfig() {
        final List<MultiTenantMongoProperties.TenantProperties> multiTenantList = multiTenantMongoProperties.getProperties();
        multiTenantConfig = new TreeMap<>();

        for (final MultiTenantMongoProperties.TenantProperties multiTenant : multiTenantList) {
            final String connectionUri = multiTenant.getProperties().getUri();
            final String host = multiTenant.getProperties().getHost();
            final Integer port = multiTenant.getProperties().getPort();
            MongoClient client;

            if (connectionUri != null) {
                client = MongoClients.create(connectionUri);
            } else if (host != null && port != null) {
                final String connection = "mongodb://" + host + ":" + port + "/";
                client = MongoClients.create(connection);
            } else {
                throw new RuntimeException("At-least one of the config properties is required [uri | host & port]");
            }
            final String database = multiTenant.getProperties().getDatabase();
            final TenantMongoClient tenantMongoClient = new TenantMongoClient(client, database);
            this.multiTenantConfig.put(multiTenant.getTenant(), tenantMongoClient);
        }
    }
}
