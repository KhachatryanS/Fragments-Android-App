package com.example.fragments

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentResultListener
import com.example.fragments.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    companion object {
        const val PLAYER_1 = "player1"
        const val PLAYER_2 = "player2"
        const val MAIN_BUNDLE = "mainBundle"
        const val DICE_ROLLER_BUNDLE = "diceRoller"
        const val TIC_TAC_TOE = "ticTacToe"
        var score1 = 0
        var score2 = 0

    }
    lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        supportFragmentManager
                .beginTransaction()
                .add(R.id.main, MainFragment())
                .addToBackStack("main")
                .commit()

    }
}