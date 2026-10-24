package id.my.hendisantika.mongodbmultitenancy;

import id.my.hendisantika.mongodbmultitenancy.context.TenantContext;
import id.my.hendisantika.mongodbmultitenancy.domain.Employee;
import id.my.hendisantika.mongodbmultitenancy.exception.TenantNotFoundException;
import id.my.hendisantika.mongodbmultitenancy.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mongodb.MongoDBContainer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Testcontainers
@SpringBootTest
class SpringBootMongodbMultitenancyApplicationTests {

    @Container
    static final MongoDBContainer mongo = new MongoDBContainer("mongo:8.0.4");

    @DynamicPropertySource
    static void tenantProperties(DynamicPropertyRegistry registry) {
        registerTenant(registry, 0, "tenant_a", "tenant_a_db");
        registerTenant(registry, 1, "tenant_b", "tenant_b_db");
    }

    private static void registerTenant(DynamicPropertyRegistry registry, int index, String tenant, String database) {
        final String prefix = "config.multitenant.mongo.properties[" + index + "]";
        registry.add(prefix + ".tenant", () -> tenant);
        registry.add(prefix + ".properties.uri", mongo::getConnectionString);
        registry.add(prefix + ".properties.database", () -> database);
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void deleteAll() {
        for (String tenant : new String[]{"tenant_a", "tenant_b"}) {
            TenantContext.setTenantId(tenant);
            employeeRepository.deleteAll();
        }
        TenantContext.clear();
    }

    @AfterEach
    void clearTenant() {
        TenantContext.clear();
    }

    @Test
    void employeeIsVisibleOnlyToItsOwnTenant() {
        TenantContext.setTenantId("tenant_a");
        final Employee saved = employeeRepository.save(Employee.builder()
                .firstName("ABC")
                .lastName("XYZ")
                .age(30)
                .salary(345455)
                .build());

        assertThat(employeeRepository.findById(saved.getId())).isPresent();
        assertThat(employeeRepository.count()).isEqualTo(1);

        TenantContext.setTenantId("tenant_b");
        assertThat(employeeRepository.findById(saved.getId())).isEmpty();
        assertThat(employeeRepository.count()).isZero();
    }

    @Test
    void unknownTenantIsRejected() {
        TenantContext.setTenantId("tenant_unknown");

        assertThatThrownBy(() -> employeeRepository.count())
                .isInstanceOf(TenantNotFoundException.class);
    }
}
