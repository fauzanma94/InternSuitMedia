package com.example.mysuitmediaintern.ui.third

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mysuitmediaintern.R
import com.example.mysuitmediaintern.databinding.FragmentThirdScreenBinding
import com.example.mysuitmediaintern.ui.ViewModelFactory


class ThirdScreenFragment : Fragment() {
    private lateinit var binding: FragmentThirdScreenBinding
    private val thirdScreenViewModel: ThirdScreenViewModel by activityViewModels {
        ViewModelFactory(requireActivity().application)
    }
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_thirdScreenFragment_to_secondScreenFragment)
        }

        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            thirdScreenViewModel.refreshListUser()
        }


        val adapter = ThirdScreenAdapter()
        binding.rvUsers.adapter = adapter
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())


        thirdScreenViewModel.listUser.observe(viewLifecycleOwner) { listUser ->
            adapter.setData(listUser)
        }

        thirdScreenViewModel.isRefresh.observe(viewLifecycleOwner) { isRefreshing ->
            swipeRefreshLayout.isRefreshing = isRefreshing
        }

    }

}