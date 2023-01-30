package com.douglas.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {

    private var permiteOperacoes = false
    private var permiteDecimal = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()
        eventoClickNumeros()
        eventoClickOperadores()
        eventoClickOutros()
    }

    fun clickNumeros(view: View) {
        if (view is Button) {
            if (view.text == ".") {
                if (permiteDecimal)
                    expressao.append(view.text)
                permiteDecimal = false
            } else
                expressao.append(view.text)
            permiteOperacoes = true
        }
    }

    fun clickOperadores(view: View) {
        if (view is Button && permiteOperacoes) {
            expressao.append(view.text)
            permiteOperacoes = false
            permiteDecimal = true
        }
    }

    private fun eventoClickOutros(){

        limpar.setOnClickListener {
            expressao.text = ""
            resultado.text = ""

            habilitaTudo()
        }

        apagar.setOnClickListener {
            val string = expressao.text.toString()
            if (string.isNotBlank()) {

                expressao.text = string.substring(0, string.length - 1)
            }

            ponto.isEnabled = expressao.getText().toString() == "^(\\d+\\.\\d+)\$"

        }

        igual.setOnClickListener {

            habilitaTudo()

            try {
                val expressao = ExpressionBuilder(expressao.text.toString()).build()
                val result = expressao.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble())
                    resultado.text = longResult.toString()
                else
                    resultado.text = result.toString()
            } catch (e: Exception) {
            }
        }

    }

    private fun eventoClickOperadores(){

        fechaParentese.isEnabled = false

        soma.setOnClickListener {
            AcrescentarExpressao("+", false)
            desabilitaOperadores()
        }

        abreParentese.setOnClickListener {
            AcrescentarExpressao("(", false)
            desabilitaOperadores()
            fechaParentese.isEnabled = true
            abreParentese.isEnabled = false
        }

        fechaParentese.setOnClickListener {
            AcrescentarExpressao(")", false)
            desabilitaOperadores()
            fechaParentese.isEnabled = false
            abreParentese.isEnabled = true
        }

        subtracao.setOnClickListener {
            AcrescentarExpressao("-", false)
            desabilitaOperadores()
        }

        multiplicacao.setOnClickListener {
            AcrescentarExpressao("*", false)
            desabilitaOperadores()
        }

        divisao.setOnClickListener {
            AcrescentarExpressao("/", false)
            desabilitaOperadores()
        }

    }

    private fun eventoClickNumeros() {

        numero0.setOnClickListener {
            AcrescentarExpressao("0", true)
            habilitaOperadores()
        }

        numero1.setOnClickListener {
            AcrescentarExpressao("1", true)
            habilitaOperadores()
        }

        numero2.setOnClickListener {
            AcrescentarExpressao("2", true)
            habilitaOperadores()
        }

        numero3.setOnClickListener {
            AcrescentarExpressao("3", true)
            habilitaOperadores()
        }

        numero4.setOnClickListener {
            AcrescentarExpressao("4", true)
            habilitaOperadores()
        }

        numero5.setOnClickListener {
            AcrescentarExpressao("5", true)
            habilitaOperadores()
        }

        numero6.setOnClickListener {
            AcrescentarExpressao("6", true)
            habilitaOperadores()
        }

        numero7.setOnClickListener {
            AcrescentarExpressao("7", true)
            habilitaOperadores()
        }

        numero8.setOnClickListener {
            AcrescentarExpressao("8", true)
            habilitaOperadores()
        }

        numero9.setOnClickListener {
            AcrescentarExpressao("9", true)
            habilitaOperadores()
        }

        ponto.setOnClickListener {
            AcrescentarExpressao(".", true)
            ponto.isEnabled = false
        }
    }

    private fun desabilitaOperadores(){
        ponto.isEnabled = true
        soma.isEnabled = false
        subtracao.isEnabled = false
        multiplicacao.isEnabled = false
        divisao.isEnabled = false
    }


    private fun habilitaOperadores(){
        soma.isEnabled = true
        subtracao.isEnabled = true
        multiplicacao.isEnabled = true
        divisao.isEnabled = true
    }

    private fun habilitaTudo(){
        soma.isEnabled = true
        subtracao.isEnabled = true
        multiplicacao.isEnabled = true
        divisao.isEnabled = true
        ponto.isEnabled = true
        abreParentese.isEnabled = true
        fechaParentese.isEnabled = false
    }

    fun AcrescentarExpressao(string: String, limpar_dados: Boolean) {

        if (resultado.text.isNotEmpty()) {
            expressao.text = ""
        }
        if (limpar_dados) {
            resultado.text = ""
            expressao.append(string)
        } else {
            expressao.append(resultado.text)
            expressao.append(string)
            resultado.text = ""
        }
    }
}


