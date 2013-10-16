package ddsGrupo2.festival.model

import java.io.Serializable

class Banda(var categoria: Categoria, val nombre: String) extends Serializable{

    def getValorCategoria = categoria.getValor

    def cambiarCategoria(nuevaCategoria: Categoria) = {
        this.categoria = nuevaCategoria
    }
}