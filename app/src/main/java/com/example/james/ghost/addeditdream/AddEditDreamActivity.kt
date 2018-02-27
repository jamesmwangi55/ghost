package com.example.james.ghost.addeditdream

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.james.ghost.SingleFragmentActivity
import com.google.firebase.firestore.FirebaseFirestore

class AddEditDreamActivity : SingleFragmentActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, AddEditDreamActivity::class.java)
            return intent
        }
    }

    override fun createFragment(): Fragment {
        return AddEditDreamFragment.newInstance()
    }





}
