package com.example.james.ghost.models

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

/**
 * Created by James on 2/27/2018.
 */
class Dream: Model {
    var title: String? = null
    var description: String? = null
    var tags: MutableList<String> = mutableListOf()
    @ServerTimestamp
    var timestamp: Date? = null

    constructor() {}

    constructor(title: String, description: String, tags: MutableList<String>) {
        this.description = description
        this.title = title
        this.tags = tags
    }
}