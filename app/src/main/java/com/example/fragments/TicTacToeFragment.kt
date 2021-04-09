package com.example.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.tictactoe.TicTacToe


class TicTacToeFragment: Fragment(){

    lateinit var board:List<Button>
    lateinit var playAgainButton: Button
    lateinit var turnTextView: TextView
    var player1 = "Player 1"
    var player2 = "Player 2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tictactoe, container, false)

        playAgainButton = view.findViewById(R.id.playAgain)
        turnTextView = view.findViewById(R.id.turnTextView)
        board = listOf(
                view.findViewById(R.id.zero), view.findViewById(R.id.one), view.findViewById(R.id.two),
                view.findViewById(R.id.three), view.findViewById(R.id.four), view.findViewById(R.id.five),
                view.findViewById(R.id.six), view.findViewById(R.id.seven), view.findViewById(R.id.eight)
        )

        setFragmentResultListener(MainActivity.TIC_TAC_TOE){ _, bundle ->
            player1 = bundle.getString(MainActivity.PLAYER_1).toString()
            player2 = bundle.getString(MainActivity.PLAYER_2).toString()

            turnTextView.text = "$player1\n->X<-"
        }

        var game = TicTacToe()

        view.findViewById<Button>(R.id.back).setOnClickListener {
            activity?.onBackPressed()
        }

        var flag = true
        for (i in board.indices){
            board[i].setOnClickListener {
                if(game.makeStep(i) && flag) {
                    if (game.isXTurn()) {
                        turnTextView.text = "$player1\n->X<-"
                        board[i].text = game.O.toString()
                    } else {
                        turnTextView.text = "$player2\n->O<-"
                        board[i].text = game.X.toString()
                    }
                    when (game.getWinner()) {
                        game.X -> {
                            turnTextView.text = "WINS\n->X<-"
                            flag = false
                            MainActivity.score1++
                        }
                        game.O -> {
                            turnTextView.text = "WINS\n->O<-"
                            MainActivity.score2++
                            flag = false
                        }
                        game.NO_ONE -> {
                            turnTextView.text = "WINS\n->NO ONE<-"
                            flag = false
                        }
                    }
                }

            }
        }
        playAgainButton.setOnClickListener {
            game = TicTacToe()
            flag = true
            turnTextView.text = "$player1\n->X<-"
            for (i in board.indices)
                board[i].text = ""
        }


        return view
    }


    override fun onDetach() {
        super.onDetach()
        setFragmentResult(
                MainActivity.MAIN_BUNDLE, bundleOf(
                MainActivity.PLAYER_1 to player1,
                MainActivity.PLAYER_2 to player2
        )
        )
    }
}