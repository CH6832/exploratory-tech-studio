import 'package:flutter/foundation.dart';
import 'package:hive/hive.dart';

import '../models/transaction.dart';

class TransactionProvider with ChangeNotifier {
  List<Transaction> _transactions = [];

  List<Transaction> get transactions => _transactions;

  void addTransaction(Transaction transaction) {
    _transactions.add(transaction);
    notifyListeners();
    saveToHive();
  }

  void deleteTransaction(String id) {
    _transactions.removeWhere((tx) => tx.id == id);
    notifyListeners();
    saveToHive();
  }

  void loadTransactions() async {
    var box = await Hive.openBox<Transaction>('transactions');
    _transactions = box.values.toList();
    notifyListeners();
  }

  void saveToHive() async {
    var box = await Hive.openBox<Transaction>('transactions');
    await box.clear();
    await box.addAll(_transactions);
  }

  List<Transaction> filterTransactions({DateTime? startDate, DateTime? endDate, String? category}) {
    return _transactions.where((tx) {
      final matchesDate = (startDate == null || tx.date.isAfter(startDate)) &&
                         (endDate == null || tx.date.isBefore(endDate));
      final matchesCategory = category == null || tx.category == category;

      return matchesDate && matchesCategory;
    }).toList();
  }

  double getTotalAmount({DateTime? startDate, DateTime? endDate, String? category}) {
    return filterTransactions(startDate: startDate, endDate: endDate, category: category)
        .fold(0, (sum, tx) => sum + tx.amount);
  }

  Map<String, double> getCategoryBreakdown({DateTime? startDate, DateTime? endDate}) {
    final filteredTransactions = filterTransactions(startDate: startDate, endDate: endDate);

    final Map<String, double> breakdown = {};

    for (var tx in filteredTransactions) {
      if (breakdown.containsKey(tx.category)) {
        breakdown[tx.category] = breakdown[tx.category]! + tx.amount;
      } else {
        breakdown[tx.category] = tx.amount;
      }
    }

    return breakdown;
  }
}
