package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentDrinkBinding

class DrinkFragment : Fragment() {
    private var _binding: FragmentDrinkBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDrinkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (viewModel.order.value?.drink) {
            "冰可樂" -> binding.rbCola.isChecked = true
            "無糖綠茶" -> binding.rbGreenTea.isChecked = true
        }

        binding.rgDrink.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbCola -> viewModel.updateDrink("冰可樂")
                R.id.rbGreenTea -> viewModel.updateDrink("無糖綠茶")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
