package id.my.hendisantika.mongodbmultitenancy.service;

import id.my.hendisantika.mongodbmultitenancy.domain.Employee;
import id.my.hendisantika.mongodbmultitenancy.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : mongodb-multitenancy
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 19/01/25
 * Time: 16.55
 * To change this template use File | Settings | File Templates.
 */
@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee findById(final String id) {
        return employeeRepository.findById(id).get();
    }
}
