package com.ucne.proj_1erparcial_ap2.ui.theme.RegistroPerstamo

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun PrestamoScreen(){
    Column {
        Text(text = "Prestamos")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Deudor(viewModel: PrestamoViewModel ){
    Column {
        Text(text = "Deudor")

        OutlinedTextField(value = "",
            onValueChange = {   })

    }
}


