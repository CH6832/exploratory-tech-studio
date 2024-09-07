# Generate IFRS Balance Sheet

## Project Description

The **Generate IFRS Balance Sheet** project is designed to automate the process of generating an IFRS-compliant balance sheet within an Excel workbook. This VBA (Visual Basic for Applications) script calculates various financial totals, formats the balance sheet, and applies visual enhancements to make it easier to read and analyze.

### Features

- **Dynamic Calculation**: Automatically calculates the totals for Non-current Assets, Current Assets, Equity, and Non-current Liabilities based on predefined ranges of cells.
- **Formatting**: Applies bold formatting to important labels and calculated totals to enhance readability.
- **Cell Highlighting**: Sets a light blue background for cells containing values that are used in the calculations, making it easier to identify relevant data.
- **Balance Sheet Summary**: Computes and displays the total sum of Non-current Assets, Current Assets, Equity, and Non-current Liabilities in a designated cell, providing a summary of the balance sheet.

### Usage

1. **Preparation**:
   - Ensure your Excel workbook contains a worksheet named **"BalanceSheetTemplate"**. This sheet should include the necessary data ranges for Non-current Assets, Current Assets, Equity, and Non-current Liabilities.
   - Adjust the cell ranges and labels as necessary to fit the structure of your balance sheet.

2. **Running the Macro**:
   - Open the Excel workbook where you want to run the macro.
   - Press `ALT + F11` to open the VBA editor.
   - Insert a new module or update an existing one with the provided VBA script.
   - Close the VBA editor.
   - Press `ALT + F8`, select **"GenerateIFRSBalanceSheet"**, and click **"Run"** to execute the macro.

3. **Results**:
   - The macro will calculate the total values for each section of the balance sheet and place them in the designated cells.
   - Labels and totals will be formatted in bold.
   - The cells used for calculations will be highlighted with a light blue background.

### Project Structure

- **Worksheet "BalanceSheetTemplate"**: The target worksheet where the balance sheet is generated and formatted.
- **VBA Script**: Contains functions to calculate totals, apply formatting, and set cell backgrounds.

### Notes

- Ensure that the ranges defined in the script match the structure of your balance sheet data.
- Modify the sheet name and cell references in the VBA script as needed to align with your workbook's layout.
- This script assumes that data is entered in a specific order; adjust the ranges if your data layout differs.

### Troubleshooting

- **Index Out of Range**: Verify that the sheet names and cell references in the VBA script match those in your workbook.
- **Formatting Issues**: Ensure that there are no conflicts with existing cell formatting in the target worksheet.
