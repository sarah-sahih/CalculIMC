package com.example.calculimc

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val editTextPoids = findViewById<EditText>(R.id.editTextPoids)
        val editTextTaille = findViewById<EditText>(R.id.editTextTaille)

        val buttonCalculer = findViewById<Button>(R.id.buttonCalculer)
        val buttonEffacer = findViewById<Button>(R.id.buttonEffacer)

        val textViewImc = findViewById<TextView>(R.id.textViewImc)
        val textViewCategorie = findViewById<TextView>(R.id.textViewCategorie)


        buttonCalculer.setOnClickListener {

            val poidsTexte = editTextPoids.text.toString()
            val tailleTexte = editTextTaille.text.toString()

            if (poidsTexte.isEmpty() || tailleTexte.isEmpty()) {

                Toast.makeText(
                    this,
                    getString(R.string.erreur_champs_vides),
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val poids: Double
            val taille: Double

            try {

                poids = poidsTexte.replace(',', '.').toDouble()
                taille = tailleTexte.replace(',', '.').toDouble()

            } catch (e: NumberFormatException) {

                Toast.makeText(
                    this,
                    getString(R.string.erreur_valeurs_invalides),
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (poids <= 0 || taille <= 0) {

                Toast.makeText(
                    this,
                    getString(R.string.erreur_valeurs_positives),
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val imc = poids / (taille * taille)

            textViewImc.text = String.format(
                Locale.US,
                "IMC : %.2f",
                imc
            )


            if (imc < 18.5) {

                textViewCategorie.text =
                    getString(R.string.categorie_insuffisance)

                textViewCategorie.setTextColor(
                    Color.rgb(255, 152, 0)
                )

            } else if (imc < 25) {

                textViewCategorie.text =
                    getString(R.string.categorie_normale)

                textViewCategorie.setTextColor(Color.GREEN)

            } else if (imc < 30) {

                textViewCategorie.text =
                    getString(R.string.categorie_surpoids)

                textViewCategorie.setTextColor(
                    Color.rgb(255, 152, 0)
                )

            } else if (imc < 35) {

                textViewCategorie.text =
                    getString(R.string.categorie_obesite_moderee)

                textViewCategorie.setTextColor(Color.RED)

            } else if (imc < 40) {

                textViewCategorie.text =
                    getString(R.string.categorie_obesite_severe)

                textViewCategorie.setTextColor(Color.RED)

            } else {

                textViewCategorie.text =
                    getString(R.string.categorie_obesite_morbide)

                textViewCategorie.setTextColor(
                    Color.rgb(139, 0, 0)
                )
            }
        }


        buttonEffacer.setOnClickListener {

            editTextPoids.text.clear()
            editTextTaille.text.clear()

            textViewImc.text = ""
            textViewCategorie.text = ""

            editTextPoids.requestFocus()
        }
    }
}