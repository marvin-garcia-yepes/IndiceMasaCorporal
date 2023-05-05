package com.example.indicemasacorporal.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.indicemasacorporal.R
import com.example.indicemasacorporal.imccalculator.ImcCalculatorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResulta: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnReCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)
        val result: Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        btnReCalculate.setOnClickListener { onBackPressed() }
    }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        when (result) {
            in 0.00..18.50 -> {
                tvResulta.text = getString(R.string.title_bajo_peso)
                tvResulta.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                tvDescription.text = getString(R.string.descripcion_bajo_peso)
            }

            in 18.51..24.99 -> {
                tvResulta.text = getString(R.string.title_peso_normal)
                tvResulta.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
                tvDescription.text = getString(R.string.title_peso_normal)
            }

            in 25.00..29.99 -> {
                tvResulta.text = getString(R.string.title_sobrepeso)
                tvResulta.setTextColor(ContextCompat.getColor(this, R.color.peso_sobrepeso))
                tvDescription.text = getString(R.string.descripcion_sobrepeso)
            }

            in 30.00..99.99 -> {
                tvResulta.text = getString(R.string.title_obesidad)
                tvResulta.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                tvDescription.text = getString(R.string.descripcion_obesidad)
            }
            else -> {
                tvIMC.text = getString(R.string.error)
                tvResulta.text = getString(R.string.error)
                tvResulta.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                tvDescription.text = getString(R.string.error)
            }
        }

    }

    private fun initComponents() {
        tvResulta = findViewById(R.id.tvResulta)
        tvIMC = findViewById(R.id.tvIMC)
        tvDescription = findViewById(R.id.tvDescription)
        btnReCalculate = findViewById(R.id.btnReCalculate)
    }
}