package io.github.fantasylum.rgbot.util

import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.utils.Array

/**
    * NOTE: this method is NOT pure. Initial container modified
    *       after use. You free to use only this side-effect
    */
fun <T> Array<T>.filter(predicate: (T) -> Boolean): Array<T> {
    with (iterator()) {
        while (hasNext()) {
            val item = next()
            if (! predicate(item))
                remove()
        }
    }
    return this

}

fun Button.setOnClickListener(callback: () -> Unit) {
    // remove all clicklisteners
    listeners.filter { it !is ClickListener }

    addListener(object : ClickListener() {
        override fun clicked(event: InputEvent, x: Float, y: Float) {
            callback()
        }
    })
}
