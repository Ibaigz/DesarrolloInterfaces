package com.example.myapplication

import android.util.EventLogTags.Description
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {
    var isSingleColumn = mutableStateOf(false)  // Galeria zutabe batean edo bitan agertzen den kontrolatzen du
    var artworks = mutableStateOf(sampleArtworks()) // Arte-lanen zerrenda
}

private fun sampleArtworks(): List<Artwork> {

    return listOf(
        Artwork(
          name = "Taron",
          title = "Taron-Phantasialand",
          description = "Intamin multi launch coaster",
          creationDate = "2020/11/20",
          style = ArtworkStyle.WATERCOLOUR,
          imageResId = R.drawable.taron

        ),
        Artwork(
            name = "Untamed",
            title = "Untamed-WalibiHolland",
            description = "RMC Hybrid roller coaster",
            creationDate = "2020/11/20",
            style = ArtworkStyle.WATERCOLOUR,
            imageResId = R.drawable.untamed

        ),
    )

}
