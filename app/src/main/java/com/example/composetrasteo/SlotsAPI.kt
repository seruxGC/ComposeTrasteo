package com.example.composetrasteo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetrasteo.ui.theme.ComposeTrasteoTheme

/**
 * 4 Slots APIS
 * https://developer.android.com/codelabs/jetpack-compose-layouts?hl=es-419&continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fcompose%3Fhl%3Des-419%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fjetpack-compose-layouts#5
 */
class SlotsAPI : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTrasteoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DefaultPreview3()
                }
            }
        }
    }
}


/**
 * Definimos un componente boton generico en este caso tiene algunas de las propiedades
 * que queremos configurar en el boton como propiedades y el resto estan definidas en el propio
 * boton
 */
@Composable
fun BotonGenerico(
    onClick: (() -> Unit),
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    /**
     * Se le pasa al boton las propiedades configurables y se pueden añadir otras que sean fijas
     * como "shape" y "colors"
     */
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
    ) {
        /**
         * Contenido que tendra el boton , en este caso todos los contenidos
         * estaran en un "Row" centrado verticalmente
         */
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }
    }
}

/**
 * Conenido del boton
 */
@Composable
fun ContenidoBoton() {
    Icon(Icons.Default.Add, contentDescription = "Localized description")
    Spacer(Modifier.width(4.dp))
    Text("Texto boton")
}

fun accionBoton() {
    println("testlo")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    ComposeTrasteoTheme {
        BotonGenerico(
            onClick = { accionBoton() },
            content = { ContenidoBoton() }
        )
    }
}