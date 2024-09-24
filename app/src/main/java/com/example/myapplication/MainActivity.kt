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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           App()
            ViewContainer()
            }
        }
    }


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun ViewContainer(){
    Scaffold(
        topBar = { ToolBar()},
        content = { paddingValues -> // Contenido que puede desplazarse
            Column(
                modifier = Modifier
                    .padding(paddingValues) // Respeta las barras superior e inferior
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()) // Habilita el scroll vertical
            ) {
                App() // El contenido de tu aplicación
            }
        },
        floatingActionButton={ FAB() },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = { BottomAppBar() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(){
    val context= LocalContext.current

    TopAppBar(
        title = { Text(text = "Ibai Gonzalez Portfolio") },
        Modifier.background(colorResource(id = R.color.teal_200)),
        actions = {
            IconButton(onClick = {  val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "https://www.ejemplo.com")
            }
                // Mostrar el selector para compartir
                context.startActivity(Intent.createChooser(shareIntent, "Compartir enlace"))
            }
            ) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "Localized description"
                )
            }
        },


    )


}


@Composable
fun BottomAppBar() {
    val context = LocalContext.current

    BottomAppBar(
        modifier = Modifier.height(90.dp).background(color = Color.Gray),

        contentColor = Color.Black,
    ) {
        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("destinatario@ejemplo.com"))
            }
        }) {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "Enviar correo"
            )
        }
    }
}
@Composable
fun FAB(){
    val context= LocalContext.current
    FloatingActionButton(onClick = {Toast.makeText(context, "Email", Toast.LENGTH_SHORT).show() })
    {
       Text("+")
    }


}

@Composable
fun App(){
    Column(modifier = Modifier.fillMaxSize().padding(vertical = 30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter= painterResource(id= R.drawable.man_person_icon) , contentDescription="Logo", modifier = Modifier.size(240.dp).clip(
            CircleShape).border(2.dp, Color.Black, CircleShape))

        Row(modifier = Modifier.padding(vertical = 10.dp), horizontalArrangement = Arrangement.Center ){
            Text(text = "Ibai González", fontSize = 40.sp,)

        }

        Bio()
        AboutMe()
        Rrss()

    }

}

@Composable
fun Bio(){
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).padding(vertical=2.dp)) {
        Text(text = "Kaixo Ibai naiz, 19 urte ditut, bideojokoak asko gustatzen zaizkit eta musika asko gustatzen zait", fontSize = 20.sp, textAlign = TextAlign.Center)
    }
}

@Composable
fun AboutMe(){
    Column (modifier = Modifier.padding(vertical = 20.dp)) {
    Column (modifier = Modifier.padding(vertical = 15.dp)) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
            Image(
                painter = painterResource(id = R.drawable.baseline_school_24),
                contentDescription = "Icono Colegio",
                modifier = Modifier.size(40.dp).padding(horizontal = 1.dp)
            )


            Column {
                Text(text = "Education", fontSize = 20.sp,)
                Text(text = "Frontend Dev")
            }
        }
    }
    Column (modifier = Modifier.padding(vertical = 15.dp)) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
            Image(
                painter = painterResource(id = R.drawable.baseline_school_24),
                contentDescription = "Icono Colegio",
                modifier = Modifier.size(40.dp).padding(horizontal = 1.dp)
            )


            Column {
                Text(text = "Sport", fontSize = 20.sp,)
                Text(text = "Sleep")
            }
        }
    }
    Column (modifier = Modifier.padding(vertical = 15.dp)) {
    Row (modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
        Image(painter = painterResource(id = R.drawable.baseline_school_24), contentDescription = "Icono Colegio", modifier = Modifier.size(40.dp).padding(horizontal = 1.dp))


        Column {
            Text(text="Fav Food", fontSize = 20.sp,)
            Text(text="Grandma's")
        }
    }
    }
    Column (modifier = Modifier.padding(vertical = 15.dp)) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
            Image(
                painter = painterResource(id = R.drawable.baseline_school_24),
                contentDescription = "Icono Colegio",
                modifier = Modifier.size(40.dp).padding(horizontal = 1.dp)
            )


            Column {
                Text(text = "Hobby", fontSize = 20.sp,)
                Text(text = "Djing")
            }
        }
    }
    }
}

@Composable
fun Rrss(){
    val context = LocalContext.current
    Row (modifier = Modifier.padding(vertical = 30.dp), horizontalArrangement = Arrangement.spacedBy(40.dp)) {
        Image(
            painter = painterResource(id = R.drawable.github_seeklogo),
            contentDescription = "Icono colegio",
            modifier = Modifier.size(40.dp).clickable
            {val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Ibaigz"))
                context.startActivity(intent) }
        )
        Image(
            painter = painterResource(id = R.drawable.linkedin_seeklogo),
            contentDescription = "Icono colegio",
            modifier = Modifier.size(40.dp).clickable
            {val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/ibai-gonzalez-molina-776432257/"))
                context.startActivity(intent) }
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
fun AppPreview(){
    App()
    Bio()
    AboutMe()
    Rrss()
    FAB()
    BottomAppBar()
}






