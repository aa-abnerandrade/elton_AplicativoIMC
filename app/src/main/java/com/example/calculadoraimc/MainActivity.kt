package com.example.calculadoraimc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
        Log.d("LifeCycle", "onCreate");
        editTextTextPeso = findViewById(R.id.editTextText_peso)
        editTextTextAltura = findViewById(R.id.editTextText_altura)

    }

    fun calcularButtonOnClick(v: View) {
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
        Greeting("Bictor")
    }
}