package id.my.hendisantika.mongodbmultitenancy.exception;

/**
 * Created by IntelliJ IDEA.
 * Project : mongodb-multitenancy
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 19/01/25
 * Time: 16.53
 * To change this template use File | Settings | File Templates.
 */
public class TenantNotFoundException extends RuntimeException {

    public TenantNotFoundException() {
        super();
    }

    public TenantNotFoundException(String message) {
        super(message);
    }
}
