package com.example.composetrasteo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetrasteo.ui.theme.ComposeTrasteoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTrasteoTheme {
                DefaultPreview()
            }
        }
    }
}

enum class EstadoUsuario(val color: Color) {
    CONECTADO(Color.Green),
    AUSENTE(Color.Yellow),
    DESCONECTADO(Color.Red)
}

data class Usuario(val nombre: String, val estado: EstadoUsuario)

val usuariosEjemplo = listOf(
    Usuario("Serux", EstadoUsuario.CONECTADO),
    Usuario("Nnang", EstadoUsuario.AUSENTE),
    Usuario("Test", EstadoUsuario.DESCONECTADO),
    Usuario("Name", EstadoUsuario.CONECTADO),
    Usuario("VeryLargeName", EstadoUsuario.CONECTADO),
)

@Composable
fun ProfileCard(usuario: Usuario) {

    Row(
        verticalAlignment = CenterVertically,
        modifier = Modifier
            .height(50.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "Perfil",
            modifier = Modifier
                .padding(5.dp)
                .clip(CircleShape)
                .size(40.dp)
                .border(2.dp, usuario.estado.color, CircleShape)
        )
        Text(
            text = usuario.nombre,
            color = Color.White,
            modifier = Modifier
                .padding(start = 2.dp, end = 7.dp)
        )

        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
    }


}

@Composable
fun ProfileList() {

    Surface(
        elevation = 30.dp,
        color = Color.DarkGray,
        modifier = Modifier
            .border(1.dp, Color.LightGray, RectangleShape)
    ) {
        LazyRow {
            items(usuariosEjemplo) { usuario -> ProfileCard(usuario) }
        }
    }
}

// ####### State hoisting
/**
 * State hoisting is the way to make internal state controllable by the function that called it.
 * You do so by exposing the state through a parameter of the controlled composable function and
 * instantiating it externally from the controlling composable
 */
@Composable
fun ClickCounter(clicks: Int, updateClicks: () -> Unit) {

    Button(
        onClick = updateClicks
    ) {
        Text("I've been clicked $clicks times")
    }

}

@Composable
fun ProfileViewer(nombreUsuario: String, profesion: String, miembroDesde: String, estadoUsuario: Color) {
    
    var seleccionado by remember { mutableStateOf(false)}
    val colorFondo by animateColorAsState(if (seleccionado) Color.LightGray else Color.Transparent)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 60.dp),
        elevation = 10.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(color = colorFondo)
                .clickable (onClick = {seleccionado = !seleccionado})
        ) {
            Box (Modifier.padding(top = 10.dp)){
                Image(
                    painter = painterResource(id = R.drawable.profile_picture),
                    contentDescription = "Imagen perfil",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(150.dp)
                        .border(2.dp, estadoUsuario, CircleShape)
                )
            }

            Text(text = nombreUsuario, fontWeight = FontWeight.Bold)
            Text(text = profesion)
            Text(text = miembroDesde, fontWeight = FontWeight.Light)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTrasteoTheme {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // Lista de perfiles horizontal
            ProfileList()

            Spacer(modifier = Modifier.height(5.dp))

            // Boton contador
            var clicks by remember { mutableStateOf(0) }
            ClickCounter(clicks) { clicks++ }

            Divider(modifier = Modifier.padding(vertical = 10.dp), Color.LightGray)

            // Visualizador perfil
            ProfileViewer(usuariosEjemplo[0].nombre, 
                "Diseñador", 
                "02/04/21",
                usuariosEjemplo[0].estado.color)
        }
    }
}


// ##### Reutilizacion

/* Reutilizacion de componentes
* Se puede crear un contenedor de funciones en las que se puede aplicar una configuracion común
* y luego pasar elementos hijos @Composable a los que aplicar esa configuracion
* */

/**
 * Una pantalla generica con fondo negro a la que se le pasa una componente @Composable
 */
@Composable
fun PantallaGenerica(contenido: @Composable () -> Unit) {
    Surface(color = Color.Black) {
        contenido()
    }
}

@Preview("Reutilizacion")
@Composable
fun PreviewReutilizacion() {
    PantallaGenerica { ProfileViewer(usuariosEjemplo[0].nombre,
        "Diseñador",
        "02/04/21",
        usuariosEjemplo[0].estado.color) }
}

// ##### Fin Reutilizacion