package com.example.calculadoraimc

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme

class MainActivity : ComponentActivity () {

    private lateinit var editTextTextPeso: EditText
    private lateinit var editTextTextAltura: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate called")

        initViews()
        handleIntentForNewCalculation()
        setupTextWatchers()

    }

    private fun initViews() {
        editTextTextPeso = findViewById(R.id.editTextText_peso)
        editTextTextAltura = findViewById(R.id.editTextText_altura)
    }

    private fun handleIntentForNewCalculation() {
        val isNewCalculation = intent.getBooleanExtra("novo_calculo", false)
        if (isNewCalculation) {
            editTextTextPeso.setText("")
            editTextTextAltura.setText("")
        }
    }

    private fun setupTextWatchers() {
        addFormattedTextWatcher(editTextTextPeso, 10)
        addFormattedTextWatcher(editTextTextAltura, 100)
    }

    private fun addFormattedTextWatcher(editText: EditText, divisor: Int) {
        editText.addTextChangedListener(object : TextWatcher {
            var isUpdating = false
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (isUpdating) return

                isUpdating = true
                val str = s.toString().replace(".", "").replace(",", "")
                if (str.isNotEmpty()) {
                    try {
                        val formatted = str.toDouble() / divisor
                        editText.setText(formatted.toString())
                        editText.setSelection(editText.text.length)
                    } catch (e: NumberFormatException) {
                        e.printStackTrace()
                    }
                }
                isUpdating = false
            }
        })
    }

    fun calculateButtonOnClick(v: View) {
        val peso = editTextTextPeso.text.toString()
        val altura = editTextTextAltura.text.toString()

        if (peso.isNotEmpty() && altura.isNotEmpty()) {
            val intentResult = Intent(this, ResultActivity::class.java)
            val resultado: Double = calcularIMC(peso, altura)
            intentResult.putExtra("peso", peso)
            intentResult.putExtra("altura", altura)
            intentResult.putExtra("resultado", resultado)
            startActivity(intentResult)
        } else {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show()
        }
    }

    fun calcularIMC(p: String, a: String): Double {
        return p.toDouble() / (a.toDouble() * a.toDouble())
    }

    override fun onStart() {
        super.onStart();
        Log.v("LifeCycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.w("LifeCycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("LifeCycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.wtf("LifeCycle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("LifeCycle", "onDestroy")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculadoraIMCTheme {
        Greeting("User")
    }
}