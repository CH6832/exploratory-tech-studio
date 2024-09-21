import androidx.lifecycle.LiveData
import java.util.*

class TransactionRepository(private val transactionDao: TransactionDao) {

    val allTransactions: LiveData<List<Transaction>> = transactionDao.getAllTransactions()

    suspend fun insert(transaction: Transaction) {
        transactionDao.insert(transaction)
    }

    suspend fun delete(transactionId: UUID) {
        transactionDao.delete(transactionId)
    }

    fun filterTransactions(startDate: Date, endDate: Date, category: String): LiveData<List<Transaction>> {
        return transactionDao.filterTransactionsByDateAndCategory(startDate, endDate, category)
    }
}
