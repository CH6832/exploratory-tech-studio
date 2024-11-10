# API Documentation

## Table of Contents

1. [Contracts API](#contracts-api)
   - [Create a Contract](#1-create-a-contract-post-contracts)
   - [Get All Contracts](#2-get-all-contracts-get-contracts)
   - [Get Contract by ID](#3-get-contract-by-id-get-contractsid)
   - [Search Contract by Title](#4-get-contract-by-title-get-contractssearchtitletitle)
   - [Update a Contract](#5-update-a-contract-put-contractsid)
   - [Delete a Contract](#6-delete-a-contract-delete-contractsid)
2. [Logging API](#logging-api)
   - [Log a Message](#1-log-a-message-post-logs)
3. [Payments API](#payments-api)
   - [Process Payment](#1-process-payment-post-apipaymentsprocess)
   - [Get Payment Status](#2-get-payment-status-get-apipaymentspaymentid)
4. [Reports API](#reports-api)
   - [Generate Report](#1-generate-a-report-get-reportsgenerate)
5. [Search API](#search-api)
   - [Fetch All Contracts](#1-fetch-all-contracts-get-searchcontracts)

---

## Contracts API

### 1. **Create a Contract** (`POST /contracts`)

Create a new contract.

#### Request

**URL**: `POST http://localhost:8080/contracts`

**Request Body**:
```json
{
  "title": "Contract 123",
  "description": "This is a sample contract for testing.",
  "status": "Active",
  "parties": [
    { "name": "Party A", "role": "Seller" },
    { "name": "Party B", "role": "Buyer" }
  ],
  "pricing": {
    "baseRate": 1000,
    "currency": "USD",
    "paymentTerms": "Net 30"
  },
  "documents": [
    {
      "filename": "contract_123.pdf",
      "url": "http://example.com/contract_123.pdf",
      "uploadedDate": "2024-11-10T10:00:00Z",
      "version": 1
    }
  ]
}
```

#### Response

**HTTP Status**: `201 Created`

**Response Body**:
```json
{
  "id": "1",
  "title": "Contract 123",
  "description": "This is a sample contract for testing.",
  "status": "Active",
  "parties": [
    { "name": "Party A", "role": "Seller" },
    { "name": "Party B", "role": "Buyer" }
  ],
  "pricing": {
    "baseRate": 1000,
    "currency": "USD",
    "paymentTerms": "Net 30"
  },
  "documents": [
    {
      "filename": "contract_123.pdf",
      "url": "http://example.com/contract_123.pdf",
      "uploadedDate": "2024-11-10T10:00:00Z",
      "version": 1
    }
  ]
}
```

---

### 2. **Get All Contracts** (`GET /contracts`)

Retrieve all contracts.

#### Request

**URL**: `GET http://localhost:8080/contracts`

#### Response

**HTTP Status**: `200 OK`

**Response Body**:
```json
[
  {
    "id": "1",
    "title": "Contract 123",
    "description": "This is a sample contract for testing.",
    "status": "Active",
    "parties": [
      { "name": "Party A", "role": "Seller" },
      { "name": "Party B", "role": "Buyer" }
    ],
    "pricing": {
      "baseRate": 1000,
      "currency": "USD",
      "paymentTerms": "Net 30"
    },
    "documents": [
      {
        "filename": "contract_123.pdf",
        "url": "http://example.com/contract_123.pdf",
        "uploadedDate": "2024-11-10T10:00:00Z",
        "version": 1
      }
    ]
  }
]
```

---

### 3. **Get Contract by ID** (`GET /contracts/{id}`)

Retrieve a specific contract by its ID.

#### Request

**URL**: `GET http://localhost:8080/contracts/{id}`

#### Response

**HTTP Status**: `200 OK`

**Response Body**:
```json
{
  "id": "1",
  "title": "Contract 123",
  "description": "This is a sample contract for testing.",
  "status": "Active",
  "parties": [
    { "name": "Party A", "role": "Seller" },
    { "name": "Party B", "role": "Buyer" }
  ],
  "pricing": {
    "baseRate": 1000,
    "currency": "USD",
    "paymentTerms": "Net 30"
  },
  "documents": [
    {
      "filename": "contract_123.pdf",
      "url": "http://example.com/contract_123.pdf",
      "uploadedDate": "2024-11-10T10:00:00Z",
      "version": 1
    }
  ]
}
```

If the contract is not found:

**HTTP Status**: `404 Not Found`

---

### 4. **Search Contract by Title** (`GET /contracts/search?title={title}`)

Search for a contract by its title.

#### Request

**URL**: `GET http://localhost:8080/contracts/search?title=Contract%20123`

#### Response

**HTTP Status**: `200 OK`

**Response Body**:
```json
{
  "id": "1",
  "title": "Contract 123",
  "description": "This is a sample contract for testing.",
  "status": "Active",
  "parties": [
    { "name": "Party A", "role": "Seller" },
    { "name": "Party B", "role": "Buyer" }
  ],
  "pricing": {
    "baseRate": 1000,
    "currency": "USD",
    "paymentTerms": "Net 30"
  },
  "documents": [
    {
      "filename": "contract_123.pdf",
      "url": "http://example.com/contract_123.pdf",
      "uploadedDate": "2024-11-10T10:00:00Z",
      "version": 1
    }
  ]
}
```

If no contract with the given title exists:

**HTTP Status**: `404 Not Found`

---

### 5. **Update a Contract** (`PUT /contracts/{id}`)

Update an existing contract by ID.

#### Request

**URL**: `PUT http://localhost:8080/contracts/{id}`

**Request Body**:
```json
{
  "title": "Updated Contract 123",
  "description": "This contract has been updated.",
  "status": "Active",
  "parties": [
    { "name": "Party A", "role": "Seller" },
    { "name": "Party B", "role": "Buyer" }
  ],
  "pricing": {
    "baseRate": 1200,
    "currency": "USD",
    "paymentTerms": "Net 30"
  },
  "documents": [
    {
      "filename": "contract_123_updated.pdf",
      "url": "http://example.com/contract_123_updated.pdf",
      "uploadedDate": "2024-11-15T10:00:00Z",
      "version": 2
    }
  ]
}
```

#### Response

**HTTP Status**: `200 OK`

**Response Body**:
```json
{
  "id": "1",
  "title": "Updated Contract 123",
  "description": "This contract has been updated.",
  "status": "Active",
  "parties": [
    { "name": "Party A", "role": "Seller" },
    { "name": "Party B", "role": "Buyer" }
  ],
  "pricing": {
    "baseRate": 1200,
    "currency": "USD",
    "paymentTerms": "Net 30"
  },
  "documents": [
    {
      "filename": "contract_123_updated.pdf",
      "url": "http://example.com/contract_123_updated.pdf",
      "uploadedDate": "2024-11-15T10:00:00Z",
      "version": 2
    }
  ]
}
```

If the contract is not found:

**HTTP Status**: `404 Not Found`

---

### 6. **Delete a Contract** (`DELETE /contracts/{id}`)

Delete an existing contract by its ID.

#### Request

**URL**: `DELETE http://localhost:8080/contracts/{id}`

#### Response

**HTTP Status**: `204 No Content`

If the contract is not found:

**HTTP Status**: `404 Not Found`

---

## Logging API

### 1. **Log a Message** (`POST /logs`)

Send a log message with a specified log level.

#### Request

**URL**: `POST http://localhost:8080/logs`

**Request Body**:
```json
{
  "level": "info",
  "message

Here’s the complete API documentation in Markdown format based on your description. This includes sections for Contract Management, Logging, Payments, and Report Generation APIs.

```markdown
# API Documentation

## Table of Contents

1. [Contracts API](#contracts-api)
   - [Create a Contract](#1-create-a-contract-post-contracts)
   - [Get All Contracts](#2-get-all-contracts-get-contracts)
   - [Get Contract by ID](#3-get-contract-by-id-get-contractsid)
   - [Search Contract by Title](#4-get-contract-by-title-get-contractssearchtitletitle)
   - [Update a Contract](#5-update-a-contract-put-contractsid)
   - [Delete a Contract](#6-delete-a-contract-delete-contractsid)
2. [Logging API](#logging-api)
   - [Log a Message](#1-log-a-message-post-logs)
3. [Payments API](#payments-api)
   - [Process Payment](#1-process-payment-post-apipaymentsprocess)
   - [Get Payment Status](#2-get-payment-status-get-apipaymentspaymentid)
4. [Reports API](#reports-api)
   - [Generate Report](#1-generate-a-report-get-reportsgenerate)
5. [Search API](#search-api)
   - [Fetch All Contracts](#1-fetch-all-contracts-get-searchcontracts)

---

## Contracts API

### 1. **Create a Contract** (`POST /contracts`)

Create a new contract.

#### Request

**URL**: `POST http://localhost:8080/contracts`

**Request Body**:
```json
{
  "title": "Contract 123",
  "description": "This is a sample contract for testing.",
  "status": "Active",
  "parties": [
    { "name": "Party A", "role": "Seller" },
    { "name": "Party B", "role": "Buyer" }
  ],
  "pricing": {
    "baseRate": 1000,
    "currency": "USD",
    "paymentTerms": "Net 30"
  },
  "documents": [
    {
      "filename": "contract_123.pdf",
      "url": "http://example.com/contract_123.pdf",
      "uploadedDate": "2024-11-10T10:00:00Z",
      "version": 1
    }
  ]
}
```

#### Response

**HTTP Status**: `201 Created`

**Response Body**:
```json
{
  "id": "1",
  "title": "Contract 123",
  "description": "This is a sample contract for testing.",
  "status": "Active",
  "parties": [
    { "name": "Party A", "role": "Seller" },
    { "name": "Party B", "role": "Buyer" }
  ],
  "pricing": {
    "baseRate": 1000,
    "currency": "USD",
    "paymentTerms": "Net 30"
  },
  "documents": [
    {
      "filename": "contract_123.pdf",
      "url": "http://example.com/contract_123.pdf",
      "uploadedDate": "2024-11-10T10:00:00Z",
      "version": 1
    }
  ]
}
```

---

### 2. **Get All Contracts** (`GET /contracts`)

Retrieve all contracts.

#### Request

**URL**: `GET http://localhost:8080/contracts`

#### Response

**HTTP Status**: `200 OK`

**Response Body**:
```json
[
  {
    "id": "1",
    "title": "Contract 123",
    "description": "This is a sample contract for testing.",
    "status": "Active",
    "parties": [
      { "name": "Party A", "role": "Seller" },
      { "name": "Party B", "role": "Buyer" }
    ],
    "pricing": {
      "baseRate": 1000,
      "currency": "USD",
      "paymentTerms": "Net 30"
    },
    "documents": [
      {
        "filename": "contract_123.pdf",
        "url": "http://example.com/contract_123.pdf",
        "uploadedDate": "2024-11-10T10:00:00Z",
        "version": 1
      }
    ]
  }
]
```

---

### 3. **Get Contract by ID** (`GET /contracts/{id}`)

Retrieve a specific contract by its ID.

#### Request

**URL**: `GET http://localhost:8080/contracts/{id}`

#### Response

**HTTP Status**: `200 OK`

**Response Body**:
```json
{
  "id": "1",
  "title": "Contract 123",
  "description": "This is a sample contract for testing.",
  "status": "Active",
  "parties": [
    { "name": "Party A", "role": "Seller" },
    { "name": "Party B", "role": "Buyer" }
  ],
  "pricing": {
    "baseRate": 1000,
    "currency": "USD",
    "paymentTerms": "Net 30"
  },
  "documents": [
    {
      "filename": "contract_123.pdf",
      "url": "http://example.com/contract_123.pdf",
      "uploadedDate": "2024-11-10T10:00:00Z",
      "version": 1
    }
  ]
}
```

If the contract is not found:

**HTTP Status**: `404 Not Found`

---

### 4. **Search Contract by Title** (`GET /contracts/search?title={title}`)

Search for a contract by its title.

#### Request

**URL**: `GET http://localhost:8080/contracts/search?title=Contract%20123`

#### Response

**HTTP Status**: `200 OK`

**Response Body**:
```json
{
  "id": "1",
  "title": "Contract 123",
  "description": "This is a sample contract for testing.",
  "status": "Active",
  "parties": [
    { "name": "Party A", "role": "Seller" },
    { "name": "Party B", "role": "Buyer" }
  ],
  "pricing": {
    "baseRate": 1000,
    "currency": "USD",
    "paymentTerms": "Net 30"
  },
  "documents": [
    {
      "filename": "contract_123.pdf",
      "url": "http://example.com/contract_123.pdf",
      "uploadedDate": "2024-11-10T10:00:00Z",
      "version": 1
    }
  ]
}
```

If no contract with the given title exists:

**HTTP Status**: `404 Not Found`

---

### 5. **Update a Contract** (`PUT /contracts/{id}`)

Update an existing contract by ID.

#### Request

**URL**: `PUT http://localhost:8080/contracts/{id}`

**Request Body**:
```json
{
  "title": "Updated Contract 123",
  "description": "This contract has been updated.",
  "status": "Active",
  "parties": [
    { "name": "Party A", "role": "Seller" },
    { "name": "Party B", "role": "Buyer" }
  ],
  "pricing": {
    "baseRate": 1200,
    "currency": "USD",
    "paymentTerms": "Net 30"
  },
  "documents": [
    {
      "filename": "contract_123_updated.pdf",
      "url": "http://example.com/contract_123_updated.pdf",
      "uploadedDate": "2024-11-15T10:00:00Z",
      "version": 2
    }
  ]
}
```

#### Response

**HTTP Status**: `200 OK`

**Response Body**:
```json
{
  "id": "1",
  "title": "Updated Contract 123",
  "description": "This contract has been updated.",
  "status": "Active",
  "parties": [
    { "name": "Party A", "role": "Seller" },
    { "name": "Party B", "role": "Buyer" }
  ],
  "pricing": {
    "baseRate": 1200,
    "currency": "USD",
    "paymentTerms": "Net 30"
  },
  "documents": [
    {
      "filename": "contract_123_updated.pdf",
      "url": "http://example.com/contract_123_updated.pdf",
      "uploadedDate": "2024-11-15T10:00:00Z",
      "version": 2
    }
  ]
}
```

If the contract is not found:

**HTTP Status**: `404 Not Found`

---

### 6. **Delete a Contract** (`DELETE /contracts/{id}`)

Delete an existing contract by its ID.

#### Request

**URL**: `DELETE http://localhost:8080/contracts/{id}`

#### Response

**HTTP Status**: `204 No Content`

If the contract is not found:

**HTTP Status**: `404 Not Found`

---

## Logging API

### 1. **Log a Message** (`POST /logs`)

Send a log message with a specified log level.

#### Request

**URL**: `POST http://localhost:8080/logs`

**Request Body**:
```json
{
  "level": "info",
  "message": "

Here is the continuation of the comprehensive API documentation, including the Logging, Payments, Reports, and Search APIs, as requested:

---

### 1. **Log a Message** (`POST /logs`)

Send a log message with a specified log level.

#### Request

**URL**: `POST http://localhost:8080/logs`

**Request Body**:
```json
{
  "level": "info",
  "message": "Contract 123 created successfully."
}
```

#### Response

**HTTP Status**: `201 Created`

**Response Body**:
```json
{
  "id": "1",
  "level": "info",
  "message": "Contract 123 created successfully.",
  "timestamp": "2024-11-10T10:30:00Z"
}
```

---

## Payments API

### 1. **Process Payment** (`POST /apipayments/process`)

Process a payment for a contract.

#### Request

**URL**: `POST http://localhost:8080/apipayments/process`

**Request Body**:
```json
{
  "contractId": "1",
  "amount": 1000,
  "currency": "USD",
  "paymentMethod": "Credit Card",
  "paymentTerms": "Net 30"
}
```

#### Response

**HTTP Status**: `200 OK`

**Response Body**:
```json
{
  "paymentId": "12345",
  "status": "Processed",
  "amount": 1000,
  "currency": "USD",
  "paymentMethod": "Credit Card",
  "paymentDate": "2024-11-10T10:45:00Z"
}
```

---

### 2. **Get Payment Status** (`GET /apipayments/{paymentId}`)

Get the status of a specific payment.

#### Request

**URL**: `GET http://localhost:8080/apipayments/{paymentId}`

#### Response

**HTTP Status**: `200 OK`

**Response Body**:
```json
{
  "paymentId": "12345",
  "status": "Processed",
  "amount": 1000,
  "currency": "USD",
  "paymentMethod": "Credit Card",
  "paymentDate": "2024-11-10T10:45:00Z"
}
```

If the payment is not found:

**HTTP Status**: `404 Not Found`

---

## Reports API

### 1. **Generate a Report** (`GET /reports/generate`)

Generate a report based on specific filters.

#### Request

**URL**: `GET http://localhost:8080/reports/generate?startDate=2024-01-01&endDate=2024-11-10`

#### Response

**HTTP Status**: `200 OK`

**Response Body**:
```json
{
  "reportId": "report123",
  "generatedDate": "2024-11-10T11:00:00Z",
  "data": [
    {
      "contractId": "1",
      "title": "Contract 123",
      "status": "Active",
      "paymentStatus": "Processed",
      "amountPaid": 1000
    }
  ]
}
```

---

## Search API

### 1. **Fetch All Contracts** (`GET /searchcontracts`)

Search for all contracts without any specific filters.

#### Request

**URL**: `GET http://localhost:8080/searchcontracts`

#### Response

**HTTP Status**: `200 OK`

**Response Body**:
```json
[
  {
    "id": "1",
    "title": "Contract 123",
    "description": "This is a sample contract for testing.",
    "status": "Active",
    "parties": [
      { "name": "Party A", "role": "Seller" },
      { "name": "Party B", "role": "Buyer" }
    ],
    "pricing": {
      "baseRate": 1000,
      "currency": "USD",
      "paymentTerms": "Net 30"
    },
    "documents": [
      {
        "filename": "contract_123.pdf",
        "url": "http://example.com/contract_123.pdf",
        "uploadedDate": "2024-11-10T10:00:00Z",
        "version": 1
      }
    ]
  }
]
```
