package com.example.james.ghost.dreams


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.james.ghost.R
import com.example.james.ghost.addeditdream.AddEditDreamActivity
import com.example.james.ghost.models.Dream
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.dream_item.view.*
import kotlinx.android.synthetic.main.fragment_dreams.*


/**
 * A simple [Fragment] subclass.
 */
class DreamsFragment : Fragment() {

    private var options: FirestoreRecyclerOptions<Dream>? = null
    private var adapter: FirestoreRecyclerAdapter<Dream, DreamViewHolder>? = null

    // instantite firestore
    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    val DREAM_COLLECTION = "dreams"
    val TAG = "DreamsFragment"

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab_open_add_edit_dream.setOnClickListener {
            startActivity(AddEditDreamActivity.newIntent(this.context!!))
        }

        val query: Query = firestore
                .collection(DREAM_COLLECTION)
                .orderBy("timestamp")
                .limit(50)

        query.addSnapshotListener{snapshot, e -> run {
            if (e != null) {
                Log.d(TAG, "Error when getting dreams", e)
                makeToast("Error when getting dreams")
            } else {
                val dreams = snapshot.toObjects(Dream::class.java)
                options = FirestoreRecyclerOptions.Builder<Dream>()
                        .setLifecycleOwner(this)
                        .setQuery(query, Dream::class.java)
                        .build()

                adapter = object : FirestoreRecyclerAdapter<Dream, DreamViewHolder>(options as FirestoreRecyclerOptions<Dream>) {
                    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DreamViewHolder {
                        val viewItem = LayoutInflater.from(parent!!.context)
                                .inflate(R.layout.dream_item,parent, false)
                        return DreamViewHolder(viewItem)
                    }

                    override fun onBindViewHolder(holder: DreamViewHolder, position: Int, model: Dream) {
                        holder.bindItem(dream = model)
                    }

                }

                recycler_view_dreams.layoutManager = LinearLayoutManager(context)
                recycler_view_dreams.adapter = adapter

            }
        }}

    }

    fun makeToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    class DreamViewHolder(private val view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            view.setOnClickListener(this)
        }

        fun bindItem(dream: Dream) {
            view.dream_text_view_description.text = dream.description
            view.dream_text_view_title.text = dream.title
        }

        override fun onClick(v: View?) {

        }

    }

}// Required empty public constructor
