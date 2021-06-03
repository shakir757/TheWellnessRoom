package com.shakir.thewellnessroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_product.*
import org.json.JSONObject
import kotlin.math.pow

class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val rName = intent.getStringExtra("nameProduct").toString()
        progress_bar_image.visibility = View.VISIBLE
        progress_bar_stat.visibility = View.VISIBLE
        text_view_product_name.text = rName

        var engName: String = ""
        val url = "https://cryptic-atoll-88150.herokuapp.com/?text=$rName"

        val requestQueue = Volley.newRequestQueue(this)

        val responseListener = Response.Listener<JSONObject> { response ->
            engName = response.getString("text")
            getProductInfo(engName)
        }

        val responseErrorListener = Response.ErrorListener {
            Toast.makeText(this, "Проверьте интернет соединение!", Toast.LENGTH_SHORT).show()
            progress_bar_image.visibility = View.GONE
            progress_bar_stat.visibility = View.GONE
            image_view_product.visibility = View.VISIBLE
            image_view_product.setImageResource(R.drawable.product)
        }

        val request = JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                responseListener,
                responseErrorListener
        )

        requestQueue.add(request)
    }

    private fun getProductInfo(engName: String) {
        val baseUrl = "https://api.edamam.com/api/food-database/v2/parser"
        val url = "$baseUrl?app_id=b3546fcd&app_key=09c65ad415cd8c2be3966db126557c13&ingr=$engName"

        val requestQueue = Volley.newRequestQueue(this)
        val responseListener = Response.Listener<JSONObject> { response ->
            try {
                val jsonArray = response.getJSONArray("parsed")
                val jsonFood = jsonArray.getJSONObject(0)
                val jsonProduct = jsonFood.getJSONObject("food")
                val jsonNutrients = jsonProduct.getJSONObject("nutrients")

                text_view_calories.text = "Калории: ${jsonNutrients.getDouble("ENERC_KCAL")} kcal"
                text_view_protein.text = "Протеин: ${jsonNutrients.getDouble("PROCNT")} g"
                text_view_fat.text = "Жиры: ${jsonNutrients.getDouble("FAT")} g"
                text_view_carbs.text = "Углеводы: ${jsonNutrients.getDouble("CHOCDF")} g"
                text_view_fiber.text = "Белки: ${jsonNutrients.getDouble("FIBTG")} g"

                val urlImage = jsonProduct.getString("image")

                progress_bar_image.visibility = View.GONE
                progress_bar_stat.visibility = View.GONE
                image_view_product.visibility = View.VISIBLE

                Glide
                        .with(this)
                        .load(urlImage)
                        .into(image_view_product)

            } catch (e: Exception) {
                Toast.makeText(this, "Неудалось найти продукт", Toast.LENGTH_SHORT).show()
                Log.d("Product", "Ошибка: $e")
            }
        }

        val responseErrorListener = Response.ErrorListener {
            Toast.makeText(
                    this,
                    "Неудалось получить данные продукта",
                    Toast.LENGTH_LONG
            ).show()
            progress_bar_image.visibility = View.GONE
            progress_bar_stat.visibility = View.GONE
            image_view_product.visibility = View.VISIBLE
            image_view_product.setImageResource(R.drawable.product)
        }

        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            responseListener,
            responseErrorListener
        )
        requestQueue.add(request)
    }
}