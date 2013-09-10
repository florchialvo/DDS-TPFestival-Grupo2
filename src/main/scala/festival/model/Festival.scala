package festival.model

import festival.model.exception.EntradaYaVendidaException
import scala.collection.mutable.Set
import org.uqbar.commons.utils.Observable

@Observable
class Festival(var valoresBase: Map[Char, Array[Int]], var fechaVtoEntradasAnticipadas: Fecha) {

    var entradasVendidas: Set[Entrada] = Set()
    var noches: Set[Noche] = Set()
    var descuentosValidos: Set[TipoPersona] = Set()

    def agregarNoche = noches += (_: Noche)
    def agregarDescuento = descuentosValidos += (_: TipoPersona)

    def valorBase(fila: Int, sector: Char): Int = valoresBase.apply(sector).apply(fila)

    def estaVendida(fila: Int, sector: Char, fecha: Fecha) =
        entradasVendidas.exists(entrada => entrada.estasVendida(fila, sector, fecha))

    def esAnticipada(fechaVto: Fecha) = !(new Fecha().fechaActual > fechaVto)

    def nuevaEntrada(fila: Int, sector: Char, fecha: Fecha, persona: TipoPersona) = {
        if (this.esAnticipada(fechaVtoEntradasAnticipadas))
            new EntradaAnticipada(this, valorBase(fila, sector), noche(fecha), persona, sector, fila)
        else
            new Entrada(this, valorBase(fila, sector), noche(fecha), persona, sector, fila)
    }

    def vender(entrada: Entrada) {
        validarEntrada(entrada)
        entradasVendidas += entrada
    }

    def vender(unCombo: Combo): Unit = {
        unCombo.venderEn(this)
    }
    
    def noche(unaFecha: Fecha): Noche = noches.find(_.correspondeA(unaFecha)).get

    def validarEntrada(entrada: Entrada) =
        if (this.estaVendida(entrada.fila_, entrada.sector_, entrada.fecha_))
            throw new EntradaYaVendidaException("La entrada ya está vendida")

    def porcentajeVendidoDamas =
        entradasVendidas.count(_.persona == Dama) / this.entradasTotales * 100

    def entradasTotales = entradasVendidas.size

    def descuento(persona: TipoPersona, valorBase: Int): Double = {
        if (this.esPosibleDescuento(persona))
            persona.descuento(valorBase)
        else
            Mayor.descuento(valorBase)
    }
    
    def esPosibleDescuento(persona: TipoPersona): Boolean = {
        descuentosValidos.contains(persona) &&
            persona.esPosibleEn(this)
    }

    def cancelar(entrada: Entrada) = {
        entradasVendidas -= entrada
    }
}
