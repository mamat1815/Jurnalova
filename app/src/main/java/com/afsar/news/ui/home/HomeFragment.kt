package com.afsar.news.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.afsar.news.data.Repository
import com.afsar.news.data.source.RemoteDataSource
import com.afsar.news.databinding.FragmentHomeBinding
import com.afsar.news.utils.ViewModelFactory


class HomeFragment : Fragment() {

//    private var _binding: FragmentHomeBinding? = null
//    private val binding get() = _binding!!

    private lateinit var homeAdapter: HomeAdapter
    private lateinit var popularAdapter: HomePopularAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = Repository(RemoteDataSource())
        val factory = ViewModelFactory(repository)
        homeAdapter = HomeAdapter()
        popularAdapter = HomePopularAdapter()
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
//        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[HomeViewModel::class.java]
        binding.rvHome.adapter = homeAdapter
        binding.rvHome.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.apply {
            rvHeadlines.adapter = popularAdapter
            rvHeadlines.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        }
        viewModel.getListNews().observe(viewLifecycleOwner) {
            homeAdapter.setNews(it)
        }

        viewModel.getListPopularNews().observe(viewLifecycleOwner) {
            popularAdapter.setNews(it)

        }

    }


}