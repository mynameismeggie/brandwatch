# Shop API

> Serves shop business logic

## Technologies Used

- Java `17`
- Spring Boot `3.1.0`
- MySQL `8.0.26`
- RabbitMQ `3.1.0`

## Getting Started

Follow the steps below to get the project up and running on your local machine.

1. Clone the repository:

   ```shell
   git clone https://github.com/your-username/brandwatch.git
   
2. Build the project using Maven

   ```shell 
   ./mvnw clean install

3. Run RabbitMQ and MySQL

    ```shell 
    docker compose -f /infrastructure/docker-compose.yaml -p brandwatch up -d
   
4. Run the Shop application

    ```shell 
   ./mvnw spring-boot:run
