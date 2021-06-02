package com.shakir.thewellnessroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
import com.shakir.thewellnessroom.check.CheckViewModel
import com.shakir.thewellnessroom.check.ScanCheckInteractor
import com.shakir.thewellnessroom.check_api.DataBodyCheck

class ScannerActivity : AppCompatActivity() {

    private val checkViewModel = CheckViewModel()
    private val scanInteractor = ScanCheckInteractor()
    private val TOKEN = "1393.UgJKe7UUCJCf7ARal"
    val productsList : MutableList<Product> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        scanQRCode()
    }

    private fun scanQRCode() {
        val integrator = IntentIntegrator(this).apply {
            captureActivity = CaptureActivity::class.java
            setOrientationLocked(false)
            setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
            setPrompt("Наведите камеру на QR-code")
        }
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                Log.d("Check", "Check result: " + result.contents)

                val rawData = result.contents
                val checkDictionary = scanInteractor.makeDataDictionary(rawData)

                Log.d("Check", "Dictionary: $checkDictionary")
                Toast.makeText(this, "Чек отсканирован!", Toast.LENGTH_SHORT).show()

                val intentToMain = Intent(this, MainActivity::class.java)

                checkViewModel.fetchNewCheck(
                        DataBodyCheck(
                                checkDictionary[2],
                                checkDictionary[3],
                                checkDictionary[4],
                                checkDictionary[5].toInt().toString(),
                                checkDictionary[1].toDouble().toString(),
                                scanInteractor.makeDocDateTime(),
                                "0",
                                TOKEN
                        ), {
                    for (i in it.items) {
                        productsList.add(
                                Product(
                                        i.productName,
                                        i.price.toDouble(),
                                        i.quantity
                                )
                        )
                    }
                    Log.d("Check", "Request: $it")
                    Log.d("Check", "counts: ${it.items.size}")
                }
                ) {
                    Toast.makeText(
                            this,
                            "Ошибка при получении чека",
                            Toast.LENGTH_LONG
                    ).show()
                }
                startActivity(intentToMain)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}