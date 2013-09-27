package ddsGrupo2.festival.model

class Banda(var categoria: Categoria) {

    def getValorCategoria = categoria.getValor

    def cambiarCategoria(nuevaCategoria: Categoria) = {
        this.categoria = nuevaCategoria
    }
}