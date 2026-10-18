package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentConfirmBinding

class ConfirmFragment : Fragment() {
    private var _binding: FragmentConfirmBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.order.observe(viewLifecycleOwner) { order ->
            binding.tvOrderSummary.text = "點餐明細確認：\n" +
                    "主餐：${order.mainMeal ?: "尚未選擇"}\n" +
                    "副餐：${if (order.sideDishes.isEmpty()) "尚未選擇" else order.sideDishes.joinToString(", ")}\n" +
                    "飲料：${order.drink ?: "尚未選擇"}"
        }

        binding.btnConfirm.setOnClickListener {
            val currentOrder = viewModel.order.value

            if (currentOrder?.checkIsValid() != true) {
                Toast.makeText(
                    requireContext(),
                    "請選擇主餐、至少一項副餐與飲料！",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            AlertDialog.Builder(requireContext())
                .setTitle("確認訂單")
                .setMessage(
                    "主餐：${currentOrder.mainMeal}\n" +
                            "副餐：${currentOrder.sideDishes.joinToString(", ")}\n" +
                            "飲料：${currentOrder.drink}\n\n確定要提交訂單嗎？"
                )
                .setPositiveButton("Submit") { dialog, _ ->
                    dialog.dismiss()
                    Toast.makeText(requireContext(), "訂單已成功提交！", Toast.LENGTH_SHORT).show()

                    (activity as? MainActivity)?.onOrderSubmitted()
                }
                .setNegativeButton("取消", null)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
