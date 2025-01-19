package id.my.hendisantika.mongodbmultitenancy.controller;

import id.my.hendisantika.mongodbmultitenancy.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : mongodb-multitenancy
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 19/01/25
 * Time: 17.03
 * To change this template use File | Settings | File Templates.
 */
@RestController
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;
}
