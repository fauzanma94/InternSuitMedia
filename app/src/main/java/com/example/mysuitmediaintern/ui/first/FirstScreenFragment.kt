package com.example.mysuitmediaintern.ui.first

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mysuitmediaintern.R
import com.example.mysuitmediaintern.databinding.FragmentFirstScreenBinding
import com.example.mysuitmediaintern.ui.SharedViewModel
import com.example.mysuitmediaintern.ui.ViewModelFactory
import java.util.Locale


class FirstScreenFragment : Fragment(R.layout.fragment_first_screen) {

    private val viewModel: SharedViewModel by activityViewModels {
        ViewModelFactory(requireActivity().application)
    }
    private val binding: FragmentFirstScreenBinding by lazy {
        FragmentFirstScreenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        binding.btnNext.setOnClickListener {
            val inputName = binding.etName.text.toString()
            viewModel.inputName = inputName

            val bundle = Bundle().apply {
                putString("inputName", inputName)
            }
            findNavController().navigate(
                R.id.action_firstScreenFragment_to_secondScreenFragment,
                bundle
            )
        }

        binding.btnCheck.setOnClickListener {
            val input = binding.etPalindrome.text.toString()
            val isPalindrome = isPalindrome(input)

            val message = if (input.isBlank()) {
                "Masukkan Kata"
            } else {
                if (isPalindrome) "isPalindrome." else "not palindrome."
            }

            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    private fun isPalindrome(word: String): Boolean {
        val string = word.lowercase(Locale.ROOT).replace("[^a-z0-9]".toRegex(), "")
        val length = string.length
        for (i in 0 until length / 2) {
            if (string[i] != string[length - i - 1]) {
                return false
            }
        }
        return true

    }

    companion object {

    }
}