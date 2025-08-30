package com.afsar.news.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.afsar.news.data.Repository
import com.afsar.news.data.source.RemoteDataSource
import com.afsar.news.databinding.FragmentSearchBinding
import com.afsar.news.utils.ViewModelFactory

class SearchFragment : Fragment() {

    private lateinit var searchAdapter: SearchAdapter
    private lateinit var viewModel: SearchViewModel
    private lateinit var querySearch : String
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = Repository(RemoteDataSource())
        val factory = ViewModelFactory(repository)
        searchAdapter = SearchAdapter()
        viewModel = ViewModelProvider(this, factory)[SearchViewModel::class.java]

        binding.apply {

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    querySearch = query.toString()
                    viewModel.searchNews(querySearch).observe(viewLifecycleOwner) {
                        searchAdapter.setNews(it)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })
            rvSearch.apply {
                adapter = searchAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
            }

        }
    }

}