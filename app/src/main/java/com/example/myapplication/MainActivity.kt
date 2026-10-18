package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTabMain.setOnClickListener { switchFragment(MainMealFragment()) }
        binding.btnTabSide.setOnClickListener { switchFragment(SideDishFragment()) }
        binding.btnTabDrink.setOnClickListener { switchFragment(DrinkFragment()) }
        binding.btnTabConfirm.setOnClickListener { switchFragment(ConfirmFragment()) }

        if (savedInstanceState == null) {
            switchFragment(MainMealFragment())
        }
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    fun onOrderSubmitted() {
        val finalOrder = viewModel.order.value

        binding.tvFinalResult.visibility = View.VISIBLE
        binding.tvFinalResult.text = "成功下單！餐點為：\n" +
                "主餐：${finalOrder?.mainMeal}\n" +
                "副餐：${finalOrder?.sideDishes?.joinToString(", ")}\n" +
                "飲料：${finalOrder?.drink}"

        switchFragment(MainMealFragment())
        viewModel.resetOrder()
    }
}
