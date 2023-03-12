package com.ucne.proj_1erparcial_ap2.ui.RegistroPerstamo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

import androidx.compose.material.icons.twotone.Error
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamoEntity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrestamoScreen(viewModel: PrestamoViewModel = hiltViewModel()) {
    Column(Modifier.fillMaxSize()) {
        PrestamoBody(viewModel)
        val uiState by viewModel.uiState.collectAsState()
        PrestamoListScreen(uiState.prestamosList)

    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PrestamoBody(
    viewModel: PrestamoViewModel
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Registro de Prestamos",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.deudor,
            onValueChange = viewModel::onDeudorChanged,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = {
                Text(
                    text = "Deudor",
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
            },
            isError = viewModel.deudorError.isNotBlank(),
            trailingIcon = {
                if(viewModel.deudorError.isNotBlank()){
                    androidx.compose.material3.
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (viewModel.deudorError.isNotBlank()) {
            Text(
                text = viewModel.deudorError,
                color = MaterialTheme.colorScheme.error
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.concepto,
            onValueChange =viewModel::onConceptoChanged,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.TextSnippet,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = {
                Text(
                    "Concepto",
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
            },
            isError = viewModel.conceptoError.isNotBlank(),
            trailingIcon = {
                if(viewModel.conceptoError.isNotBlank()){
                    androidx.compose.material3.
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (viewModel.conceptoError.isNotBlank()) {
            Text(
                text = viewModel.conceptoError,
                color = MaterialTheme.colorScheme.error
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.monto,
            onValueChange = viewModel::onMontoChanged,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.AttachMoney,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = {
                Text(
                    "Monto",
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
            },
            isError = viewModel.montoError.isNotBlank(),
            trailingIcon = {
                if (viewModel.montoError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )
        if (viewModel.montoError.isNotBlank()) {
            Text(
                text = viewModel.montoError,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
        ExtendedFloatingActionButton(
            modifier = Modifier
                .size(100.dp, 80.dp)
                .align(Alignment.End)
                .wrapContentSize(Alignment.Center),
            shape = CircleShape,
            text = {
                Text(
                    ""
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Save, contentDescription
                    = "Guardar"
                )
            },
            onClick = { viewModel.insertar() },
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
private fun PrestamoListScreen(prestamos: List<PrestamoEntity>) {
    LazyColumn {
        item {
            Text(
                text = "Lista de Prestamos",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
        }
        items(prestamos) {

                pre ->
            PrestamoRow(pre)

        }
    }
}

@Composable
private fun PrestamoRow(prestamo: PrestamoEntity) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column() {
                Text(
                    text = prestamo.deudor,
                    style = MaterialTheme.typography.titleLarge,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                    // modifier = Modifier.weight(3f)
                )
                Text(
                    text = prestamo.concepto,
                    style = MaterialTheme.typography.titleSmall,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                    // modifier = Modifier.weight(3f)
                )
            }

            Text(
                String.format("%.2f", prestamo.monto),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.titleLarge,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(2f)
            )
        }
        Divider(Modifier.fillMaxWidth())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun MyPreview() {
    var deudor by remember {
        mutableStateOf("")
    }
    val deudorError by remember {
        mutableStateOf("")
    }
    var concepto by remember {
        mutableStateOf("")
    }
    val conceptoError by remember {
        mutableStateOf("")
    }
    var monto by remember {
        mutableStateOf("")
    }
    val montoError by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = deudor,
            onValueChange = { deudor = it },
            label = { Text("Deudor") },
            isError = deudorError.isNotBlank(),
            trailingIcon = {
                if (deudorError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (deudorError.isNotBlank()) {
            Text(
                text = deudorError,
                color = MaterialTheme.colorScheme.error
            )
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = concepto,
            onValueChange = { concepto = it },
            label = { Text("Concepto") },
            isError = conceptoError.isNotBlank(),
            trailingIcon = {
                if (conceptoError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (conceptoError.isNotBlank()) {
            Text(
                text = conceptoError,
                color = MaterialTheme.colorScheme.error
            )
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = monto,
            onValueChange = { monto = it },
            label = { Text("Monto") },
            isError = deudorError.isNotBlank(),
            trailingIcon = {
                if (deudorError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )
        if (deudorError.isNotBlank()) {
            Text(
                text = montoError,
                color = MaterialTheme.colorScheme.error
            )
        }

    }
}


