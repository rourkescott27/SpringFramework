# SPRING ANNOTATIONS OVERVIEW

## @Component

- Tells Spring to create and manage an object of this class automatically.
- Spring keeps track of it in the application context so it can be reused anywhere.

- You can give a component a custom name by adding a name inside the annotation, like `@Component("myBean")`.
- This name is used as the bean’s identifier in Spring’s application context.
- It helps when you want to refer to the bean by name (for example, with `@Qualifier`) or avoid name conflicts.
- If you don’t specify a name, Spring uses the default bean name (usually the class name with a lowercase first letter).

------------------------------------------------------------------------------------------------------------------------

## @Autowired

- Tells Spring to automatically connect and inject the required dependencies (objects) into this class.
- Helps avoid manual object creation and wiring.

- Can be used in three ways:
    - Field injection: Directly on a class field (not recommended for testing).
    - Constructor injection: On the constructor — preferred for immutability and easier testing.
    - Setter injection: On a setter method — useful when the dependency is optional or can be changed later.

- Constructor injection is generally recommended because it makes dependencies clear and your classes easier to test.

------------------------------------------------------------------------------------------------------------------------

## @Qualifier

- Used when there are multiple beans of the same type.
- Use `@Qualifier` to specify which Pizza bean to inject when multiple implementations exist

------------------------------------------------------------------------------------------------------------------------

## @Primary

- Marks one bean as the default choice when there are multiple candidates.
- Spring injects this bean unless another is specifically selected.

------------------------------------------------------------------------------------------------------------------------

## @Bean

#### What is it?

- `@Bean` is used in Java-based configuration to manually define and register a bean in the Spring container.
- It goes inside a class annotated with `@Configuration`.

#### Bean Naming

- By default, the name of the bean is the method name where `@Bean` is applied.
- You can customize the name using the name attribute: `@Bean(name = "myCustomBean")`

### Java-based vs Annotation-based configuration

#### Annotation-based

- You annotate classes directly with things like `@Component`, `@Service`, etc. Spring automatically detects and
  registers them via component scanning.

#### Java-based

- You use a `@Configuration` class and manually define beans with @Bean methods. This gives more control and
  customization.

### Injecting Dependencies

#### Java-based Config

- You manually pass dependencies as parameters in the method:
  `@Bean
  public UserService userService(EmailService emailService) {
  return new UserService(emailService);
  }`

#### Annotation-based Config

- You use @Autowired to let Spring automatically inject dependencies:
  `@Component
  public class UserService {
  @Autowired
  private EmailService emailService;
  }`

------------------------------------------------------------------------------------------------------------------------

## Stereotype Annotations - @Controller, @Repository, @ Service

- These annotations are all specializations of @Component, meaning:
    - They are all Spring-managed components, but each serves a specific purpose based on where it’s used in the
      application architecture.

- Spring Boot uses stereotype annotations to automatically detect and register classes as beans within specific
  application layers.

| Annotation  | Layer                    | Purpose                         |
|-------------|--------------------------|---------------------------------|
| @Controller | Presentation Layer       | Handles HTTP requests/responses |
| @Service	   | Service (Business) Layer | Contains business logic         |
| @Repository | Persistence (DAO) Layer  | Manages data access logic       |

### @Controller

- Belongs to: Presentation Layer
- Purpose: Handles incoming HTTP requests and maps them to handler methods.
- Typically used with @RequestMapping, @GetMapping, @PostMapping, etc.

- Extra Behavior:
    - Works with Spring MVC.
    - Returns view templates (e.g., Thymeleaf) unless @ResponseBody or @RestController is used.

### @Service

- Belongs to: Service (Business Logic) Layer

- Purpose:
    - Contains the core logic of your application (calculations, rules, orchestration).
    - Acts as a bridge between controllers and repositories.

- Extra Behavior:
    - Can be picked up by Spring AOP for things like transactions, logging, security, etc.

### @Repository

- Belongs to: Persistence (DAO) Layer

- Purpose:
    - Responsible for data access (e.g., database interaction).
    - Encapsulates logic for querying, saving, updating, or deleting data.

- Extra Behavior:
    - Spring wraps method calls and translates persistence-related exceptions into Spring’s DataAccessException.

#### Layer Interaction Flow

- @Controller → @Service → @Repository → Database
- Each layer depends only on the one below it, promoting separation of concerns and testability.

------------------------------------------------------------------------------------------------------------------------

## @Lazy

#### What is @Lazy?

- @Lazy tells Spring to delay the creation of a bean until it’s actually needed (on-demand).
- By default, Spring creates singleton beans eagerly — meaning at application startup.
- With @Lazy, the bean is only created when it is injected, accessed, or requested.

#### Why Use It?

- Speeds up application startup by skipping the creation of unused beans.
- Reduces memory usage if some beans are rarely used or expensive to create.
- Can help resolve circular dependencies in some cases.
- Eager initialization is generally recommended for fail-fast behavior (you find errors early).

#### Where You Can Use @Lazy:

| Usage Location                              | Behavior                         |
|---------------------------------------------|----------------------------------|
| On a @Component class                       | That component is created lazily |
| On a dependency injection (@Autowired)      | That specific injection is lazy  |
| On a @Bean method in a @Configuration class | Only that method's bean is lazy  |
| On an entire @Configuration class           | All beans in that class are lazy |

#### Summary:

- Use @Lazy when startup performance matters, or when beans are rarely used or expensive to initialize.
- Eager (default) -> Bean is created at application startup.
- Lazy (@Lazy)    -> Bean is created only when it’s first accessed or injected.

------------------------------------------------------------------------------------------------------------------------

## @ConfigurationProperties

### What is @ConfigurationProperties?

- @ConfigurationProperties is used to bind external configuration (like values from application.properties or
  application.yml) to a Java object.
- It enables type-safe and structured access to configuration values, allowing developers to group related settings into
  dedicated config classes rather than scattering them throughout the code using individual @Value annotations.

### Why Use It?

- ***Type Safety:*** Automatically converts property values to the correct Java types (e.g., String, int, List, Map,
  etc.).
- ***Cleaner Code:*** Encapsulates related config settings in one class instead of spreading them across multiple fields
  or components.
- ***Supports Complex Structures:*** Works well with nested objects, collections, and maps — great for real-world
  applications with layered configuration.
- ***Validation Support:*** Can be combined with @Validated to enforce constraints (e.g., not null, size limits).

### How It Works Conceptually

- When you annotate a class with @ConfigurationProperties("prefix"), Spring will:
    - Look for configuration keys in the environment (typically application.properties or application.yml) that begin
      with the given prefix.
    - Match the remaining key paths to fields in the class, using naming conventions (e.g., kebab-case config matches
      camelCase fields).
    - Instantiate the class as a Spring-managed bean and populate its fields with the matched values.

### Types of Properties It Supports

- ***Top-Level Properties:*** Basic key-value pairs (e.g., app.name, server.port).
- ***Nested Properties:*** Hierarchical configuration mapped to inner objects or nested field structures.
- ***Map Properties:*** Dynamically structured key-value pairs stored in a Map<K, V>.
- ***List/Array Properties:*** Collections of values bound to Java lists or arrays.

------------------------------------------------------------------------------------------------------------------------