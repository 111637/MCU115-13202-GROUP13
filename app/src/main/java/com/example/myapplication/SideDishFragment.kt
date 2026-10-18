package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentSideDishBinding

class SideDishFragment : Fragment() {
    private var _binding: FragmentSideDishBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSideDishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentSideDishes = viewModel.order.value?.sideDishes ?: emptyList()
        binding.cbFries.isChecked = currentSideDishes.contains("黃金薯條")
        binding.cbSalad.isChecked = currentSideDishes.contains("鮮蔬沙拉")

        binding.cbFries.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateSideDish("黃金薯條", isChecked)
        }
        binding.cbSalad.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateSideDish("鮮蔬沙拉", isChecked)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
