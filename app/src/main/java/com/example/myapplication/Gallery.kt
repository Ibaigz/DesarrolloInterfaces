package com.example.myapplication
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun GalleryScreen( navController: NavController, viewModel: GalleryViewModel ) {
    GalleryContent(viewModel.isSingleColumn, viewModel.artworks.value)
}

@Composable
fun GalleryContent(
    isSingleColumn: MutableState<Boolean>,
    artworks: List<Artwork>
) {
  LazyVerticalGrid(
        columns = if (isSingleColumn.value) GridCells.Fixed(1) else GridCells.Fixed(2), // Zutabe bat edo bi erakusten dituen aldaketa
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0.1f, 0.1f, 0.1f, 0.9f))
    ) {

        items(
            count = artworks.size, // Zerrendako elementu kopurua
            key = { index -> artworks[index].name } // Arte-lan bakoitzaren gako bakarra
        ) { index ->
            val artwork = artworks[index] // Uneko arte-lana lortu
            ArtworkCard(artwork) // Arte-lanaren txartela renderizatu
        }
    }

}
@Composable
fun ArtworkCard(artwork: Artwork) {

    // Artearen estiloaren arabera ikonoa aukeratu
    val iconResId = when (artwork.style) {
        ArtworkStyle.WATERCOLOUR -> R.drawable.instagram_simple_icon
        ArtworkStyle.DIGITAL -> R.drawable.github_seeklogo
        ArtworkStyle.INK -> R.drawable.linkedin_seeklogo
    }

    Box(
        modifier = Modifier
            .padding(6.dp) // Txartelaren inguruko padding-a
            .shadow(8.dp, RoundedCornerShape(16.dp)) // Itzala ertz biribilduekin
            .background(Color.White, RoundedCornerShape(16.dp)) // Atzealde zuria eta ertz biribilduak
            .border(2.dp, Color.Red, RoundedCornerShape(16.dp)) // Borde mehea
            .fillMaxWidth() // Txartelaren tamaina doitu
    ) {
        Column () {
            // Ikonoa irudiaren gainean jartzeko edukiontzia
            Box {
                // Arte-lanaren irudia
                Image(
                    painter = painterResource(id = artwork.imageResId),
                    contentDescription = "Arte-lanaren irudia",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth() // Zabalera osatu
                        .aspectRatio(1f) // 1:1 erlazioa, laukizuzena izateko
                        .clip(RoundedCornerShape(16.dp)) // Irudia biribildu
                )

                // Ikonoaren atzealdea (errekuadro gris iluna)
                Box(
                    modifier = Modifier
                        .size(36.dp) // Errekuadroaren tamaina
                        .background(Color(0xFF2C2C2C), RoundedCornerShape(8.dp)) // Atzealde gris iluna ertz biribilduekin
                        .align(Alignment.TopEnd) // Goiko eskuineko izkinan kokatu
                        .padding(8.dp) // Posizioa doitzen duen padding-a
                ) {

                    // Ikono gainjarritua
                    Icon(
                        painter = painterResource(id = iconResId),
                        contentDescription = "Arte-lanaren estiloaren ikonoa",
                        modifier = Modifier
                            .size(24.dp) // Ikonoaren tamaina
                            .align(Alignment.Center), // Ikonoa errekuadroaren barruan zentratu
                        tint = Color.White // Ikonoaren kolorea
                    )
                }
            }

            // Izenburuaren atzealdea
            Row(
                modifier = Modifier
                    .fillMaxWidth() // Zabalera osatu
                    .background(Color(0xFF2C2C2C), RoundedCornerShape(8.dp)) // Atzealde gris iluna ertz biribilduekin
                    .padding(8.dp) // Posizioa doitzen duen padding-a
            ) {
                // Arte-lanaren izenburua
                Text(
                    text = artwork.title,
                    style = MaterialTheme.typography.bodyLarge, // EB Garamond iturria erabilita
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(8.dp) // Izenburuaren inguruko padding-a
                    , color = Color.White // Testuaren kolorea
                )
            }

        }
    }
}


