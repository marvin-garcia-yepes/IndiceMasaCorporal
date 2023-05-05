package com.example.indicemasacorporal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.example.indicemasacorporal.imccalculator.ImcCalculatorActivity
import com.example.indicemasacorporal.setings.SetingsActivity
import com.example.indicemasacorporal.superheroapp.SuperHeroListActivity
import com.example.indicemasacorporal.todoapp.TuduActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnSaludar = findViewById<AppCompatButton>(R.id.btnSaludar)
        btnSaludar.setOnClickListener { navigateToSaludar() }

        val btnIndiceMasaCorporal = findViewById<AppCompatButton>(R.id.btnIndiceMasaCorporal)
        btnIndiceMasaCorporal.setOnClickListener { navigateToIndiceMasaCorporal() }

        val btnTODO = findViewById<AppCompatButton>(R.id.btnTODO)
        btnTODO.setOnClickListener { navigateToTUDU() }

        val btnSuperHero = findViewById<AppCompatButton>(R.id.btnSuperHero)
        btnSuperHero.setOnClickListener { navigateToSuperHeroApp() }

        val btnSetings = findViewById<AppCompatButton>(R.id.btnSetings)
        btnSetings.setOnClickListener { navigateToSetings() }

    }

    private fun navigateToSetings() {
        val intent = Intent(this, SetingsActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSuperHeroApp() {
        val intent = Intent(this, SuperHeroListActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToTUDU() {
        val intent = Intent(this, TuduActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToIndiceMasaCorporal() {
        val intent = Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSaludar() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}