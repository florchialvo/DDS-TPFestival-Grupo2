package ddsGrupo2.festival.model

import java.io.Serializable

object Categoria {
    var instances: Map[Symbol, Categoria] = Map()

    def apply(s: Symbol) = instances.apply(s)

    def modificar(s: Symbol, valorNuevo: Int) = instances(s).valor = valorNuevo

    def crearCategoria(key: Symbol, valor: Int) = instances += key -> new Categoria(key, valor)
}

class Categoria(var key: Symbol, var valor: Int) extends Serializable {
    def getValor = valor
}