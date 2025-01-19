package id.my.hendisantika.mongodbmultitenancy.repository;

import id.my.hendisantika.mongodbmultitenancy.domain.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : mongodb-multitenancy
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 19/01/25
 * Time: 16.54
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
