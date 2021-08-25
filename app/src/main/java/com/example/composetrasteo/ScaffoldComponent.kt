package com.example.composetrasteo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetrasteo.ui.theme.ComposeTrasteoTheme

/**
 * Scaffold allows you to implement a UI with the basic Material Design layout structure.
 * It provides slots for the most common top-level Material components such as TopAppBar,
 * BottomAppBar, FloatingActionButton and Drawer. With Scaffold, you make sure these components
 * will be positioned and work together correctly.
 *
 */
class ScaffoldComponent : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTrasteoTheme {
                LayoutsCodelab()
            }
        }
    }
}

/**
 * Let's add the Scaffold composable to our example so that we can have a typical Material
 * Design structure. All parameters in the Scaffold API are optional except the body content
 * that is of type @Composable (InnerPadding) -> Unit: the lambda receives a padding as a parameter.
 * That's the padding that should be applied to the content root composable to constrain the items
 * appropriately on the screen.
 */
@Composable
fun LayoutsCodelab() {
    Scaffold(
        /** Scaffold has a slot for a top AppBar with the topBar parameter
         * of type @Composable () -> Unit, meaning we can fill the slot with any composable we want.*/
        topBar = {
             /** Compose comes with a TopAppBar composable that has slots for a title, navigation icon, and actions */
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodelab")
                },
                /** The slot for action items in the top AppBar is the actions parameter that internally
                 * uses a Row, so multiple actions will be placed horizontally. In order to use one of
                 * the predefined icons, we can use the IconButton composable with an Icon */
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        /** Adicionalmente se pueden añadir al proyecto mas iconos añadiendo al build.gradle la siguiente dependencia:
                         * implementation "androidx.compose.material:material-icons-extended:$compose_version" */
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPading ->
        /** Para est ejemplo se quiere aplicar un padding superior al establecido por defecto
         * asi que a partir del parametro obligatorio "innerPading" le añadimos adicionalmente 8 dps */
        BodyContent(
            Modifier
                .padding(innerPading)
                .padding(8.dp))
    }
}

/**
 * To make our code more reusable and testable, we should structure it into small chunks.
 * For that, let's create another composable function with the content of our screen.
 */
@Composable
fun BodyContent(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
    }
}

@Preview
@Composable
fun LayoutsCodelabPreview() {
    ComposeTrasteoTheme {
        LayoutsCodelab()
    }
}