package com.example.composetrasteo.composeviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MiembrosViewModel: ViewModel() {

    private val _listaMiembros = MutableLiveData(listOf<Miembro>()) // variable de estado
    val listaMiembros: LiveData<List<Miembro>> = _listaMiembros // funcion para observar

    private val miembroSeleccionados = mutableSetOf<Miembro>()

    fun addItem(miembro: Miembro) {
        _listaMiembros.value = _listaMiembros.value!!.plus(listOf(miembro))
    }

    fun removeItem(miembro: Miembro) {
        _listaMiembros.value = _listaMiembros.value!!.toMutableList().also {
            it.remove(miembro)
        }
    }

    /**
     * Comprueba si un miembro esta seleccionado y si lo está lo quita de la
     * lista de seleccionado, si no, lo añade.
     */
    fun toggleSeleccionado(miembro: Miembro) {
        val isMiembroSeleccionado = _listaMiembros.value!!.contains(miembro)
        if (isMiembroSeleccionado)
            addMiembroSeleccionado(miembro)
        else
            removeMiembroSeleccionado(miembro)
    }

    fun quitarSeleccionados() {
        // Quitamos de la lista de miembros los seleccionados
        _listaMiembros.value = _listaMiembros.value!!.minus(miembroSeleccionados)

        // Vaciamos la lista de seleccionados
        miembroSeleccionados.clear()
    }

    private fun addMiembroSeleccionado(miembro: Miembro) {
        miembroSeleccionados.add(miembro)
        println(miembroSeleccionados.size)
    }

    private fun removeMiembroSeleccionado(miembro: Miembro) {
        miembroSeleccionados.remove(miembro)
    }
}