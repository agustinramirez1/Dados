package com.example.dados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val numerosGuardados = mutableListOf<Int>()
    private lateinit var viewNumerosGuardados: TextView
    private lateinit var buttonLanzar: Button
    private var clickCounter = 0
    private lateinit var message: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val start = 1
        val end = 2
        buttonLanzar = findViewById(R.id.button_lanzar)
        val imageDados: ImageView = findViewById(R.id.image_dados)
        message = findViewById(R.id.text_message)
        var possibilities = listOf<String>("12345", "23456", "34561", "54321", "65432", "16543")

        viewNumerosGuardados = findViewById(R.id.textViewNumerosGuardados)

        buttonLanzar.setOnClickListener {
            if (clickCounter < 5) {
                val numero = rand(start, end)
                numerosGuardados.add(numero)

                when (numero) {
                    1 -> imageDados.setImageResource(R.drawable.dice_1)
                    2 -> imageDados.setImageResource(R.drawable.dice_2)
                    3 -> imageDados.setImageResource(R.drawable.dice_3)
                    4 -> imageDados.setImageResource(R.drawable.dice_4)
                    5 -> imageDados.setImageResource(R.drawable.dice_5)
                    6 -> imageDados.setImageResource(R.drawable.dice_6)
                }
                viewNumerosGuardados.text = numerosGuardados.toString()
                clickCounter++
                if (clickCounter == 5) {
                    for (i in numerosGuardados.distinct()) {
                        if (numerosGuardados.count {it == i} == 5) {
                            message.text = "TU HERMANA"
                        } else if (numerosGuardados.count { it == i } == 4) {
                            message.text = "POKER"
                        } else if (numerosGuardados.count { it == i } == 3) {
                            for (i in numerosGuardados.distinct()) {
                                if (numerosGuardados.count { it == i } == 2) {
                                    message.text = "FULL"
                                }
                            }
                        }
                    }
                    if (message.text == "") {
                        message.text = if (possibilities.contains(numerosGuardados.joinToString())) "ESCALERA" else "SIGA PARTICIPANDO"
                    }
                    buttonLanzar.text = "Jugar de Nuevo"
                }
            } else {
                clickCounter = 0
                viewNumerosGuardados.text = ""
                numerosGuardados.clear()
                buttonLanzar.text = "Lanzar Dado"
                message.text = ""
            }
        }
    }

    fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }
}