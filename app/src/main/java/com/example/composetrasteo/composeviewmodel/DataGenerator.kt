package com.example.composetrasteo.composeviewmodel

object DataGenerator {
    private val listaTiembros = listOf(
        Miembro(nombre = "Serux", tipo = "Administrador"),
        Miembro(nombre = "Nnang", tipo = "Colaborador"),
        Miembro(nombre = "Cnnangsu", tipo = "Colaborador"),
        Miembro(nombre = "Juan", tipo = "Registrado"),
        Miembro(nombre = "Pepe", tipo = "Registrado"),
        Miembro(nombre = "Lucas", tipo = "Registrado"),
        Miembro(nombre = "Roberto", tipo = "Registrado"),
        Miembro(nombre = "Julio", tipo = "Registrado")
    )

    fun obtenerMiembroAleatorio(): Miembro {
        return listaTiembros.random()
    }
}