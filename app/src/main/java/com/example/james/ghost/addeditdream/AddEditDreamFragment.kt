package com.example.james.ghost.addeditdream


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast

import com.example.james.ghost.R
import com.example.james.ghost.models.Dream
import com.example.james.ghost.models.Tag
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_add_edit_dream.*


/**
 * A simple [Fragment] subclass.
 */
class AddEditDreamFragment : Fragment() {

    // instantite firestore
    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    val DREAM_COLLECTION = "dreams"
    val TAGS_COLLECTION = "tags"
    val TAG = "AddEditDreamFragment"
    val tagsList = mutableListOf<String>()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        makeToast("Make sure device is connected to internet")

        firestore.collection("tags")
                .get()
                .addOnCompleteListener{
                    if (it.isSuccessful) {
                        val document = it.getResult()
                        val tags = document.toObjects(Tag::class.java)
                        tags.forEach({tag ->
                            run {
                                val checkBox = CheckBox(context)
                                checkBox.setText(tag.name)
                                checkBox.setOnClickListener {
                                    if (checkBox.isChecked) {
                                        tagsList.add(tag.name!!)
                                    } else {
                                        if(tagsList.contains(tag.name)) {
                                            tagsList.remove(tag.name)
                                        }
                                    }
                                }
                                linear_layout_tags.addView(checkBox)
                            }
                        })
                    } else {
                        Log.d(TAG, "get tasks failed with exception", it.exception)
                        makeToast("Get tasks failed check if you have an internet connection")
                    }
                }

        button_save_dream.setOnClickListener {
            val title = edit_text_title.text.toString()
            val description = edit_text_description.text.toString()
            val dream = Dream(title = title, description = description, tags = tagsList)

            val ref = firestore
                    .collection(DREAM_COLLECTION)
                    .document()

            dream.id = ref.id

            ref.set(dream)
                    .addOnSuccessListener {
                        makeToast("Item saved")
                    }
                    .addOnFailureListener { makeToast("Item save failed") }
        }
    }

    fun makeToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


}// Required empty public constructor
