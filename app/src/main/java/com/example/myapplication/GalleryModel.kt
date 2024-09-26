package com.example.myapplication

import androidx.activity.ComponentActivity

data class Artwork(
    val name: String,
    val title: String,
    val description: String,
    val creationDate: String,
    val style: ArtworkStyle,  // Arte estiloa definitzen duen enum-a
    val imageResId: Int // Irudiaren baliabidearen IDa (JPG)
)


enum class ArtworkStyle {
    WATERCOLOUR,  // Akuarela
    DIGITAL,      // Arte digitala
    INK           // Tinta
}
