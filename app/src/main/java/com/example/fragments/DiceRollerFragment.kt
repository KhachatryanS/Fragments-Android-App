package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.fragments.databinding.FragmentDiceRollerBinding


class DiceRollerFragment : Fragment() {

    private val imagesList = listOf(R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six)
    lateinit var binding: FragmentDiceRollerBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_dice_roller, container, false)
        binding = FragmentDiceRollerBinding.bind(view)

        val animation: Animation = AnimationUtils.loadAnimation(
            this.context, R.anim.dice_rotate)

        binding.back.setOnClickListener {
            activity?.onBackPressed()
        }

        val dice1Image = binding.dice1
        val dice2Image = binding.dice2

        view.findViewById<Button>(R.id.clickMe).setOnClickListener {
            dice1Image.startAnimation(animation)
            dice2Image.startAnimation(animation)
            Thread(Runnable {
                val first = (0..5).random()
                val second = (0..5).random()
                if(first > second) MainActivity.score1++ else MainActivity.score2++
                Thread.sleep(500)
                dice1Image.setImageResource(imagesList[first])
                dice1Image.startAnimation(animation)
                dice2Image.setImageResource(imagesList[second])
                dice2Image.startAnimation(animation)
            }).start()
        }

        setFragmentResultListener(MainActivity.DICE_ROLLER_BUNDLE){ _, bundle ->
            binding.player1DiceRoller.text = bundle.getString(MainActivity.PLAYER_1)
            binding.player2DiceRoller.text = bundle.getString(MainActivity.PLAYER_2)
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        setFragmentResult(
                MainActivity.MAIN_BUNDLE, bundleOf(
                MainActivity.PLAYER_1 to binding.player1DiceRoller.text.toString(),           //նայ ասե՞մ ոնցաա ուղարկում էս պահը,,ինչ ուղարկել կայնի ջոգեմ վափշե ինչ անում
                MainActivity.PLAYER_2 to binding.player2DiceRoller.text.toString()            //ոնցոր անունները գրումա սխմումա սբմիթ, մեյն ֆռագմենտի մեջ փոխվումա անունի
            )                                                                           //տեղի գրածները, այսքն պետքա սաղ ֆռագմենտներում փոխվի բայց չի փոխվում
        )
    }


}