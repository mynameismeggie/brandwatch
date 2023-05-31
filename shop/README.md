# Shop API

> Serves shop business logic

## Technologies Used

- Java `17`
- Spring Boot `3.1.0`
- PostgreSQL `42.6.0`
- Apache Kafka `3.0.7`

## Getting Started

Follow the steps below to get the project up and running on your local machine.

1. Clone the repository:

   ```shell
   git clone https://github.com/your-username/brandwatch.git
   
2. Build the project using Maven

   ```shell 
   ./mvnw clean install

3. Run Kafka and PostgreSQL

    ```shell 
    docker compose -f /infrastructure/docker-compose.yaml -p brandwatch up -d
   
4. Run the Shop application

    ```shell 
   ./mvnw spring-boot:run
