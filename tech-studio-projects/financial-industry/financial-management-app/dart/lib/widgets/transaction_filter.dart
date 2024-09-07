import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../providers/transaction_provider.dart';
import '../models/transaction.dart';

class TransactionFilter extends StatefulWidget {
  @override
  _TransactionFilterState createState() => _TransactionFilterState();
}

class _TransactionFilterState extends State<TransactionFilter> {
  DateTime? _startDate;
  DateTime? _endDate;
  String? _selectedCategory;

  final List<String> _categories = [
    'All',
    'Food',
    'Transport',
    'Entertainment',
    'Utilities',
    'Health',
    'Other',
  ];

  void _presentStartDatePicker() {
    showDatePicker(
      context: context,
      initialDate: DateTime.now(),
      firstDate: DateTime(2020),
      lastDate: DateTime.now(),
    ).then((pickedDate) {
      if (pickedDate == null) {
        return;
      }
      setState(() {
        _startDate = pickedDate;
      });
    });
  }

  void _presentEndDatePicker() {
    showDatePicker(
      context: context,
      initialDate: DateTime.now(),
      firstDate: DateTime(2020),
      lastDate: DateTime.now(),
    ).then((pickedDate) {
      if (pickedDate == null) {
        return;
      }
      setState(() {
        _endDate = pickedDate;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    final transactionProvider = Provider.of<TransactionProvider>(context);

    return Column(
      children: <Widget>[
        Row(
          children: <Widget>[
            Expanded(
              child: Text(
                'Start Date: ${_startDate != null ? _startDate!.toLocal().toString().split(' ')[0] : 'Not selected'}',
              ),
            ),
            TextButton(
              onPressed: _presentStartDatePicker,
              child: Text(
                'Choose Start Date',
                style: TextStyle(fontWeight: FontWeight.bold),
              ),
            ),
          ],
        ),
        Row(
          children: <Widget>[
            Expanded(
              child: Text(
                'End Date: ${_endDate != null ? _endDate!.toLocal().toString().split(' ')[0] : 'Not selected'}',
              ),
            ),
            TextButton(
              onPressed: _presentEndDatePicker,
              child: Text(
                'Choose End Date',
                style: TextStyle(fontWeight: FontWeight.bold),
              ),
            ),
          ],
        ),
        DropdownButton<String>(
          value: _selectedCategory,
          hint: Text('Select Category'),
          onChanged: (newValue) {
            setState(() {
              _selectedCategory = newValue == 'All' ? null : newValue;
            });
          },
          items: _categories.map<DropdownMenuItem<String>>((String value) {
            return DropdownMenuItem<String>(
              value: value,
              child: Text(value),
            );
          }).toList(),
        ),
        ElevatedButton(
          child: Text('Filter Transactions'),
          onPressed: () {
            setState(() {});
          },
        ),
        Expanded(
          child: ListView.builder(
            itemCount: transactionProvider
                .filterTransactions(startDate: _startDate, endDate: _endDate, category: _selectedCategory)
                .length,
            itemBuilder: (ctx, index) {
              final tx = transactionProvider.filterTransactions(
                  startDate: _startDate, endDate: _endDate, category: _selectedCategory)[index];
              return Card(
                margin: EdgeInsets.symmetric(vertical: 8, horizontal: 5),
                child: ListTile(
                  title: Text(tx.title),
                  subtitle: Text('${tx.category} - \$${tx.amount.toStringAsFixed(2)}'),
                  trailing: Text('${tx.date.toLocal()}'.split(' ')[0]),
                ),
              );
            },
          ),
        ),
      ],
    );
  }
}
