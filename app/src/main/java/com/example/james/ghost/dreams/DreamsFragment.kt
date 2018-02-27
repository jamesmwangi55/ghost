package com.example.james.ghost.dreams


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.james.ghost.R


/**
 * A simple [Fragment] subclass.
 */
class DreamsFragment : Fragment() {

    companion object {
        fun newInstance(): DreamsFragment {
            val args =  Bundle()
            val dreamsFragment = DreamsFragment()
            dreamsFragment.arguments = args
            return dreamsFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dreams, container, false)
    }

}// Required empty public constructor
