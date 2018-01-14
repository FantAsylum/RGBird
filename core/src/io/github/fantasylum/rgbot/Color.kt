package io.github.fantasylum.rgbot

enum class Color {
    RED, GREEN, BLUE;

    fun next() = when (this) {
                     RED   -> GREEN
                     GREEN -> BLUE
                     BLUE  -> RED
                }
}
