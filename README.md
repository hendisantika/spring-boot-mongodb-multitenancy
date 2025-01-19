# spring-boot-mongodb-multitenancy

Multi-Tenancy implementation using Spring Boot + MongoDB

### What is Multi tenancy?

Multi-tenancy is a way/architecture to serve multiple clients using a single instance of software application. Each
client is called a tenant. It means, requests from all tenants will share resources but they won’t have access to other
tenant’s data.

### Multi-Tenancy Models

There are three ways to achieve multi tenancy based on the degree of (physical) separation of the tenant’s data.

1. **Database per Tenant**: Each Tenant has its own database and is isolated from other tenants.
2. **Shared Database**, Shared Schema: All Tenants share a database and. tables. Every table has a Column with the
   Tenant/client Identifier. Every query to database will have tenant/client identifier in it’s where clause.
3. **Shared Database**, Separate Schema: All Tenants share a single database, but have their own schemas and tables.

We will follow approach #1 in this solution.

Problem statement

By default, spring provides capability to connect to only one mongo database instance whose configuration is provided as
below -

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://USER:pwd@localhost:27017/test_db
```

So there is no support in spring which enables user to connect to multiple mongo databases. To achieve this, we need to
override some spring mongo configuration which we will go through below -

Every http request from all tenants will have some information to resolve tenant identifier. We will use http request
header to pass tenant identifier. Let’s write a spring filter extending OncePerRequestFilter class which will extract
tenant identifier from request and set it to TenantContext.

TenantContext is the class which will store tenant information. This class is having a ThreadLocal variable to ensure
that each request/thread has access to its tenant information only. We have used variable of type InheritableThreadLocal
to allow child threads created from the main thread in our application to use same tenantId of the Parent Thread.

Start the application and call the REST API to save employee for tenant_a -

```shell
curl -X POST   http://localhost:8080/api/employees -H 'Content-Type: application/json' -H 'X-Tenant: tenant_a' -d '{"firstName":"ABC","lastName":"XYZ","age":30,"salary":345455}'
```

This should insert employee record for tenant_a.

Now call below API to confirm the behaviour-

```shell
curl -X GET   http://localhost:8080/api/employees/6045fcc00c38b75e8d154933 -H 'Content-Type: application/json' -H 'X-Tenant: tenant_a'
```

This should not return data which we inserted for tenant_a.
