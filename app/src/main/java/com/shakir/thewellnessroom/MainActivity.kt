package com.shakir.thewellnessroom

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val viewAdapter = ProductsAdapter(listOf())
    val scannerActivity = ScannerActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productsList : List<Product> = scannerActivity.productsList

        if (productsList.isEmpty()) {
            recycler_view_products.visibility = View.GONE
            text_view_list_empty.visibility = View.VISIBLE
        } else {
            recycler_view_products.visibility = View.VISIBLE
            text_view_list_empty.visibility = View.GONE

            recycler_view_products.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = viewAdapter
            }

            viewAdapter.submitList(productsList)
            viewAdapter.notifyDataSetChanged()
        }

        Log.d("MainF", "Products List: $productsList")

        button_scan_check.setOnClickListener {
            val intent = Intent(this, ScannerActivity::class.java)
            startActivity(intent)
        }
    }

    private fun testData(): List<Product> {
        return listOf(
                Product(
                        "Колбаса Мираторг Московская",
                        366.40,
                        3),
                Product(
                        "Йогурт Danone 110 г.",
                        50.80,
                        1),
                Product(
                        "Творог Простоквашино 5%",
                        44.50,
                        2),
                Product(
                        "Яйца куриные",
                        60.99,
                        5),
                Product(
                        "Напиток coca-cola 1л.",
                        80.00,
                        1),
                Product(
                        "Чипсы Lays сметана и зелень",
                        89.50,
                        1),
                Product(
                        "Шоколад Alpen Gold 90г. с орехом",
                        39.99,
                        5),
                Product(
                        "Шоколад Alpe Gold 90г. обычный",
                        39.99,
                        6)
        )
    }
}