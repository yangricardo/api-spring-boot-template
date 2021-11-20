# API Spring Boot

A sample Spring Boot Api with Spring JPA and Spring Security JWT token and folder structure "best practices" known at this moment. This project is being improved.

## Project Structure

This project suggests a structure that follows some good practices principles, some of them inspired on `Clean Code` and `Angular Framework`. The Base package implementation starts on [src/main/java/com/github/yangricardo/api_spring_boot](./src/main/java/com/github/yangricardo/api_spring_boot/).

```bash
src/main/java/com/github/yangricardo/api_spring_boot
├── ApiSpringBootApplication.java
├── modules
└── shared
```

The Main Class that should start the execution is [ApiSpringBootApplication.java](src/main/java/com/github/yangricardo/api_spring_boot/ApiSpringBootApplication.java). After that, there is two folders structures: `modules` and `shared`.

### Modules Structure

The `modules` contains specific application implementations. For each module, you could follow the structure found at [./modules/module_structure_reference](src/main/java/com/github/yangricardo/api_spring_boot/modules/module_structure_reference/) folder:

```bash
src/main/java/com/github/yangricardo/api_spring_boot/modules/module_structure_reference
├── controllers
├── model
├── repository
└── services
```

> It's not necessary to implement all the suggested modules. You can delete the folders that does not make sense to the module to be implemented

In each folder we should arrange:

1. `model`: All `JPA Entities` and `DTOs (Data Transfer Objects)` classes related to the module.
2. `repository`: All `JPA Entities` and `DTOs (Data Transfer Objects)` classes related to the module.
3. `services`: All classes that implement business logic, like how the data can be saved on the each `Repository` and other support modules
4. `controllers`: All REST Controllers that exposes HTTP API endpoints for services.

> This project disposes an CRUD api for a [`/value`](./src/main/java/com/github/yangricardo/api_spring_boot/modules/value/) resource, that stores only a string value:

```bash
src/main/java/com/github/yangricardo/api_spring_boot/modules/value
├── controllers
│   └── ValueController.java
├── model
│   └── Value.java
├── repository
│   └── IValueRepository.java
└── services
    ├── IValueService.java
    └── ValueServiceImpl.java
```

### Shared Structure

The Shared folder structure contains all generic codes that is someway shared by the specific modules:

```bash
src/main/java/com/github/yangricardo/api_spring_boot/shared/
├── configurations
└── modules
```

#### Shared Modules

The `shared modules` contains:

```bash
src/main/java/com/github/yangricardo/api_spring_boot/shared/modules
├── controllers
│   └── BaseController.java
└── models
    └── BaseModel.java
```

- The [BaseModel](src/main/java/com/github/yangricardo/api_spring_boot/shared/modules/models/BaseModel.java) contains an abstract class that implements some common properties like:

  - `id`: a Long typed column that holds the integer primary key from each record.
  - `uuid`: a UUID typed column that holds the UUID unique key from each record. This also has an associated `hibernate annotation` method that generate this value `on pre persist event`.
  - `createdAt`: a date typed column that holds the Date of creation of the record. This also has an associated `hibernate annotation` method that generate this value `on pre persist event`.
  - `updatedAt`: a date typed column that holds the Date of the last update on the record. This also has an associated `hibernate annotation` method that generate this value `on pre updated event`.

- The [BaseController](src/main/java/com/github/yangricardo/api_spring_boot/shared/modules/controllers/BaseController.java) contains common methods and configurations shared by the specific `RESTControllers`

#### Spring Security Configuration

The authentication of this api is main configured on [SecurityConfiguration](src/main/java/com/github/yangricardo/api_spring_boot/shared/configurations/security/SecurityConfiguration.java) and with `Auth` module implementations:

```bash
src/main/java/com/github/yangricardo/api_spring_boot/modules/auth
├── controllers
│   └── AuthenticationController.java
├── model
│   ├── CreateUserDTO.java
│   ├── LoginDTO.java
│   └── TokenDTO.java
├── repository
└── services
    ├── AuthenticationService.java
    ├── TokenAuthenticationFilterService.java
    └── TokenIssuerService.java
```

The `Auth` module manages how the users are created and authenticated on the API by the services implementations:

- The [AuthenticationService](src/main/java/com/github/yangricardo/api_spring_boot/modules/auth/services/AuthenticationService.java) provides implementations to find the `UserDetails` Spring Security interface by the `UserDetailsService::loadUserByUsername`. Also disposes a `createUser` method to securely create a user and process the hash Password before save an new user record.

- The [TokenIssuerService](src/main/java/com/github/yangricardo/api_spring_boot/modules/auth/services/TokenIssuerService.java) disposes methods for `JWT (JSON Web Tokens)` issue, validation and user metadata identification.

- The [TokenAuthenticationFilterService](src/main/java/com/github/yangricardo/api_spring_boot/modules/auth/services/TokenAuthenticationFilterService.java) implements the `OncePerRequestFilter` that implements the logic for the JWT Authentication Middleware validation.

- The [SecurityConfiguration](src/main/java/com/github/yangricardo/api_spring_boot/shared/configurations/security/SecurityConfiguration.java) implements the `WebSecurityConfigurerAdapter` to configure the `AuthenticationManager` object and `HTTP requests` authorization for route patterns.

> Note that the [AuthenticationService](src/main/java/com/github/yangricardo/api_spring_boot/modules/auth/services/AuthenticationService.java) uses an `Users module`:

```bash
src/main/java/com/github/yangricardo/api_spring_boot/modules/users
├── controllers
├── model
│   ├── AuthorizationProfile.java
│   └── User.java
├── repository
│   └── IUserRepository.java
└── services
    ├── IUserService.java
    └── UserService.java
```

> This is an base implementation for users resources. Note that the [User](src/main/java/com/github/yangricardo/api_spring_boot/modules/users/model/User.java) model implements an `UserDetails` Spring Security interface and has could have many [AuthorizationProfile](src/main/java/com/github/yangricardo/api_spring_boot/modules/users/model/AuthorizationProfile.java) records, that implements the `GrantedAuthority` Spring Security interface in order to implement RBAC functionalities for roles and privileges management.

## Libraries Usage

### [Lombok](https://projectlombok.org)

Recommended usage as it provide annotations that avoid the write of bloated code, as it turns easier to write class without the need to implement constructors, builders, getters and setters methods

## Required Environment

### Java Requirements

- Java 11

### Database Requirements

This project uses `Postgres` as main database for JPA integration. If you use another, update the configuration for integrate with another database like `MySQL` or `SQL Server`.

> This project provides a [docker-compose.yaml](./docker-compose.yaml) with default database

### Docker Requirements

This project provides a [Dockerfile](Dockerfile) and [docker-compose.yaml](./docker-compose.yaml) with default configurations for the dockerization of this api. Also is provided a [shell build script](build.sh) that performs `Maven` and `Docker Image` build executions.

## Development Environment

### Java Installation with ASDF

1. Install the [ASDF](<[asdf-vm.com/#/core-manage-asdf](https://asdf-vm.com/guide/getting-started.html#getting-started)>)

   > Make sure to follow your shell environment instructions, like `bash` or `zsh`

2. Enable the [ASDF plugin for Java](https://github.com/halcyon/asdf-java): `asdf plugin add java`

   > Make sure to follow your shell environment instructions, like `bash` or `zsh`, by setting the [JAVA_HOME](https://github.com/halcyon/asdf-java#java_home)

3. Find a Java version: `asdf list all java`

4. Install a Java version: `asdf install java corretto-11.0.13.8.1`

   > This project is developed using the AWS Java Correto 11 distribution

5. Enable the Java's ASDF installed version globally: `asdf global java corretto-11.0.13.8.1`

### Visual Studio Code

1. Install the [Visual Studio Code](https://code.visualstudio.com)
2. Install the [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
3. Install the [Spring Boot Tools](https://marketplace.visualstudio.com/items?itemName=Pivotal.vscode-spring-boot)
4. Make sure that the environment recognizes your Java Runtime.

   > Sometimes, the ASDF Java installation is not recognized by Visual Studio code. In that case, update the settings.json by going on the `Preferences > Settings` add add or update with the following sample, changing with your path for the JAVA_HOME and Java Runtimes:

   ```json
   {
     "java.configuration.runtimes": [
       {
         "name": "JavaSE-11",
         "path": "/home/kali/.asdf/installs/java/corretto-11.0.13.8.1",
         "default": true
       }
     ],
     "java.home": "/home/kali/.asdf/installs/java/corretto-11.0.13.8.1"
   }
   ```

5. Install [Lombok plugin](https://projectlombok.org/setup/vscode) to improve Lombok annotations usage on code implementation
