package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.fragments.databinding.FragmentDiceRollerBinding
import com.example.fragments.databinding.FragmentPlayerNameBinding

class PlayerNameFragment : Fragment() {

    lateinit var binding:FragmentPlayerNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_player_name, container, false)
        binding = FragmentPlayerNameBinding.bind(view)

        binding.submit.setOnClickListener {
            if(binding.player1name.toString() != "" && binding.player2name.toString() != "") {
                sendData()
                activity?.onBackPressed()
            }
            else Toast.makeText(view?.context, "Invalid input", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    fun sendData(){
        setFragmentResult(
                MainActivity.MAIN_BUNDLE, bundleOf(
                MainActivity.PLAYER_1 to binding.player1name.text.toString(),           //նայ ասե՞մ ոնցաա ուղարկում էս պահը,,ինչ ուղարկել կայնի ջոգեմ վափշե ինչ անում
                MainActivity.PLAYER_2 to binding.player2name.text.toString()            //ոնցոր անունները գրումա սխմումա սբմիթ, մեյն ֆռագմենտի մեջ փոխվումա անունի
            )                                                                           //տեղի գրածները, այսքն պետքա սաղ ֆռագմենտներում փոխվի բայց չի փոխվում
        )
    }

    override fun onDetach() {
        super.onDetach()
        sendData()
    }
}