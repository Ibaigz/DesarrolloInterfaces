import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class Task(val title: String, var isChecked: Boolean = false)

class TodoViewModel : ViewModel() {
    // Lista de tareas
    private val _tasks = mutableStateListOf<Task>()
    val tasks: List<Task> = _tasks

    // Función para agregar una tarea
    fun addTask(taskTitle: String) {
        if (taskTitle.isNotBlank()) {
            _tasks.add(Task(taskTitle))
        }
    }

    // Función para eliminar una tarea
    fun removeTask(task: Task) {
        _tasks.remove(task)
    }

    // Función para cambiar el estado de completado
    fun toggleTaskChecked(task: Task) {
        val index = _tasks.indexOf(task)
        if (index >= 0) {
            _tasks[index] = task.copy(isChecked = !task.isChecked) // Crea una copia con el nuevo estado
        }
    }
}
