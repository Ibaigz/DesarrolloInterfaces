package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class AddressFormatterViewModel : ViewModel() {
    // Campos de dirección
    private val _street = mutableStateOf("")
    val street: State<String> = _street

    private val _city = mutableStateOf("")
    val city: State<String> = _city

    private val _postalCode = mutableStateOf("")
    val postalCode: State<String> = _postalCode

    // Formato seleccionado (0: Una línea, 1: Multilinea)
    private val _selectedFormat = mutableStateOf(0)
    val selectedFormat: State<Int> = _selectedFormat

    // Funciones para actualizar los campos
    fun onStreetChange(newStreet: String) {
        _street.value = newStreet
    }

    fun onCityChange(newCity: String) {
        _city.value = newCity
    }

    fun onPostalCodeChange(newPostalCode: String) {
        _postalCode.value = newPostalCode
    }

    // Función para cambiar el formato seleccionado
    fun onFormatChange(format: Int) {
        _selectedFormat.value = format
    }

    // Función para formatear la dirección según el formato seleccionado
    fun getFormattedAddress(): String {
        return if (_selectedFormat.value == 1) {
            // Multilinea
            "${_street.value}\n${_city.value}\n${_postalCode.value}"
        } else {
            // Una línea
            "${_street.value}, ${_city.value}, ${_postalCode.value}"
        }
    }
}