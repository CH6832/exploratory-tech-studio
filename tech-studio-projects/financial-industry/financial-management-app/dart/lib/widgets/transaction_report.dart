import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../providers/transaction_provider.dart';

class TransactionReport extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final transactionProvider = Provider.of<TransactionProvider>(context);

    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text(
          'Total Amount: \$${transactionProvider.getTotalAmount().toStringAsFixed(2)}',
          style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
        ),
        SizedBox(height: 20),
        Text(
          'Category Breakdown:',
          style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
        ),
        ...transactionProvider.getCategoryBreakdown().entries.map((entry) {
          return ListTile(
            title: Text(entry.key),
            trailing: Text('\$${entry.value.toStringAsFixed(2)}'),
          );
        }).toList(),
      ],
    );
  }
}
