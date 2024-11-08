# Contract Management System (CMS)

## Overview

The Contract Management System (CMS) is a web-based application developed with Spring Boot and MongoDB to provide centralized contract management for organizations. The system enables users to create, store, track, approve, and retrieve contracts efficiently while maintaining compliance and minimizing operational risks.

## Purpose

The CMS centralizes contract storage and management, promoting transparency, accountability, and efficiency across contract lifecycles. By automating reminders, approvals, and document storage, it helps legal, finance, sales, and procurement teams manage contractual obligations and reduce potential risks.

## Key Users and Roles

1. **Contract Managers**: Create, review, and approve contracts.
2. **Legal Team**: Review terms, approve contracts, and ensure legal compliance.
3. **Procurement**: Manage vendor contracts and track renewals.
4. **Sales Team**: Handle customer agreements and manage renewals.
5. **Finance Team**: Review financial terms and obligations.
6. **Compliance Team**: Ensure regulatory compliance across contracts.
7. **Administrators**: Manage user roles, permissions, and system settings.

## Functional Requirements

### 1. Contract Management
   - **Create, edit, and delete contracts** with detailed fields.
   - Support for multiple **contract types** (e.g., Service Agreement, NDA).
   - Include **multiple parties** per contract with roles (e.g., Client, Provider).
   - **Contract fields**:
     - Title, Description
     - Parties and Contact Information
     - Status (Pending, Active, Expired)
     - Important Dates (Start Date, End Date, Renewal Date)
     - Terms (Scope, Pricing, Payment Terms, Termination Conditions)

### 2. Document Storage and Versioning
   - **Upload** contract documents in PDF or Word formats.
   - Maintain **version history** of documents for each contract.
   - **Retrieve and view** specific document versions as needed.

### 3. Approval Workflow
   - Multi-step **approval workflow** with custom roles.
   - Status tracking of approvals (Pending, Approved, Rejected).
   - Option to **reassign approvals** if necessary.

### 4. Notifications and Reminders
   - Automated **reminders** for contract renewals, expirations, and approvals.
   - Customizable **notification preferences** per contract.
   - **Notification channels**: Email, in-app alerts.

### 5. Audit Trail and Logging
   - **Detailed audit log** of actions taken on each contract, including:
     - User actions (creation, approval, editing)
     - Date and time of each action
     - Changes made to the contract
   - **Log export options** for compliance and auditing.

### 6. Search and Filter Capabilities
   - **Advanced search** by attributes like contract title, party name, status, or date range.
   - Filters for specific criteria, such as expiring soon, pending approvals, or active contracts.
   - **Full-text search** across contract contents and terms.

### 7. Role-Based Access Control (RBAC)
   - Define **permissions** based on roles (e.g., create, view-only, approve).
   - Administrative controls for **managing users and access rights**.

### 8. Dashboard and Reporting
   - A user-friendly **dashboard** with a summary of:
     - Total active contracts
     - Contracts expiring soon
     - Contracts awaiting approval
   - **Reports** on contract statuses, renewal timelines, and audit logs.
   - **Export options** for reports (e.g., CSV, PDF) for further analysis.

### 9. API for External Integrations
   - REST API for integration with external systems (e.g., ERP, CRM).
   - Authentication for API access with tokens or OAuth2.
   - Expose endpoints for:
     - Contract CRUD operations
     - Approval workflows
     - Notifications and reminders

## Non-Functional Requirements

### 1. Performance
   - The system should handle **up to 10,000 contracts** efficiently.
   - **Fast response times**: Queries should return results within 2 seconds.

### 2. Security
   - **Data encryption** for sensitive fields.
   - Secure **authentication and authorization**.
   - **Audit trail** compliance with industry standards (GDPR, etc.).

### 3. Scalability
   - Support for **horizontal scaling** as the contract volume grows.
   - Cloud-compatible for deployment on services like AWS or Azure.

### 4. Availability and Reliability
   - Ensure **99.9% uptime** for high availability.
   - Regular **backups** of data and documents.

### 5. Compliance
   - Meet data protection regulations (e.g., GDPR).
   - **Data retention policies** and deletion requests.

## Database Design (MongoDB Schema)

### Collection: `contracts`

Each document represents a single contract with nested structures for parties, approvals, and document versions.

```json
{
    "_id": "ObjectId",
    "title": "string",
    "description": "string",
    "parties": [
        {
            "party_name": "string",
            "role": "string",
            "contact_details": {
                "email": "string",
                "phone": "string"
            }
        }
    ],
    "status": "string",
    "dates": {
        "start_date": "date",
        "end_date": "date",
        "signing_date": "date",
        "last_updated": "date"
    },
    "terms": {
        "scope": "string",
        "pricing": {
            "base_rate": "number",
            "currency": "string",
            "payment_terms": "string"
        },
        "termination": {
            "notice_period": "string",
            "termination_fee": "number"
        }
    },
    "approvals": [
        {
            "approver": "string",
            "role": "string",
            "approved_date": "date",
            "status": "string"
        }
    ],
    "documents": [
        {
            "filename": "string",
            "url": "string",
            "uploaded_date": "date",
            "version": "number"
        }
    ],
    "audit_log": [
        {
            "action": "string",
            "user": "string",
            "timestamp": "date"
        }
    ],
    "notifications": [
        {
            "type": "string",
            "send_date": "date",
            "status": "string"
        }
    ]
}
```

### Additional Considerations

1. **Indexing**: Index fields like `status`, `dates.end_date`, and `parties.party_name` for efficient querying.
2. **Full-Text Search**: Implement MongoDB full-text search on text-heavy fields like `terms`.
3. **Data Validation**: Use JSON Schema validation to enforce structure (e.g., mandatory `title` and `start_date`).
4. **Encryption**: Encrypt sensitive fields, particularly `terms` and `party contact details`.

## Initial API Endpoints

1. **POST /contracts** - Create a new contract.
2. **GET /contracts/{id}** - Retrieve a specific contract by ID.
3. **PUT /contracts/{id}** - Update a contract by ID.
4. **DELETE /contracts/{id}** - Delete a contract by ID.
5. **GET /contracts** - Retrieve all contracts, with optional filters.
6. **POST /contracts/{id}/approve** - Submit an approval.
7. **POST /contracts/{id}/upload-document** - Upload a document.
8. **GET /contracts/search** - Search functionality.

## Future Enhancements

1. **Integration with Digital Signature Providers** like DocuSign for e-signatures.
2. **AI/ML Capabilities**: Extract and categorize contract terms automatically.
3. **Mobile App Support** for accessing contracts remotely.

## Technology Tools

   **Backend**: Spring Boot framework to build RESTful services.
   - **SpringBoot**
       - **Project**: Maven
       - **Language**: Java 21
       - **Spring Boot**: 3.3.5
       - **Packaging**: Jar
 
   - **Spring Data JPA**: For ORM and database interactions.
   - **Spring Security**: For user authentication, authorization, and role-based access control.
   - **Spring Batch or Quartz**: For scheduling recurring tasks like sending notifications.
   - **OpenAPI/Swagger**: For API documentation.
   
   **Frontend**: Consider using **React** or **Angular** for a modern UI with an interactive dashboard, or **Thymeleaf** for a simpler, server-side rendered solution.

   **File Storage**: Use AWS S3, Azure Blob Storage, or a similar service if deploying to the cloud, or keep it local in the development phase.

   **Notifications**: Leverage email services (like SMTP) and/or SMS for alert notifications. This could evolve to use in-app notifications, push notifications, etc.



