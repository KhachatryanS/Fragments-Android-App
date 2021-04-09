package com.example.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}