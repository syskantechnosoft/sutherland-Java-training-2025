# ARS Application

## Overview
ARS (Authentication & Role-based Security) application with JWT authentication, H2 console, and Swagger UI access restricted to Admin role only.

## Features
- JWT-based authentication
- Role-based access control (USER, ADMIN)
- H2 console access (Admin only)
- Swagger UI documentation (Admin only)
- Circular dependency resolution

## Getting Started

### Prerequisites
- Java 21
- Maven 3.6+

### Running the Application
```bash
mvn spring-boot:run
```

The application will start on port 8085.

## Authentication

### Sample Users
The application comes with pre-configured users (password: `password123`):

1. **Admin User**
   - Email: `admin@ars.com`
   - Roles: `ROLE_ADMIN`

2. **Regular User**
   - Email: `user@ars.com`
   - Roles: `ROLE_USER`

3. **Multi-role User**
   - Email: `jane@ars.com`
   - Roles: `ROLE_USER,ROLE_ADMIN`

### Getting JWT Token
1. **Login Endpoint**: `POST /auth/login`
   ```json
   {
     "username": "admin@ars.com",
     "password": "password123"
   }
   ```

2. **Use the returned JWT token** in the Authorization header:
   ```
   Authorization: Bearer <your-jwt-token>
   ```

## Admin-Only Access

### H2 Console
- **URL**: http://localhost:8085/h2-console
- **Access**: Admin role only
- **JDBC URL**: `jdbc:h2:mem:arsdb`
- **Username**: `sa`
- **Password**: (leave empty)

### Swagger UI
- **URL**: http://localhost:8085/swagger-ui.html
- **Access**: Admin role only
- **API Docs**: http://localhost:8085/v3/api-docs

## API Endpoints

### Public Endpoints
- `GET /auth/welcome` - Welcome message
- `POST /auth/register` - Register new user
- `POST /auth/login` - User authentication

### User Endpoints (ROLE_USER required)
- `GET /auth/user/**` - User-specific endpoints

### Admin Endpoints (ROLE_ADMIN required)
- `GET /auth/admin/**` - Admin-specific endpoints
- `GET /auth/admin/dashboard` - Admin dashboard
- `GET /auth/admin/tools` - Admin tools links
- `GET /h2-console/**` - H2 database console
- `GET /swagger-ui/**` - Swagger UI documentation

## Database Profiles

### Development Profile (H2)
```bash
mvn spring-boot:run -Dspring.profiles.active=dev
```

### Test Profile (MySQL)
```bash
mvn spring-boot:run -Dspring.profiles.active=test
```

## Security Configuration
- JWT token expiration: 30 minutes
- BCrypt password encoding with strength 12
- Stateless session management
- Frame options enabled for H2 console

## Troubleshooting

### Circular Dependency Resolution
The application resolves circular dependencies by:
1. Moving `PasswordEncoder` to a separate configuration class
2. Using constructor injection instead of field injection
3. Proper dependency ordering in Spring context

### Access Denied Issues
1. Ensure you have a valid JWT token
2. Check that your user has the required role (ADMIN for H2/Swagger)
3. Verify the token is included in the Authorization header

## Development Notes
- H2 console and Swagger UI are restricted to Admin role for security
- JWT tokens must be included in all authenticated requests
- The application uses in-memory H2 database in development mode
- MySQL database is used in test/production profiles