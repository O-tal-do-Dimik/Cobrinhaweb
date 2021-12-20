package com.example.cobrinha

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.cobrinha.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        var params = intent.extras
        var continuar = params?.getBoolean("pause")

        if(continuar == true){
            binding.botaocontinuar.visibility = View.VISIBLE
        }
        else{
            binding.botaocontinuar.visibility = View.INVISIBLE
        }


        binding.apply {
            botaonovojogo.setOnClickListener {
                var intent = Intent(this@MainActivity, Tabuleiro::class.java)
                startActivity(intent)
            }
            botaoconfiguracao.setOnClickListener {
                val intent = Intent(this@MainActivity, Configuracao::class.java)
                startActivityForResult(intent, 3)
            }

            botaocontinuar.setOnClickListener{
                val intent = Intent()
                setResult(Activity.RESULT_OK, intent)
                finish()
            }

        }

    }
}