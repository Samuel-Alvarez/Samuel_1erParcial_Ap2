package com.ucne.proj_1erparcial_ap2.ui.RegistroPerstamo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.*
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamoEntity
import com.ucne.proj_1erparcial_ap2.data.repository.PrestamoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class PrestamoUiState(
    val prestamoList: List<PrestamoEntity> = emptyList()
)

@HiltViewModel
class PrestamoViewModel @Inject constructor(
    private val prestamoRepository: PrestamoRepository
) : ViewModel() {
    var Deudor by mutableStateOf("Anonimo")
    var Concepto by mutableStateOf("Ingeniero")
    var Monto by mutableStateOf("30000")

    var conceptoError by mutableStateOf("")
    var montoError by mutableStateOf("")
    var deudorError by mutableStateOf("")

    var hayError = false

    var uiState = MutableStateFlow(PrestamoUiState())
        private set
    init {
        getListPrestamo()
    }
    fun getListPrestamo() {
        viewModelScope.launch(Dispatchers.IO) {
            prestamoRepository.getList().collect{lista ->
                uiState.update {
                    it.copy(prestamoList = lista)
                }
            }
        }
    }

    fun onDeudorChanged(Deudor: String) {
        this.Deudor = Deudor
        Validation()
    }
    fun onMontoChanged(Monto: String) {
        this.Monto = Monto
        Validation()
    }

    fun onConceptoChanged(Concepto: String) {
        this.Concepto = Concepto
        Validation()
    }

    fun insertar() {
        if (Validation())
            return

        val prestamo = PrestamoEntity(
            deudor = Deudor,
            concepto = Concepto,
            monto = Monto.toDoubleOrNull()?:0.0
        )

        viewModelScope.launch(Dispatchers.IO) {
            prestamoRepository.insert(prestamo)
            Limpiar()
        }
    }

    fun eliminar() {
        if (Validation())
            return

        val prestamo = PrestamoEntity(
            deudor = Deudor,
            concepto = Concepto,
            monto = Monto.toDoubleOrNull()?:0.0
        )

        viewModelScope.launch(Dispatchers.IO) {
            prestamoRepository.delete(prestamo)
            Limpiar()
        }
    }

    private fun Validation(): Boolean {

        deudorError = ""

        if (Deudor.isBlank()) {
            deudorError = "Debe indicar el deudor"
            hayError = true
        }else{
            hayError
        }

        conceptoError = ""

        if (Concepto.isBlank()) {
            conceptoError = "Debe indicar el concepto"
            hayError = true
        }else{
            hayError
        }

        montoError = ""

        if ((Monto.toDoubleOrNull() ?: 0.0) <= 0.0) {
            montoError = "Debe indicar un monto mayor que cero"
            hayError = true
        }else{
            hayError
        }

        return hayError
    }

    fun Limpiar() {
        Deudor.toString()?:null
        Concepto.toString()?:null
        Monto.toString()?:null
    }
}