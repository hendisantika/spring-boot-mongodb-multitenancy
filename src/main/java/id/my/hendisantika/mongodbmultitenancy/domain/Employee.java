package id.my.hendisantika.mongodbmultitenancy.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Created by IntelliJ IDEA.
 * Project : mongodb-multitenancy
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 19/01/25
 * Time: 16.52
 * To change this template use File | Settings | File Templates.
 */
@Data
@Builder
public class Employee {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private long salary;
}
