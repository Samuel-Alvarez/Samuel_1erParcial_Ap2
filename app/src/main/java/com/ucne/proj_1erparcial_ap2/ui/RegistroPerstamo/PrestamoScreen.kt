package com.ucne.proj_1erparcial_ap2.ui.RegistroPerstamo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.twotone.Check
import androidx.compose.material.icons.twotone.Description
import androidx.compose.material.icons.twotone.Error
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamoEntity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrestamoScreen(viewModel: PrestamoViewModel = hiltViewModel()) {

    Column(
        Modifier.fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        Spacer(modifier = Modifier.padding(60.dp))
        Text(
            text = "Prestamos", fontSize = 40.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )

        Spacer(modifier = Modifier.padding(15.dp))
        PrestamoBody(viewModel)

        Spacer(modifier = Modifier.padding(18.dp))
        Text(
            text = "Lista de Prestamos", fontSize = 40.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        val uiState by viewModel.uiState.collectAsState()
        PrestamoListScreen(uiState.prestamoList)
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PrestamoBody(
    viewModel: PrestamoViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {

        OutlinedTextField(
            modifier = Modifier
                .size(330.dp, 60.dp)
                .fillMaxWidth(),
            value = viewModel.Deudor,
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
            label = { Text("Deudor") },
            isError = viewModel.deudorError.isNotBlank(),
            trailingIcon = {
                if (viewModel.deudorError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }else if(viewModel.hayError)
                {
                    Icon(imageVector = Icons.TwoTone.Check, contentDescription = "success")
                }
            }
        )
        if (viewModel.deudorError.isNotBlank()) {
            Text(
                text = viewModel.deudorError,
                color = MaterialTheme.colorScheme.error
            )
        }else if(viewModel.hayError){
            Text(
                text = viewModel.deudorError,
                color = Color.Green
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .size(330.dp, 60.dp)
                .fillMaxWidth(),
            value = viewModel.Concepto,
            onValueChange = viewModel::onConceptoChanged,
            leadingIcon = {
                Icon(
                    imageVector = Icons.TwoTone.Description,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                )
            },
            label = { Text("Concepto") },
            isError = viewModel.conceptoError.isNotBlank(),
            trailingIcon = {
                if (viewModel.conceptoError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }else if(viewModel.hayError){
                    Icon(imageVector = Icons.TwoTone.Check, contentDescription = "success")
                }
            }
        )

        if (viewModel.conceptoError.isNotBlank()) {
            Text(
                text = viewModel.conceptoError,
                color = MaterialTheme.colorScheme.error
            )
        }else if(viewModel.hayError){
            Text(
                text = viewModel.conceptoError,
                color = Color.Green
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .size(330.dp, 60.dp)
                .fillMaxWidth(),
            value = viewModel.Monto,
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
            label = { Text("Monto") },
            isError = viewModel.montoError.isNotBlank(),
            trailingIcon = {
                if (viewModel.montoError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }else if(viewModel.hayError){
                    Icon(imageVector = Icons.TwoTone.Check, contentDescription = "success")
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
        }else if(viewModel.hayError){
            Text(
                text = viewModel.montoError,
                color = Color.Green
            )
        }
    }

    Spacer(modifier = Modifier.padding(14.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        ) {
            Box()
            {
                ExtendedFloatingActionButton(
                    modifier = Modifier
                        .size(60.dp, 50.dp),
                    containerColor = Color.Blue,
                    text = { Text("") },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Cached,
                            contentDescription = "Clear",
                            tint = Color.White
                        )
                    },
                    onClick = {
                        viewModel.Limpiar()
                    }
                )
            }

            Spacer(modifier = Modifier.padding(20.dp))
            Box()
            {
                ExtendedFloatingActionButton(
                    modifier = Modifier
                        .size(60.dp, 50.dp),
                    containerColor = Color.Green,
                    text = { Text("") },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Save,
                            contentDescription = "Save",
                            tint = Color.White
                        )
                    },
                    onClick = {
                        viewModel.insertar()
                    }
                )
            }


        }
    }
}

@Composable
private fun PrestamoListScreen(prestamoList: List<PrestamoEntity>) {
    LazyColumn {
        items(prestamoList) { prestamo ->
            PrestamoRow(prestamo)
        }
    }
}

@Composable
private fun PrestamoRow(prestamo: PrestamoEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        Divider(Modifier.fillMaxWidth())
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.CenterStart)
        ) {
            Text(
                text = prestamo.deudor, fontSize = 50.sp,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.wrapContentSize(Alignment.CenterStart)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = prestamo.concepto,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.wrapContentSize(Alignment.CenterStart)
            )
            Text(
                String.format("$ %.2f", prestamo.monto),
                fontSize = 50.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth()
            )
        }
        Divider(Modifier.fillMaxWidth())
    }
}


