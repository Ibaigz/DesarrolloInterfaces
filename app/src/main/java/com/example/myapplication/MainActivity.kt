package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import contadorScreen
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import ejercicioScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                ViewContainer(innerPadding = PaddingValues())
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ViewContainer(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination?.route

    // Define a title based on the current destination
    val title = when (currentDestination) {
        "home" -> "Ibai Gonzalez Portfolio"
        "info" -> "Info"
        "gallery" -> "Gallery"
        "settings" -> "Settings"
        else -> "Ibai Gonzalez Portfolio" // Default title
    }

    val viewModel: GalleryViewModel = viewModel()

    Scaffold(
        topBar = { ToolBar(title) }, // Pass the dynamic title
        content = {

            // Integrating MainScreen's NavHost
            NavHost(navController = navController, startDestination = "home",modifier = Modifier.padding(innerPadding)) {
                composable("home") { ejercicioScreen(navController) }
                composable("info") { InfoScreen(navController) }
                composable("gallery") { GalleryScreen(navController, viewModel) }
                composable("settings") { SettingsScreen(navController) }
            }
        },
        //floatingActionButton = { FAB() },
        floatingActionButtonPosition = FabPosition.End,
        //bottomBar = { BottomNavBar(navController) }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(title: String) {
    val context = LocalContext.current

    TopAppBar(
        title = { Text(text = title) },
        modifier = Modifier.background(MaterialTheme.colorScheme.primary) ,
        actions = {
            IconButton(onClick = {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "https://www.ejemplo.com")
                }
                // Show the share chooser
                context.startActivity(Intent.createChooser(shareIntent, "Compartir enlace"))
            }) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "Share"
                )
            }
        },
    )
}


@Composable
fun BottomNavBar(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            selected = navController.currentBackStackEntry?.destination?.route == "home",
            onClick = {
                navController.navigate("home") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Info, contentDescription = "Info") },
            selected = navController.currentBackStackEntry?.destination?.route == "info",
            onClick = {
                navController.navigate("info") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Edit, contentDescription = "Gallery") },
            selected = navController.currentBackStackEntry?.destination?.route == "gallery",
            onClick = {
                navController.navigate("gallery") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
            selected = navController.currentBackStackEntry?.destination?.route == "settings",
            onClick = {
                navController.navigate("settings") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}


@Composable
fun FAB() {
    val context = LocalContext.current
    FloatingActionButton(onClick = { Toast.makeText(context, "Email", Toast.LENGTH_SHORT).show() })
    {
        Text("+")
    }


}

@Composable
fun App() {
    ViewContainer(innerPadding = PaddingValues())
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 120.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.man_person_icon),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(240.dp)
                    .clip(
                        CircleShape
                    )
                    .border(2.dp, Color.Black, CircleShape)
            )

            Row(
                modifier = Modifier.padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Ibai González", fontSize = 40.sp)

            }

            Bio()
            AboutMe()
            Rrss()

        }

    }

}

@Composable
fun Bio() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
        .padding(vertical = 2.dp)) {
        Text(
            text = "Kaixo Ibai naiz, 19 urte ditut, bideojokoak asko gustatzen zaizkit eta musika asko gustatzen zait",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun AboutMe() {
    Column(modifier = Modifier.padding(vertical = 20.dp)) {
        Column(modifier = Modifier.padding(vertical = 15.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_school_24),
                    contentDescription = "Icono Colegio",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(horizontal = 1.dp)
                )


                Column {
                    Text(text = "Education", fontSize = 20.sp)
                    Text(text = "Frontend Dev")
                }
            }
        }
        Column(modifier = Modifier.padding(vertical = 15.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_school_24),
                    contentDescription = "Icono Colegio",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(horizontal = 1.dp)
                )


                Column {
                    Text(text = "Sport", fontSize = 20.sp)
                    Text(text = "Sleep")
                }
            }
        }
        Column(modifier = Modifier.padding(vertical = 15.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_school_24),
                    contentDescription = "Icono Colegio",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(horizontal = 1.dp)
                )


                Column {
                    Text(text = "Fav Food", fontSize = 20.sp)
                    Text(text = "Grandma's")
                }
            }
        }
        Column(modifier = Modifier.padding(vertical = 15.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_school_24),
                    contentDescription = "Icono Colegio",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(horizontal = 1.dp)
                )


                Column {
                    Text(text = "Hobby", fontSize = 20.sp)
                    Text(text = "Djing")
                }
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {


}


@Composable
fun SettingsScreen(navController: NavController) {
    // Settings pantailaren edukia
}

@Composable
fun Rrss() {
    val context = LocalContext.current
    Row(
        modifier = Modifier.padding(vertical = 30.dp),
        horizontalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.github_seeklogo),
            contentDescription = "Icono colegio",
            modifier = Modifier
                .size(40.dp)
                .clickable
                {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Ibaigz"))
                    context.startActivity(intent)
                }
        )
        Image(
            painter = painterResource(id = R.drawable.linkedin_seeklogo),
            contentDescription = "Icono colegio",
            modifier = Modifier
                .size(40.dp)
                .clickable
                {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/in/ibai-gonzalez-molina-776432257/")
                    )
                    context.startActivity(intent)
                }
        )
        Image(
            painter = painterResource(id = R.drawable.instagram_simple_icon),
            contentDescription = "Icono colegio",
            modifier = Modifier.size(40.dp)
        )
    }
}


@Composable
@Preview
fun AppPreview() {
    App()
    Bio()
    AboutMe()
    Rrss()
    FAB()
}






