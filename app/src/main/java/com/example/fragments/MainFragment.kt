package com.example.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.Navigation
import com.example.fragments.databinding.FragmentMainBinding

class MainFragment: Fragment(){

    lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)
        binding = FragmentMainBinding.bind(view)

        binding.ticTacToe.setOnClickListener {
            setFragmentResult(
                    MainActivity.TIC_TAC_TOE, bundleOf(
                    MainActivity.PLAYER_1 to binding.player1.text,
                    MainActivity.PLAYER_2 to binding.player2.text
                )
            )
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_ticTacToeFragment2)
        }

        binding.diceRoller.setOnClickListener {
            setFragmentResult(
                    MainActivity.DICE_ROLLER_BUNDLE, bundleOf(
                    MainActivity.PLAYER_1 to binding.player1.text,
                    MainActivity.PLAYER_2 to binding.player2.text
                )
            )
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_diceRollerFragment)
        }

        binding.addPlayerName.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_playerNameFragment)
        }

        setFragmentResultListener(MainActivity.MAIN_BUNDLE){_, bundle ->
            binding.player1.text = bundle.getString(MainActivity.PLAYER_1).toString()
            binding.player2.text = bundle.getString(MainActivity.PLAYER_2).toString()
        }

        return view

    }
    override fun onResume() {
        super.onResume()

        binding.player1score.text = MainActivity.score1.toString()
        binding.player2score.text = MainActivity.score2.toString()
    }
}