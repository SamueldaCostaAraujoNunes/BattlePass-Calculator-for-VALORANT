package br.com.battlepassCalculatorValorant.ui.view.fragment.Settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import br.com.battlepassCalculatorValorant.databinding.HistoricInputFragmentBinding
import br.com.battlepassCalculatorValorant.ui.view.adapter.ItemUserInputAdapter
import br.com.battlepassCalculatorValorant.ui.view.adapter.RecyclerItemClickListener
import br.com.battlepassCalculatorValorant.ui.view.dialog.DialogDeleteItemConfimation
import br.com.battlepassCalculatorValorant.ui.view.dialog.DialogInput
import br.com.battlepassCalculatorValorant.ui.view.helpers.ItemSwipeHelper
import br.com.battlepassCalculatorValorant.ui.view.helpers.SwipedEventListener
import br.com.battlepassCalculatorValorant.ui.viewModel.activity.UIViewModel
import br.com.battlepassCalculatorValorant.ui.viewModel.fragment.settings.HistoricInputViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoricInputFragment : Fragment() {
    private val viewModel: HistoricInputViewModel by viewModels()
    private val uiViewModel: UIViewModel by activityViewModels()
    private lateinit var binding: HistoricInputFragmentBinding
    private lateinit var adapter: ItemUserInputAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uiViewModel.hideBottomNav()
    }

    override fun onDestroy() {
        super.onDestroy()
        uiViewModel.showBottomNav()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HistoricInputFragmentBinding.inflate(inflater, container, false)
        setupObservers()
        return binding.root
    }

    private fun setupObservers() {
        viewModel.allUserInput.observe(viewLifecycleOwner, Observer {
            adapter = ItemUserInputAdapter(it)
            binding.rvEditHistoric.adapter = adapter
        })

        // Event click item edit / delete
        binding.rvEditHistoric.addOnItemTouchListener(
            RecyclerItemClickListener(
                binding.rvEditHistoric,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        editItem(position)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                        deleteItem(position)
                    }
                })
        )

        // Swipe delete item
        ItemSwipeHelper(binding.rvEditHistoric).setOnSwipeListener(object : SwipedEventListener {
            override fun event(
                viewHolder: RecyclerView.ViewHolder,
                direction: Int
            ) {
                val pos = viewHolder.adapterPosition
                deleteItem(pos)
            }
        })
    }

    private fun editItem(
        pos: Int
    ) {
//        DialogEditInput(this@EditHistoricActivity, pos, historic).show {
//            adapter.notifyItemChanged(pos)
//        }
    }

    private fun deleteItem(
        pos: Int
    ) {
        val adapter = (binding.rvEditHistoric.adapter as ItemUserInputAdapter)
        DialogDeleteItemConfimation(adapter.list[pos]) { adapter.notifyItemRemoved(pos) }.show(
            requireActivity().supportFragmentManager,
            DialogInput.TAG
        )
    }
}