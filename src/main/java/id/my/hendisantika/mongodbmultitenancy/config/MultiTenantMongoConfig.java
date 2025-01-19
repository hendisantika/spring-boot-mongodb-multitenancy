package id.my.hendisantika.mongodbmultitenancy.config;

import com.mongodb.client.MongoClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

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
}
