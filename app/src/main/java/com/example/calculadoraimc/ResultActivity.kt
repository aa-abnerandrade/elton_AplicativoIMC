package com.example.calculadoraimc

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val resultado = intent.getDoubleExtra("resultado", 0.0)
        setContentView(R.layout.activity_result)
        classificarResultado(resultado)
        //setTitle("Result")
    }

    fun classificarResultado(resultado: Double) {
        val textoResultadoClassificado: String

        textoResultadoClassificado = when {
            resultado < 18.5 -> "Magreza"
            resultado >= 18.5 && resultado < 25 -> "Normal"
            resultado >= 25 && resultado < 30 -> "Sobrepeso"
            resultado >= 30 && resultado < 40 -> "Obesidade"
            resultado >= 40 -> "Obesidade Grave"
            else -> "Valor Inválido"
        }

        val textViewResultado = findViewById<TextView>(R.id.textView_textoresultado)
        textViewResultado.text = textoResultadoClassificado
    }

    fun voltarButtonOnClick(v: View) {
        finish()
    }
}