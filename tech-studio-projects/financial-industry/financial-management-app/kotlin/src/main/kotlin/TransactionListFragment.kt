import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TransactionListFragment : Fragment() {

    private lateinit var transactionViewModel: TransactionViewModel
    private lateinit var transactionAdapter: TransactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        transactionAdapter = TransactionAdapter()
        recyclerView.adapter = transactionAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        transactionViewModel = ViewModelProvider(requireActivity()).get(TransactionViewModel::class.java)
        transactionViewModel.allTransactions.observe(viewLifecycleOwner, Observer { transactions ->
            transactions?.let { transactionAdapter.submitList(it) }
        })

        return view
    }
}
