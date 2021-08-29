package com.example.composetrasteo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.composetrasteo.ui.theme.ComposeTrasteoTheme
import kotlinx.coroutines.launch

/**
 * 6 Working with Lists
 * https://developer.android.com/codelabs/jetpack-compose-layouts?hl=es-419&continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fcompose%3Fhl%3Des-419%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fjetpack-compose-layouts#5
 */
class ListasElementos : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTrasteoTheme {
                DefaultPreview4()
            }
        }
    }
}


@Composable
fun ListaEjemplo() {
    /** Podemos usar la funcion rememberLazyListState para guardar el estado de la posicion del scroll */
    val scrollState = rememberLazyListState()

    /** Para ser eficientes se usa el componente LazyColumn para mostrar elementos de una lista */
    val listaNombres = listOf("Gopal", "Asad", "Shubham", "Aditya", "Devarsh", "Nikhil", "Gagan")

    LazyColumn(state = scrollState) {
        // Para recorrer cada elemento de la lista se usa el objeto items
        items(listaNombres) { nombre ->
            Text(text = nombre)
        }

        // Tambien se pueden apadir elementos individuales con el objeto item
        item {
            Text(text = "Otro elemento de la lista")
        }

        // Otra foram de añadir multiples elementos
        items(30) {
            Text(text = "Mas elementos $it")
        }
    }
}

/** Ejemplo de componente para mostrar una lista imagenes */
// Componente que crea la lista
@Composable
fun ListaImagenes() {

    /** Botones para ir al principio o final de la lista */
    val numElementosLista = 50
    // Variable para guardar el estado del scroll
    val scrollState = rememberLazyListState()
    // Corrutina en la que se ejecutara el scroll animado
    val corutineScope = rememberCoroutineScope()

    // Botones
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
    ) {
        Button(
            onClick = {
                corutineScope.launch {
                    // Funcion para desplazarse a un elemento del scroll
                    scrollState.animateScrollToItem(0)
                }
            },
            modifier = Modifier
                .weight(1F, true)
                .padding(2.dp)
        ) {
            Text(text = "Principio de la lista")
        }

        Button(
            onClick = {
                corutineScope.launch {
                    // Funcion para desplazarse a un elemento del scroll
                    scrollState.animateScrollToItem(numElementosLista - 1)
                }
            },
            modifier = Modifier
                .weight(1F, true)
                .padding(2.dp)
        ) {
            Text(text = "Final de la lista")
        }
    }

    LazyColumn(state = scrollState) {
        items(numElementosLista) {
            ItemListaImagenes(it)
        }
    }
}


// Componente del item de lista
@Composable
fun ItemListaImagenes(indice: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = rememberImagePainter("https://developer.android.com/images/brand/Android_Robot.png"),
            contentDescription = "Logo android",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text(text = "Elemento $indice", style = MaterialTheme.typography.subtitle1)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    Column {
        //ListaEjemplo()
        ListaImagenes()
    }
}