# SPRING ANNOTATIONS OVERVIEW 

## @Component
- Tells Spring to create and manage an object of this class automatically.
- Spring keeps track of it in the application context so it can be reused anywhere.

- You can give a component a custom name by adding a name inside the annotation, like `@Component("myBean")`.
- This name is used as the bean’s identifier in Spring’s application context.
- It helps when you want to refer to the bean by name (for example, with `@Qualifier`) or avoid name conflicts.
- If you don’t specify a name, Spring uses the default bean name (usually the class name with a lowercase first letter).

## @Autowired
- Tells Spring to automatically connect and inject the required dependencies (objects) into this class.
- Helps avoid manual object creation and wiring.

- Can be used in three ways:
   - Field injection: Directly on a class field (not recommended for testing).
   - Constructor injection: On the constructor — preferred for immutability and easier testing.
   - Setter injection: On a setter method — useful when the dependency is optional or can be changed later.

- Constructor injection is generally recommended because it makes dependencies clear and your classes easier to test.

## @Qualifier
- Used when there are multiple beans of the same type.
- Use `@Qualifier` to specify which Pizza bean to inject when multiple implementations exist

## @Primary
- Marks one bean as the default choice when there are multiple candidates.
- Spring injects this bean unless another is specifically selected.
