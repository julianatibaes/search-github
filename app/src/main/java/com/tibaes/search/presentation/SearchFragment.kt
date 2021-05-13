package com.tibaes.search.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tibaes.search.data.repository.Resultado
import com.tibaes.search.databinding.SearchFragmentBinding
import com.tibaes.search.domain.entity.Item
import com.tibaes.search.presentation.adapter.ItemDetailListener
import com.tibaes.search.presentation.adapter.SearchListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val viewModel by viewModel<SearchViewModel>()

    private lateinit var binding: SearchFragmentBinding
    private val listAdapter by lazy { SearchListAdapter(itemListener) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        setRecycler(binding.recyclerView)
        searchGithub(binding)
        render(binding)
        hardcodeList(binding)
        return binding.root
    }

    private fun hardcodeList(binding: SearchFragmentBinding) {
        val items = listOf(
            Item(
                2,
                3,
                "dois",
                "teste teste",
                false,
                "descricao",
                1,
                "fdfd"
            )
        )

        listAdapter.submitList(items)
        renderList(binding, items)
    }

    private fun render(binding: SearchFragmentBinding) {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is SearchState.LoadingState -> renderLoading(binding)
                is SearchState.DataState -> renderList(binding, it.data)
                is SearchState.ErrorState -> renderError(it)
                is SearchState.EmptyState -> renderEmptyList(binding)
            }
        }
    }

    private fun setRecycler(recycler: RecyclerView) {
        recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = listAdapter
        }
    }

    private fun renderEmptyList(binding: SearchFragmentBinding) {
        binding.searchLoading.visibility = View.GONE
        binding.emptyStateImage.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
    }

    private fun renderLoading(binding: SearchFragmentBinding) {
        binding.searchLoading.visibility = View.VISIBLE
        binding.emptyStateImage.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
    }

    private fun renderList(binding: SearchFragmentBinding, list: List<Item>) {
        binding.searchLoading.visibility = View.GONE
        binding.emptyStateImage.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        listAdapter.submitList(list)
    }

    private fun renderError(state: SearchState.ErrorState) {
        Toast.makeText(context, state.error, Toast.LENGTH_LONG).show()
    }

    private fun searchGithub(binding: SearchFragmentBinding) {
        binding.searchButton.setOnClickListener {
            // viewModel.interact(SearchInteraction.SearchGithub(binding.queryText.text.toString()))
            viewModel.searchGithub(binding.queryText.text.toString()).observe(this) {
                val result = it?.let { _list ->
                    when (_list) {
                        is Resultado.Success -> {
                            _list.response?.let { github ->
                                renderList(binding, github.items)
                            }
                        }
                        else -> {
                        }
                    }
                }
            }
        }
    }

    private fun viewDetail(item: Item) {
        ItemGithubBottomSheet().apply {
            arguments = Bundle().apply {
                putSerializable("item_detail", item)
            }
            this@SearchFragment.fragmentManager?.let { show(it, ItemGithubBottomSheet::class.java.name) }
            setDismissListener {
                listAdapter.notifyDataSetChanged()
            }
        }


    }

    private val itemListener = object : ItemDetailListener {
        override fun navToItemDetails(item: Item) {
            viewDetail(item)
        }

    }

}

