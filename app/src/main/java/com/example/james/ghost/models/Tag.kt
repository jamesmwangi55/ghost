package com.example.james.ghost.models

/**
 * Created by James on 2/27/2018.
 */
class Tag: Model {
    var name: String? = null

    constructor() {}

    constructor(name: String) {
        this.name = name
    }
}