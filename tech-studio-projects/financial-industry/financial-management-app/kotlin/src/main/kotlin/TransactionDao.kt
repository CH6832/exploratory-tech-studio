import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.*

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(transaction: Transaction)

    @Query("DELETE FROM transactions WHERE id = :transactionId")
    suspend fun delete(transactionId: UUID)

    @Query("SELECT * FROM transactions ORDER BY date DESC")
    fun getAllTransactions(): LiveData<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE (date BETWEEN :startDate AND :endDate) AND category = :category ORDER BY date DESC")
    fun filterTransactionsByDateAndCategory(startDate: Date, endDate: Date, category: String): LiveData<List<Transaction>>
}
