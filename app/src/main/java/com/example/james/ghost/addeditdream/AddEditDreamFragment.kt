package com.example.james.ghost.addeditdream


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.james.ghost.R


/**
 * A simple [Fragment] subclass.
 */
class AddEditDreamFragment : Fragment() {


    companion object {
        fun newInstance(): AddEditDreamFragment {
            val args = Bundle()
            val addEditDreamFragment = AddEditDreamFragment()
            addEditDreamFragment.arguments = args
            return addEditDreamFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_edit_dream, container, false)
    }

}// Required empty public constructor
