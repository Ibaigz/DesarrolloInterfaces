import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.ContadorViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.myapplication.AddressFormatterViewModel

@Composable
fun ejercicioScreen( navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        contadorScreen(navController = NavController(LocalContext.current))
        ProgressScreen()
        AddressFormScreen()
        TodoListScreen()

}
}




@Composable
fun contadorScreen(navController: NavController, counterViewModel: ContadorViewModel = viewModel()) {
    // Observar el estado del contador desde el ViewModel
    val counter by counterViewModel.contador

    // UI
    Column (modifier = Modifier.padding(top = 128.dp)) {
        Text(text = "Contador: $counter", style = MaterialTheme.typography.headlineMedium)
        Button(modifier = Modifier.padding(top = 16.dp),
            onClick = { counterViewModel.incrementCounter() }) {
            Text(text = "Incrementar")
        }
        Button(modifier = Modifier.padding(top = 16.dp),
            onClick = { counterViewModel.decrementCounter() }) {
            Text(text = "Decrementar")
        }
    }

}

@Composable
fun ProgressScreen(progressViewModel: ProgressViewModel = viewModel()) {
    // Observar el estado del progreso desde el ViewModel
    val progress by progressViewModel.progress

    // UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Progreso: ${(progress * 100).toInt()}%", style = MaterialTheme.typography.headlineMedium)
        CircularProgressIndicator(progress = progress, modifier = Modifier.width(240.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { progressViewModel.incrementProgress() }) {
            Text(text = "Incrementar Progreso")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { progressViewModel.resetProgress() }) {
            Text(text = "Reiniciar Progreso")
        }
    }
}

@Composable
fun AddressFormScreen(addressFormatterViewModel: AddressFormatterViewModel = viewModel()) {
    // Observar los campos y el formato seleccionado desde el ViewModel
    val street by addressFormatterViewModel.street
    val city by addressFormatterViewModel.city
    val postalCode by addressFormatterViewModel.postalCode
    val selectedFormat by addressFormatterViewModel.selectedFormat
    val formattedAddress by remember { derivedStateOf { addressFormatterViewModel.getFormattedAddress() } }

    // UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Entrada de dirección
        TextField(
            value = street,
            onValueChange = { addressFormatterViewModel.onStreetChange(it) },
            label = { Text("Calle") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = city,
            onValueChange = { addressFormatterViewModel.onCityChange(it) },
            label = { Text("Ciudad") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = postalCode,
            onValueChange = { addressFormatterViewModel.onPostalCodeChange(it) },
            label = { Text("Código Postal") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // RadioButtons para seleccionar el formato
        Text(text = "Formato de dirección:", style = MaterialTheme.typography.headlineMedium)
        Row {
            RadioButton(
                selected = selectedFormat == 0,
                onClick = { addressFormatterViewModel.onFormatChange(0) }
            )
            Text(text = "Una línea")
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = selectedFormat == 1,
                onClick = { addressFormatterViewModel.onFormatChange(1) }
            )
            Text(text = "Multilinea")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar dirección formateada
        Text(text = "Dirección formateada:",  style = MaterialTheme.typography.headlineMedium)
        Text(
            text = formattedAddress,
            modifier = Modifier.padding(bottom = 100.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun TodoListScreen(todoViewModel: TodoViewModel = viewModel()) {
    var newTask by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Campo de entrada para nuevas tareas
        TextField(
            value = newTask,
            onValueChange = { newTask = it },
            label = { Text("Nueva tarea") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para agregar la tarea
        Button(
            onClick = {
                todoViewModel.addTask(newTask)
                newTask = ""
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Agregar tarea")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar la lista de tareas
        todoViewModel.tasks.forEach { task ->
            TaskItem(
                task = task,
                onCheckedChange = { todoViewModel.toggleTaskChecked(task) },
                onRemove = { todoViewModel.removeTask(task) }
            )
        }
    }
}

@Composable
fun TaskItem(task: Task, onCheckedChange: () -> Unit, onRemove: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Checkbox(
                checked = task.isChecked,
                onCheckedChange = { onCheckedChange() } // Invoca la función cuando se cambia el checkbox
            )
            Text(
                text = task.title,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        IconButton(onClick = onRemove) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar tarea")
        }
    }
}




@Preview
@Composable
fun PreviewCounterScreen() {
    contadorScreen( navController = NavController(LocalContext.current))
}