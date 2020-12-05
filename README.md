# Credit

Small poc using : 
- Java 11
- Spring Boot 2.4.0
- Flyway (for migration control) 
- PostgreSQL 13
- Thymeleaf 

## Potential Improvements
- Should we use Enum type in PostgreSQL for the Java Enum type ? 
- Change DB constraints (exp: NOT NULL) to correspond to the Java validation constraints
- Default values should be made (exp: Offer status should be PENDING on creation): programmatically (exp: in service)/ in DB (exp: DEFAULT ...)/ by Hibernate annotation ?
- Refactoring the traductions files for clean organization 
- Refactoring to use "Decorator" pattern instead of flooding thymeleaf templates