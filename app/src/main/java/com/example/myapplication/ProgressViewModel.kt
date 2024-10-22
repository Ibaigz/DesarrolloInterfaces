import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class ProgressViewModel : ViewModel() {
    // Estado de progreso entre 0.0 y 1.0
    private val _progress = mutableStateOf(0f)
    val progress: State<Float> = _progress

    // Función para incrementar el progreso
    fun incrementProgress() {
        if (_progress.value < 1f) {
            _progress.value += 0.1f
        }
    }

    // Función para reiniciar el progreso
    fun resetProgress() {
        _progress.value = 0f
    }
}