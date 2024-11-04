package com.example.calculadoraimc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    private lateinit var textViewClassificacaoResultado: TextView
    private lateinit var textViewDescricaoResultado: TextView
    private lateinit var imageViewImagemResultado: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        initViews()
        val resultado = intent.getDoubleExtra("resultado", 0.0)
        classificarResultado(resultado)

    }

    private fun initViews() {
        textViewClassificacaoResultado = findViewById(R.id.textView_classificacaoresultado)
        textViewDescricaoResultado = findViewById(R.id.textView_descricaoresultado)
        imageViewImagemResultado = findViewById(R.id.imageView_imagemresultado)
    }

    private fun classificarResultado(resultado: Double) {
        val textoResultadoClassificado = getResultadoClassificado(resultado)

        setClassificationText(resultado, textoResultadoClassificado)
        setImageResult(textoResultadoClassificado)
        setDescriptionText(textoResultadoClassificado)
    }

    private fun getResultadoClassificado(resultado: Double): String {
        return when {
            resultado < 18.5 -> "Magreza"
            resultado >= 18.5 && resultado < 25 -> "Normal"
            resultado >= 25 && resultado < 30 -> "Sobrepeso"
            resultado >= 30 && resultado < 35 -> "Obesidade I"
            resultado >= 35 && resultado < 40 -> "Obesidade II"
            resultado >= 40 -> "Obesidade III"
            else -> "Valor Inválido"
        }
    }

    private fun setClassificationText(resultado: Double, classificacao: String) {
        textViewClassificacaoResultado.text = """
            Seu IMC é de ${String.format("%.2f", resultado)}.
            Segundo a OMS, sua classificação é 
            $classificacao
        """.trimIndent()
    }

    private fun setImageResult(classificacao: String) {
        val imagemResultado = when (classificacao) {
            "Magreza" -> R.drawable.resultado1
            "Normal" -> R.drawable.resultado2
            "Sobrepeso", "Obesidade I", "Obesidade II", "Obesidade III" -> R.drawable.resultado3
            else -> R.drawable.resultado2
        }
        imageViewImagemResultado.setImageResource(imagemResultado)
    }

    private fun setDescriptionText(classificacao: String) {
        val descricaoResultado = when (classificacao) {
            "Magreza" -> DESCRICAO_MAGREZA
            "Normal" -> DESCRICAO_NORMAL
            "Sobrepeso" -> DESCRICAO_SOBREPESO
            "Obesidade I", "Obesidade II", "Obesidade III" -> DESCRICAO_OBESIDADE
            else -> "Descrição não disponível para o valor fornecido."
        }
        textViewDescricaoResultado.text = descricaoResultado
    }

    fun voltarEditarButtonOnClick(v: View) {
        finish()
    }

    fun voltarNovoButtonOnClick(v: View) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("novo_calculo", true)
        startActivity(intent)
        finish()
    }
}