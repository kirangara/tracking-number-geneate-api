"# tracking-number-geneate-api" 

# 🚚 Tracking Number Generator API

A scalable and efficient Spring Boot API to generate unique tracking numbers based on shipment metadata. Designed for stateless, containerized deployment using Docker.

---

## 📐 Design Overview

This API provides a unique tracking number for each shipment request using customer and shipment data.

### ✔ Features

- Stateless Spring Boot application
- Unique tracking numbers using SHA-256 + metadata + timestamp
- Regex format: `^[A-Z0-9]{1,16}$`
- Efficient and thread-safe (uses in-memory set for uniqueness)
- Packaged and deployed via Docker

---

## 🧩 Tech Stack

| Component       | Technology         |
|----------------|--------------------|
| Language        | Java 17            |
| Framework       | Spring Boot 3.4.4    |
| Build Tool      | Maven 3.9.9             |
| Deployment      | Docker  28.0.4       |
| Hosting         | Render / Docker Hub|

---

## 📁 Project Structure

tracking-number-app/ ├── src/ │ └── main/java/com/example/tracking/ │ ├── controller/TrackingNumberController.java │ ├── service/TrackingNumberService.java │ ├── util/TrackingNumberGenerator.java │ └── model/TrackingNumberResponse.java ├── Dockerfile ├── pom.xml └── README.md


---

## 🌐 API Endpoint

### `GET /next-tracking-number`

**Query Parameters:**

| Param               | Type     | Example                                     |
|---------------------|----------|---------------------------------------------|
| origin_country_id   | String   | MY                                          |
| destination_country_id | String | ID                                         |
| weight              | Decimal  | 1.234                                       |
| created_at          | DateTime | 2025-04-14T08:00:00+08:00                   |
| customer_id         | UUID     | de619854-b59b-425e-9db4-943979e1bd49        |
| customer_name       | String   | RedBox Logistics                            |
| customer_slug       | String   | redbox-logistics                            |

**Response:**

```json
{
  "tracking_number": "A1B2C3D4E5F6G7H8",
  "created_at": "2025-04-14T08:00:00Z"
}


Docker Deployment:
Dockerfile


Build & Run Locally
Build JAR : mvn clean package
Build Docker Image : docker build -t tracking-number-generate-api .
Run Docker Container : docker run -p 8001:8081 tracking-number-api

test URL:
http://localhost:8001/next-tracking-number?origin_country_id=MY&destination_country_id=ID&weight=1.234&created_at=2025-04-14T15:00:00%2B08:00&customer_id=de619854-b59b-425e-9db4-943979e1bd49&customer_name=RedBox%20Logistics&customer_slug=redbox-logistics


