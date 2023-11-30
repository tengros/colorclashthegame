package com.example.colorclash

import android.content.Context

class DeckOfCards(mainActivity: MainActivity) {
    data class Card(val value: Int, val suit: String, val imageResource: Int)
}