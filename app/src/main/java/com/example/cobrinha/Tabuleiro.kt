package com.example.cobrinha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.GridLayout
import android.widget.ImageView
import com.example.cobrinha.databinding.ActivityTabuleiroBinding

class Tabuleiro : AppCompatActivity() {
    lateinit var binding: ActivityTabuleiroBinding
    val LINHA = 36
    val COLUNA = 26
    var running = true
    var speed = longArrayOf(500,100)
    var speed_value = 0


    var cobra = Cobra(1)

    inner class Ponto(var x: Int, var y: Int) {
        fun moveDown() {
            x++
        }
    }

    var board = Array(LINHA) {
        Array(COLUNA) { 0 }
    }
    var boardView = Array(LINHA) {
        arrayOfNulls<ImageView>(COLUNA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tabuleiro)


        var gridboard = findViewById<GridLayout>(R.id.gridboard)
        gridboard.rowCount = LINHA
        gridboard.columnCount = COLUNA

        val inflater = LayoutInflater.from(this)
        for (i in 0 until LINHA) {
            for (j in 0 until COLUNA) {
                boardView[i][j] =
                    inflater.inflate(R.layout.inflate_image_view, gridboard, false) as ImageView
                gridboard.addView(boardView[i][j])
            }
        }
        gameRun()
    }

    fun pauseGame() {
        if (running) {
            running = false;
            var intent = Intent(this, MainActivity::class.java);
            intent.putExtra("pause", true);
            startActivityForResult(intent, 7);
        } else {
            running = true;
            gameRun();
        }
    }


    fun gameRun() {
        Thread {
            while (running) {
                Thread.sleep(speed[speed_value])
                runOnUiThread {
                    for (i in 0 until LINHA) {
                        for (j in 0 until COLUNA) {
                            if ((i == 0 || i == LINHA - 1) || (j == 0 || j == COLUNA - 1)) {
                                boardView[i][j]!!.setImageResource(R.drawable.dourado)
                                board[i][j] = 2
                            } else if (board[i][j] == 1) {
                                boardView[i][j]!!.setImageResource(R.drawable.verde)
                            } else {
                                boardView[i][j]!!.setImageResource(R.drawable.verdoso)
                            }
                        }
                    }

                    try {
                        for (i in 0 until cobra.linha) {
                            for (j in 0 until cobra.coluna) {
                                if (cobra.peca[i][j] == 1) {
                                    boardView[cobra.x + i][cobra.y + j]!!.setImageResource(R.drawable.verde)
                                }
                            }
                        }
                    } catch (e: ArrayIndexOutOfBoundsException) {
                        running = false
                    }
                }
            }
        }.start()
    }
}