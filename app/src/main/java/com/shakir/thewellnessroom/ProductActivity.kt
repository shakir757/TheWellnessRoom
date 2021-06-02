package com.shakir.thewellnessroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_product.*
import org.json.JSONObject
import kotlin.math.pow

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val name = intent.getStringExtra("nameProduct")
        text_view_product_name.text = name

        val engName = rusToEnglish(name!!)
        val engNameTest = "Coca Cola 0.5"

        getProductInfo(engNameTest)
    }

    private fun getProductInfo(engName: String) {
        val baseUrl = "https://api.edamam.com/api/food-database/v2/parser"
        val url = "$baseUrl?app_id=b3546fcd&app_key=09c65ad415cd8c2be3966db126557c13&ingr=$engName"

        val requestQueue = Volley.newRequestQueue(this)
        val responseListener = Response.Listener<JSONObject> { response ->
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

            Glide
                .with(this)
                .load(urlImage)
                .into(image_view_product)
        }

        val responseErrorListener = Response.ErrorListener {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
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

    private fun rusToEnglish(rName: String): String {
        return "d"
    }
}