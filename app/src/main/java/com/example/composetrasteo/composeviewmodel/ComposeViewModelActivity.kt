package com.example.composetrasteo.composeviewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetrasteo.R
import com.example.composetrasteo.ui.theme.ComposeTrasteoTheme

class ComposeViewModel : ComponentActivity() {

    private val miembrosViewModel by viewModels<MiembrosViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTrasteoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ActivityBridge(miembrosViewModel)
                }
            }
        }
    }
}

data class Miembro(val nombre: String, val tipo: String)

@Composable
fun MemberRow(miembro: Miembro, onItemClicked: (Miembro) -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onItemClicked(miembro) }
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "ImagenMiembro",
            modifier = Modifier
                .padding(5.dp)
                .size(50.dp)
                .clip(CircleShape)

        )

        Spacer(modifier = Modifier.width(15.dp))

        Column {
            Text(text = miembro.nombre, style = MaterialTheme.typography.h6)
            Spacer(Modifier.height(3.dp))
            Text(text = miembro.tipo, style = MaterialTheme.typography.subtitle1)
        }
    }
}

@Composable
fun ListaMiembros(
    listaMiembros: List<Miembro>,
    onAgregarMiembro: (Miembro) -> Unit,
    onQuitarMiembrosSeleccionados: () -> Unit,
    onToggleSeleccionado: (Miembro) -> Unit,
) {

    Column {
        LazyColumn {
            items(listaMiembros) { miembro ->
                MemberRow(
                    miembro = miembro,
                    onItemClicked = { onToggleSeleccionado(it) }
                )
            }
        }

        Row {
            Button(
                onClick = { onAgregarMiembro(DataGenerator.obtenerMiembroAleatorio()) },
                modifier = Modifier
                    .weight(1F)
                    .padding(4.dp)
            ) {
                Text(text = "Añadir Miembro")
            }

            Button(
                onClick = { onQuitarMiembrosSeleccionados() },
                modifier = Modifier
                    .weight(1F)
                    .padding(4.dp)
            ) {
                Text(text = "Eliminar miembro")
            }

        }
    }
}

@Composable
fun ActivityBridge(miembrosViewModel: MiembrosViewModel) {
    val listaMiembros: List<Miembro> by miembrosViewModel.listaMiembros.observeAsState(listOf())

    ListaMiembros(
        listaMiembros = listaMiembros,
        onAgregarMiembro = { miembrosViewModel.addItem(it) },
        onQuitarMiembrosSeleccionados = { miembrosViewModel.quitarSeleccionados()},
        onToggleSeleccionado = { miembrosViewModel.toggleSeleccionado(it) }
    )

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ComposeTrasteoTheme {
        val listaTiembros = listOf(
            Miembro(nombre = "Serux", tipo = "Administrador"),
            Miembro(nombre = "Nnang", tipo = "Colaborador"),
            Miembro(nombre = "Cnnangsu", tipo = "Colaborador"),
            Miembro(nombre = "Juan", tipo = "Registrado"),
            Miembro(nombre = "Pepe", tipo = "Registrado"),
            Miembro(nombre = "Lucas", tipo = "Registrado"),
            Miembro(nombre = "Roberto", tipo = "Registrado"),
            Miembro(nombre = "Julio", tipo = "Registrado")
        )

        ListaMiembros(listaMiembros = listaTiembros, {}, {}, {})

    }
}