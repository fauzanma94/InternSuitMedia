package com.example.mysuitmediaintern.ui.second

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mysuitmediaintern.R
import com.example.mysuitmediaintern.databinding.FragmentSecondScreenBinding
import com.example.mysuitmediaintern.ui.SharedViewModel
import com.example.mysuitmediaintern.ui.ViewModelFactory


class SecondScreenFragment : Fragment(R.layout.fragment_second_screen) {
    private val viewModel: SharedViewModel by activityViewModels {
        ViewModelFactory(requireActivity().application)
    }

    private val binding: FragmentSecondScreenBinding by lazy {
        FragmentSecondScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_secondScreenFragment_to_firstScreenFragment)
        }

        binding.btnChooseUser.setOnClickListener {
            findNavController().navigate(R.id.action_secondScreenFragment_to_thirdScreenFragment)
        }

        val inputName = viewModel.inputName
        binding.tvInputName.text = inputName

        val firstName = arguments?.getString("first_name") ?: "Selected"
        val lastName = arguments?.getString("last_name") ?: "User Name"
        val selectedUserName = "$firstName $lastName"

        binding.tvSelectedUserName.text = selectedUserName
    }


}