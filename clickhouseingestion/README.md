# ClickHouse Ingestion Tool (Spring Boot)

A simple bidirectional ingestion tool built with **Spring Boot** that allows:
- Uploading a CSV file and storing the data into a **ClickHouse** database
- Exporting data from ClickHouse into a downloadable **CSV** file
- Manual user insertion and listing via REST API

---

## 🚀 Features

| Functionality             | Endpoint              | Method |
|--------------------------|-----------------------|--------|
| Upload CSV               | `/api/upload`         | POST   |
| Download CSV             | `/api/download`       | GET    |
| Insert user manually     | `/api/users`          | POST   |
| Fetch all users          | `/api/users`          | GET    |

---

## 📦 Setup Instructions

### 1. Prerequisites
- Java 17+
- Maven
- ClickHouse DB (cloud or local)

### 2. Clone & Build

```bash
git clone <your-repo-url>
cd clickhouse-ingestion
mvn clean install
```

### 3. Run the App

```bash
mvn spring-boot:run
```

App runs on: `http://localhost:8080`

---

## ⚙️ Configuration

Edit the following in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:clickhouse://<your-host>:<port>/<database>?ssl=true
spring.datasource.username=default
spring.datasource.password=your-password
```

Example for ClickHouse Cloud:

```properties
spring.datasource.url=jdbc:clickhouse://ln8431ofup.ap-south-1.aws.clickhouse.cloud:8443/default?ssl=true
```

---

## 🧪 Testing

Use **Postman** or `curl`:

### ➕ Upload CSV

```
POST /api/upload
form-data: file=@users.csv
```

### 📤 Export CSV

```
GET /api/download
```

### ➕ Add User

```
POST /api/users?id=1&name=Alice&email=alice@example.com
```

### 📄 List Users

```
GET /api/users
```

---

## 🛠 Table Definition (ClickHouse)

```sql
CREATE TABLE default.users (
    id UInt32,
    name String,
    email String
)
ENGINE = SharedMergeTree('/clickhouse/tables/{uuid}/{shard}', '{replica}')
ORDER BY id
SETTINGS index_granularity = 8192;
```

---

## 🧠 Assumptions & Notes

- CSV columns: `id,name,email`
- All CSV and API actions are mapped to the `users` table
- Assumes ClickHouse is accessible (mocked if not available live)
- Spring Security is disabled

---

## 🗂 Folder Structure

```
src/
└── main/
    ├── java/com/example/clickhouseingestion/
    │   ├── controller/
    │   ├── model/
    │   └── repository/
    └── resources/
        └── application.properties
```

---

## 🤖 Prompts & Assistance Used

See `prompts.txt` for a full list of AI tool prompts used during development.