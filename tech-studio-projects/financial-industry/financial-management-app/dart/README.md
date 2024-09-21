# Financial Manager App

A simple, cross-platform financial management app built with Flutter. This app allows users to manage their financial transactions, filter them by date and category, and generate reports to get an overview of their financial situation.

## Features

- **Add Transactions:** Users can add transactions with a title, amount, date, and category.
- **View Transactions:** Transactions are displayed in a list, showing the title, amount, category, and date.
- **Filter Transactions:** Users can filter transactions by date range and category.
- **Generate Reports:** Users can view total spending and a breakdown by category.
- **Persistent Storage:** Data is stored locally using Hive, so transactions persist across app sessions.

## Technologies and Resources Used

- **Flutter:** A UI toolkit for building natively compiled applications for mobile, web, and desktop from a single codebase.
- **Dart:** The programming language used by Flutter.
- **Provider:** A state management solution for managing application state.
- **Hive:** A lightweight and fast key-value database that is used for local storage in the app.
- **UUID:** Used for generating unique identifiers for each transaction.

## Getting Started

### Prerequisites

To run this project, you'll need to have the following installed on your development machine:

- **Flutter SDK:** [Install Flutter](https://flutter.dev/docs/get-started/install)
- **Dart SDK:** (Included with Flutter)
- **Android Studio or Visual Studio Code:** Recommended for editing and running Flutter projects.

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/yourusername/finance_manager.git
   ```

2. **Navigate to the project directory:**

   ```bash
   cd finance_manager
   ```

3. **Install dependencies:**

   Run the following command to get all the necessary packages:

   ```bash
   flutter pub get
   ```

4. **Generate the Hive adapter:**

   Hive requires code generation for custom data types. Run the following command:

   ```bash
   flutter packages pub run build_runner build
   ```

### Running the App

You can run the app on an Android emulator, iOS simulator, or a real device.

1. **Connect a device or start an emulator.**
2. **Run the app:**

   ```bash
   flutter run
   ```

### Project Structure

- **lib/models/transaction.dart:** Defines the `Transaction` model with fields for id, title, amount, date, and category.
- **lib/providers/transaction_provider.dart:** Manages the state of transactions, including adding, deleting, filtering, and generating reports.
- **lib/widgets/transaction_list.dart:** Displays the list of transactions.
- **lib/widgets/new_transaction.dart:** A form for adding a new transaction.
- **lib/widgets/transaction_filter.dart:** Provides UI for filtering transactions by date and category.
- **lib/widgets/transaction_report.dart:** Displays a report with total spending and category breakdown.
- **lib/main.dart:** The main entry point of the app, sets up the app structure and integrates the various widgets.

### Usage

- **Add a Transaction:** Tap the '+' button in the app bar or the floating action button to add a new transaction. Fill in the details and save.
- **Filter Transactions:** Use the filter section to choose a date range and category to view specific transactions.
- **View Reports:** Scroll down to the report section to see your total spending and a breakdown by category.

### Screenshots

(Include screenshots of your app here, showing the main features.)

### Future Enhancements

- **Exporting Reports:** Add functionality to export reports to PDF or Excel.
- **Advanced Filtering:** Allow users to filter by multiple categories or add more complex filters.
- **Integration:** Connect to external APIs or services for syncing data with online financial tools.
