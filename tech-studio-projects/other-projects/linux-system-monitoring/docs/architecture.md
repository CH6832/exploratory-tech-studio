# Architecture Overview

The Monitoring System consists of a Flask backend API that fetches server metrics and a frontend that allows users to interact with the data. The architecture follows a client-server model.

## Components

- **Flask API**: Handles requests and serves metrics.
- **Bash Scripts**: Collects server metrics periodically.
- **Frontend**: Displays metrics in a user-friendly manner and allows remote monitoring.
