package com.example.james.ghost.models

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

/**
 * Created by James on 2/27/2018.
 */
class Dream: Model {
    var title: String? = null
    var description: String? = null
    var tag: String? = null
    @ServerTimestamp
    var timestamp: Date? = null

    constructor() {}

    constructor(title: String, description: String, tag: String) {
        this.description = description
        this.title = title
        this.tag = tag
    }
}