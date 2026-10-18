package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentMainMealBinding

class MainMealFragment : Fragment() {
    private var _binding: FragmentMainMealBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainMealBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (viewModel.order.value?.mainMeal) {
            "美味牛肉堡" -> binding.rbBurger.isChecked = true
            "脆皮炸雞套餐" -> binding.rbChicken.isChecked = true
        }

        binding.rgMainMeal.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbBurger -> viewModel.updateMainMeal("美味牛肉堡")
                R.id.rbChicken -> viewModel.updateMainMeal("脆皮炸雞套餐")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
