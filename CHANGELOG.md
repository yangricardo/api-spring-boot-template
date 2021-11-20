# Changelog

## 2021.11.20

- Readme project explanation
- Standardization of project for `com.github.yangricardo` base package
- Lombok classes definition improvement
- Serializing operations of Java classes to JSON objects notation

## 2021.11.18

- review source folder structure
- start spring security integration

  - add dependencies
  - enable base security configuration context
  - create auth controller route
  - stateless http security configuration for `/auth` route
  - add spring AuthtenticationManager to handle authentications
  - implement `User` and `AuthorizationProfile` based on spring security models
  - implement IUserReposity following JpaRepository interface
  - implement UserService adapter for IUserRepository
  - implement AuthenticatonService based on spring UserDetailsService
  - IUserRepositiy and UserService update to load user by username
  - Add environment variables for Token Service
  - TokenIssuerService implementation to issue JWT
  - TokenAuthenticationFilter service implementation
  - Add JWT token valiations
  - Fix User authorization profiles
  - add TokenAuthenticationFilterService to security configuration context for HTTP security
  - add authentication manager builder with bcrypt password encoder
  - login route (before signup)
  - signup authentication service
  - login and signup routes
  - update insomnia with auth routes

## 2021.11.17

- Bootstrap project
- spring boot database connection configuration
- Value Model
- Value JPA Repository
- base Value Service
- autowire Value Service with Value Repository integration
- value service
  - find all values
  - create value
  - find by id value
  - update by id value
  - delete by id value
- values controller resource

  - find all values route: `[GET] /api/values`

    - Response Body

      ```json
      [
        {
          "id": 1,
          "value": "teste"
        }
      ]
      ```

  - create value route: `[POST] /api/values`

    - Resquest Body

      ```json
      {
        "value": "teste"
      }
      ```

    - Response Body

      ```json
      {
        "id": 1,
        "value": "teste"
      }
      ```

  - find by id value route: `[GET] /api/values/{id}`

    - Response Body

      ```json
      {
        "id": 1,
        "value": "teste"
      }
      ```

  - update by id value route: `[PATCH] /api/values/{id}`

    - Resquest Body

      ```json
      {
        "value": "teste2"
      }
      ```

    - Response Body

      ```json
      {
        "id": 1,
        "value": "teste2"
      }
      ```

  - delete by id value route: `[DELETE] /api/values/{id}`

- add value model validation rules
  - can not be empty
  - can not be blank
  - the size must varies from 3 to 255 characters
- add Valid annotation to validate request body based on value model validation rules to create and update by id routes
- add PrePersist model event that adds createdAt and updatedAt date
- add PreUpdate model event that update updatedAt date
- base model extraction of Long ID, Date createdAt and Date updatedAt columns
- add UUID unique identifier to base model
- update delete route to improve response on not found resource
- create BaseController handlers
  - badRequestValidationResponseEntity
- improve error handles on update value route
- improve error handles on find by id value route
- improve error handles on create value route
- add spring-fox dependency to generate swagger documentation on `http://localhost:8080/api/swagger-ui/#/`
- add ApiOperation annotations to describe the routes
- api dockerization
- add insomnia json request collection
- cross origin definition on BaseController
