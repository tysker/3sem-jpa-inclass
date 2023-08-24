**1. Why do we need a no-arg constructor in an entity class ?**

Because it often happens that the JPA provider has to instantiate your domain object dynamically. 
It cannot do so, unless there is a no-arg constructor - it can't guess what the arguments should be.

**2. Investigate where we in our code can change the following:**
    - specify the database dialect? - in the buildEntityFactoryConfig() method
    - specify the JDBC connection properties? - in the buildEntityFactoryConfig() method
    - add annotated classes? - the getAnnotationConfiguration(Configuration configuration) method

**3. What is the purpose of the @GeneratedValue annotation and what are the different strategies that can be used to generate primary key values for entities?**

The `@GeneratedValue` annotation in JPA (Java Persistence API) is used to specify how primary key values for entities are generated. There are mainly three strategies you can use with this annotation:

 - **AUTO**: This strategy allows the database to automatically generate a primary key value. The exact behavior depends on the underlying database system. For example, in PostgreSQL, it might use a serial or identity column.
 - **IDENTITY**: This strategy typically relies on database-specific identity columns, such as auto-increment columns in MySQL or identity columns in SQL Server.
 - **SEQUENCE**: This strategy uses a database sequence to generate primary key values. It's common in databases like Oracle.

These strategies determine how the primary key values are assigned to entities when they are persisted in the database.

**4. In Java, we have something called try with resources. What does it do and how can we use it in our code?**

In Java, the "try-with-resources" statement is used to simplify resource management, such as closing files, 
database connections, or network sockets, by automatically closing them when they are no longer needed. It was introduced in Java 7.

**5. What is the difference between persist() and merge() methods in JPA?**

In JPA (Java Persistence API), `persist()` and `merge()` are two methods used for managing entities, but they serve different purposes:

 - **`persist()`**:
    - `persist()` is used to make a transient (newly created) entity persistent, meaning it becomes managed by the JPA EntityManager and is scheduled for insertion into the database.
    - It is typically used for new entities that are not yet associated with the database.
    - If the entity already has an identifier (primary key), calling `persist()` will throw an exception because JPA assumes it's a new entity.
    - It does not return the managed entity; you continue to work with the original instance.

 - **`merge()`**:
    - `merge()` is used to update an existing, detached entity. A detached entity is one that was previously managed by an EntityManager but is no longer in a managed state.
    - It takes the state of the detached entity passed to it, merges it with the current state of the entity in the database, and returns a new managed entity representing the merged state.
    - If there is no corresponding entity in the database with the same identifier, `merge()` will insert a new record and return a managed entity.
    - `merge()` is often used for updating existing database records with new data.

In summary, `persist()` is primarily used for making new entities persistent, while `merge()` is used to update existing entities, and it returns the managed entity representing the updated state. It's important to choose the method that aligns with your intended use case to ensure proper behavior in your JPA application.

**6. What is the difference between the TypedQuery and Query interfaces in JPA?**

In JPA (Java Persistence API), both `TypedQuery` and `Query` are interfaces used for creating and executing queries, but they have some differences in terms of type safety and flexibility:

 - **TypedQuery**:
    - `TypedQuery` is a parameterized interface that allows you to define the type of the result expected from the query. This provides strong type checking at compile-time, reducing the risk of type-related runtime errors.
    - It's typically used when you expect the query to return a specific entity type or a specific Java class as a result.
    - Example:
      ```java
      TypedQuery<MyEntity> query = entityManager.createQuery("SELECT e FROM MyEntity e", MyEntity.class);
      List<MyEntity> resultList = query.getResultList();
      ```

 - **Query**:
    - `Query` is a non-parameterized interface, meaning it doesn't enforce type safety at compile-time. You have to cast the query results to the appropriate types when working with them.
    - It's more flexible in the sense that you can use it for queries that return multiple types of results or when the result type is not known in advance.
    - Example:
      ```java
      Query query = entityManager.createQuery("SELECT e.name, e.age FROM Employee e");
      List<Object[]> resultList = query.getResultList();
      ```

In summary, the main difference is that `TypedQuery` enforces type safety for query results, making it the preferred choice when you know the expected result type in advance, while `Query` provides more flexibility but requires you to handle type casting manually. The choice between them depends on the complexity and type safety requirements of your JPA queries.

**7. What is the difference between the `getSingleResult()` and `getResultList()` methods in JPA?**

In JPA (Java Persistence API), both `getSingleResult()` and `getResultList()` are methods used to retrieve query results, but they differ in how they handle the results:

 - **`getSingleResult()`**:
    - This method is used when you expect your query to return a single result, typically a single entity or a single value (e.g., a count or sum).
    - If the query returns no results, it throws a `NoResultException`.
    - If the query returns more than one result, it throws a `NonUniqueResultException`.
    - It provides type safety when used with `TypedQuery`, ensuring that the result type matches the expected type.
    - Example with a `TypedQuery`:
      ```java
      TypedQuery<MyEntity> query = entityManager.createQuery("SELECT e FROM MyEntity e WHERE e.id = :id", MyEntity.class);
      MyEntity result = query.setParameter("id", entityId).getSingleResult();
      ```

 - **`getResultList()`**:
    - This method is used when your query can return multiple results, such as a list of entities or values.
    - It returns the query results as a list (or an empty list if there are no results) without any exceptions for empty results or multiple results.
    - It's suitable for queries where you expect multiple rows to match your criteria.
    - Example with a `TypedQuery`:
      ```java
      TypedQuery<MyEntity> query = entityManager.createQuery("SELECT e FROM MyEntity e WHERE e.name = :name", MyEntity.class);
      List<MyEntity> resultList = query.setParameter("name", "John").getResultList();
      ```

The getSingleResult() is used when you expect a single result and want to enforce type safety, but it can throw exceptions if 
the result set is empty or contains more than one result. On the other hand, getResultList() is used when you expect multiple 
results and want to retrieve them as a list, without exceptions for empty or multiple results. The choice between them depends 
on the nature of the query and the expectations regarding the result set.

**8. What are the different states of an entity in JPA?**

In JPA (Java Persistence API), entities go through different states during their lifecycle. These states are defined by the JPA specification and are important for managing how entities interact with the database. The main states of an entity in JPA are:

1. **New (Transient)**:
    - An entity is in the "new" or "transient" state when it's first created using the `new` keyword but is not yet associated with a persistence context.
    - It's not managed by the JPA EntityManager, and changes to the entity won't be synchronized with the database until it transitions to the "managed" state.

2. **Managed**:
    - An entity is in the "managed" state when it's associated with an active JPA EntityManager. This typically happens when you persist or retrieve an entity using the EntityManager.
    - Any changes made to a managed entity will be tracked and automatically synchronized with the database when the EntityManager's transaction is committed.

3. **Detached**:
    - An entity transitions to the "detached" state when it was previously managed by an EntityManager, but that EntityManager has been closed or the entity was explicitly detached.
    - In this state, the entity is no longer associated with an EntityManager, and changes to the entity will not be automatically persisted to the database.
    - Detached entities can be reattached to a new EntityManager using `merge()` to make them managed again.

4. **Removed**:
    - An entity is in the "removed" state when you explicitly mark it for removal using the `remove()` method of the EntityManager.
    - After removal, it's no longer managed and will be deleted from the database when the EntityManager's transaction is committed.

5. **Transitional States**:
    - In addition to these primary states, entities can also temporarily enter "transitional" states such as "removing" (after being marked for removal but before the transaction is committed) and "persisting" (after being persisted but before the transaction is committed).

Understanding these entity states is crucial for managing the lifecycle of your JPA entities and ensuring that changes are properly synchronized with the database.