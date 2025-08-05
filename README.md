# Lavoisier API v3

A comprehensive healthcare management system API built with Spring Boot 3, providing secure endpoints for patient management, clinical records, appointments, and medical data tracking.

## 🏥 Features

- **Authentication & Authorization**: JWT-based authentication with role-based access control
- **Patient Management**: Complete CRUD operations for patient records
- **Clinical Records**: Medical history and clinical data management
- **Appointment System**: Schedule and manage patient appointments
- **Anthropometric Data**: Track patient measurements and vital signs
- **Laboratory Results**: Store and manage lab test results
- **Payment Processing**: Handle payment records and transactions
- **File Upload**: Cloudinary integration for secure file storage
- **RESTful API**: Clean, documented REST endpoints
- **Database**: PostgreSQL with JPA/Hibernate ORM
- **Security**: Spring Security with custom JWT implementation

## 🛠️ Technology Stack

- **Java 21**
- **Spring Boot 3.5.4**
- **Spring Security**
- **Spring Data JPA**
- **PostgreSQL**
- **JWT (JSON Web Tokens)**
- **Cloudinary** (File storage)
- **Lombok**
- **Maven**

## 📋 Prerequisites

- Java 21 or higher
- Maven 3.6+
- PostgreSQL 12+
- Cloudinary account (for file uploads)

## 🚀 Quick Start

### 1. Clone the Repository

```bash
git clone <repository-url>
cd lavoisier-api-v3
```

### 2. Environment Setup

Copy the environment example file and configure your settings:

```bash
cp env.example .env
```

Edit the `.env` file with your configuration:

```properties
# Application Configuration
SPRING_APPLICATION_NAME=lavoisier-api-v3

# Database Configuration
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/lavoisier_db
SPRING_DATASOURCE_USERNAME=your_username
SPRING_DATASOURCE_PASSWORD=your_password
SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver

# JPA Configuration
SPRING_JPA_SHOW_SQL=true
SPRING_JPA_HIBERNATE_NAMING_PHYSICAL_STRATEGY=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
SPRING_JPA_HIBERNATE_NAMING_IMPLICIT_STRATEGY=org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl
SPRING_JPA_PROPERTIES_HIBERNATE_GLOBALLY_QUOTED_IDENTIFIERS=true

# JWT Configuration
JWT_SEED=your-super-secret-jwt-key-that-is-at-least-32-characters-long-for-security
JWT_EXPIRATION=3600000

# Cloudinary Configuration
CLOUDINARY_CLOUD_NAME=your-cloudinary-cloud-name
CLOUDINARY_API_KEY=your-cloudinary-api-key
CLOUDINARY_API_SECRET=your-cloudinary-api-secret

# File Upload Configuration
SPRING_SERVLET_MULTIPART_MAX_FILE_SIZE=10MB
SPRING_SERVLET_MULTIPART_MAX_REQUEST_SIZE=10MB

# Server Configuration
SERVER_PORT=8080
```

### 3. Database Setup

Create a PostgreSQL database:

```sql
CREATE DATABASE lavoisier_db;
```

### 4. Build and Run

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## 🔐 Authentication

The API uses JWT-based authentication. All protected endpoints require a valid JWT token in the Authorization header.

### Register a new user:

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Dr. John Doe",
    "telefono": "+1234567890",
    "email": "doctor@example.com",
    "password": "securepassword",
    "rol": "DOCTOR"
  }'
```

### Login:

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "doctor@example.com",
    "password": "securepassword"
  }'
```

Use the returned token in subsequent requests:

```bash
curl -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  http://localhost:8080/api/paciente
```

## 📚 API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - User login

### Patients
- `GET /api/paciente` - List all patients (paginated)
- `GET /api/paciente/{id}` - Get patient by ID
- `POST /api/paciente` - Create new patient
- `PUT /api/paciente/{id}` - Update patient
- `DELETE /api/paciente/{id}` - Delete patient

### Appointments
- `GET /api/cita` - List all appointments
- `GET /api/cita/{id}` - Get appointment by ID
- `POST /api/cita` - Create new appointment
- `PUT /api/cita/{id}` - Update appointment
- `DELETE /api/cita/{id}` - Delete appointment

### Clinical Records
- `GET /api/historial` - List clinical records
- `GET /api/historial/{id}` - Get clinical record by ID
- `POST /api/historial` - Create clinical record
- `PUT /api/historial/{id}` - Update clinical record
- `DELETE /api/historial/{id}` - Delete clinical record

### Anthropometric Data
- `GET /api/antropometria` - List anthropometric records
- `GET /api/antropometria/{id}` - Get anthropometric record by ID
- `POST /api/antropometria` - Create anthropometric record
- `PUT /api/antropometria/{id}` - Update anthropometric record
- `DELETE /api/antropometria/{id}` - Delete anthropometric record

### Laboratory Results
- `GET /api/laboratorial` - List laboratory results
- `GET /api/laboratorial/{id}` - Get laboratory result by ID
- `POST /api/laboratorial` - Create laboratory result
- `PUT /api/laboratorial/{id}` - Update laboratory result
- `DELETE /api/laboratorial/{id}` - Delete laboratory result

### Payments
- `GET /api/pago` - List payments
- `GET /api/pago/{id}` - Get payment by ID
- `POST /api/pago` - Create payment
- `PUT /api/pago/{id}` - Update payment
- `DELETE /api/pago/{id}` - Delete payment

### Staff Management
- `GET /api/personal` - List staff members
- `GET /api/personal/{id}` - Get staff member by ID
- `POST /api/personal` - Create staff member
- `PUT /api/personal/{id}` - Update staff member
- `DELETE /api/personal/{id}` - Delete staff member

## 🏗️ Project Structure

```
src/main/java/com/ccc/projects/lavoisier_api_v3/
├── controllers/          # REST API controllers
├── dto/                  # Data Transfer Objects
├── models/               # JPA entities
├── repositories/         # Data access layer
├── security/             # JWT and security configuration
├── services/             # Business logic layer
└── LavoisierApiV3Application.java
```

## 🔧 Configuration

### Database Configuration
The application uses PostgreSQL with the following default settings:
- Connection pooling with HikariCP
- JPA/Hibernate for ORM
- Automatic schema generation

### Security Configuration
- JWT-based authentication
- Role-based authorization (ADMIN, DOCTOR, NURSE, RECEPTIONIST)
- Password encryption with BCrypt
- CORS configuration for web clients

### File Upload Configuration
- Cloudinary integration for file storage
- Maximum file size: 10MB
- Supported formats: Images, documents

## 🧪 Testing

Run the test suite:

```bash
mvn test
```

## 📦 Deployment

### Docker Deployment

Create a `Dockerfile`:

```dockerfile
FROM openjdk:21-jdk-slim
COPY target/lavoisier-api-v3-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

Build and run:

```bash
docker build -t lavoisier-api .
docker run -p 8080:8080 lavoisier-api
```

### Production Deployment

1. Set up a PostgreSQL database
2. Configure environment variables
3. Build the JAR file: `mvn clean package`
4. Run: `java -jar target/lavoisier-api-v3-0.0.1-SNAPSHOT.jar`

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

For support and questions, please contact the development team or create an issue in the repository.

## 🔄 Version History

- **v3.0.0** - Complete rewrite with Spring Boot 3 and enhanced features
- **v2.x** - Previous versions with Spring Boot 2
- **v1.x** - Initial release

---

**Note**: This is a healthcare application. Ensure compliance with local healthcare data protection regulations (HIPAA, GDPR, etc.) before deploying to production. 