
package com.example.james.ghost.models

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

/**
 * A Base Model to be extended by other models to add ids.
 */

@IgnoreExtraProperties
open class Model {
    @Exclude
    var id: String? = null

    @Suppress("UNCHECKED_CAST")
    fun <T : Model> withId(id: String): T {
        this.id = id
        return this as T
    }
}
