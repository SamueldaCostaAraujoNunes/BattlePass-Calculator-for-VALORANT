package br.com.samuelnunes.valorantpassbattle.ui.view.fragment.Settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.samuelnunes.valorantpassbattle.NavGraphDirections
import br.com.samuelnunes.valorantpassbattle.databinding.FragmentHistoricInputBinding
import br.com.samuelnunes.valorantpassbattle.ui.view.adapter.ItemUserInputAdapter
import br.com.samuelnunes.valorantpassbattle.ui.view.adapter.RecyclerItemClickListener
import br.com.samuelnunes.valorantpassbattle.ui.view.helpers.ItemSwipeHelper
import br.com.samuelnunes.valorantpassbattle.ui.view.helpers.SwipedEventListener
import br.com.samuelnunes.valorantpassbattle.ui.viewModel.activity.UIViewModel
import br.com.samuelnunes.valorantpassbattle.ui.viewModel.fragment.settings.HistoricInputViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoricInputFragment : Fragment() {
    private val viewModel: HistoricInputViewModel by viewModels()
    private val uiViewModel: UIViewModel by activityViewModels()
    private lateinit var binding: FragmentHistoricInputBinding
    private lateinit var adapter: ItemUserInputAdapter

    private val navController: NavController
        get() {
            return findNavController()
        }

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
        binding = FragmentHistoricInputBinding.inflate(inflater, container, false)
        setupObservers()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, _, _ ->
            binding.toolbar.title = ""
        }
        return binding.root
    }

    private fun setupObservers() {
        viewModel.allUserInput.observe(viewLifecycleOwner, {
            adapter = ItemUserInputAdapter(it)
            binding.rvEditHistoric.adapter = adapter
        })

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
        val userTier = (binding.rvEditHistoric.adapter as ItemUserInputAdapter).list[pos]
        navController.navigate(NavGraphDirections.actionGlobalDialogInput(userTier.id))
    }

    private fun deleteItem(
        pos: Int
    ) {
        val userTierSelected = (binding.rvEditHistoric.adapter as ItemUserInputAdapter).list[pos]
        val direction =
            HistoricInputFragmentDirections.actionHistoricInputFragmentToDialogDeleteItemConfimation(
                userTierSelected.id
            )
        navController.navigate(direction)
    }
}