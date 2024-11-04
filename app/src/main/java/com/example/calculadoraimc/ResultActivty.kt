package com.example.calculadoraimc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
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
            resultado >= 30 && resultado < 35 -> "Obesidade I"
            resultado >= 35 && resultado < 40 -> "Obesidade II"
            resultado >= 40 -> "Obesidade III"
            else -> "Valor Inválido"
        }

        val textViewClassificacaoResultado = findViewById<TextView>(R.id.textView_classificacaoresultado)
        val textViewDescricaoResultado = findViewById<TextView>(R.id.textView_descricaoresultado)
        val imageViewImagemResultado = findViewById<ImageView>(R.id.imageView_imagemresultado)

        textViewClassificacaoResultado.text = "Seu IMC é de ${String.format("%.2f", resultado)} \n" + "" +
                "Segundo a OMS sua classificação é \n" + textoResultadoClassificado

        val imagemResultado = when (textoResultadoClassificado) {
            "Magreza" -> R.drawable.resultado1
            "Normal" -> R.drawable.resultado2
            "Sobrepeso", "Obesidade I", "Obesidade II", "Obesidade III" -> R.drawable.resultado3
            else -> R.drawable.resultado2
        }

        val descricaoResultado = when (textoResultadoClassificado) {
            "Magreza" -> DESCRICAO_MAGREZA
            "Normal" -> DESCRICAO_NORMAL
            "Sobrepeso" -> DESCRICAO_SOBREPESO
            "Obesidade I", "Obesidade II", "Obesidade III" -> DESCRICAO_OBESIDADE
            else -> "Descrição não disponível para o valor fornecido."
        }
        imageViewImagemResultado.setImageResource(imagemResultado)
        textViewDescricaoResultado.text = descricaoResultado
    }

    fun voltarEditarButtonOnClick(v: View) {
        finish()
    }

    fun voltarNovoButtonOnClick(v: View) {
        // Criar um Intent para retornar à MainActivity
        val intent = Intent(this, MainActivity::class.java)
        // Adicionar uma flag extra que indica um novo cálculo
        intent.putExtra("novo_calculo", true)
        // Iniciar a MainActivity novamente
        startActivity(intent)
        // Finalizar a activity atual
        finish()
    }
}