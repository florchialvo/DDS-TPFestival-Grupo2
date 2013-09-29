package ddsGrupo2.festival.model

import ddsGrupo2.festival.model.exception._
import scala.collection.mutable.Set
import org.uqbar.commons.utils.Observable
import scala.collection.immutable.Map
import java.io.Serializable



class Festival(var valoresBase: Map[Char, Array[Int]], var fechaVtoEntradasAnticipadas: Fecha) extends Serializable {
	
    var entradasVendidas: Set[Entrada] = Set()
    var noches: Set[Noche] = Set()
    var descuentosValidos: Set[TipoPersona] = Set() 
    
    def sectores: scala.collection.immutable.Set[Char] = valoresBase.keySet
    def fechas = noches.map(n => n.fecha)
    
    def getSectores()= AsientosHome.getSectores()
    
    def agregarNoche(n: Noche) = noches += n
    def agregarDescuento(t : TipoPersona) = descuentosValidos += t

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
        if (this.estaVendida(entrada.fila, entrada.sector, entrada.fecha))
            throw new EntradaYaVendidaException("La entrada ya está vendida")

    def porcentajeVendidoDamas = 
          if(entradasTotales == 0) 0 
          else entradasVendidas.count(_.persona == Dama) / this.entradasTotales * 100
       

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
        if (!this.estaVendida(entrada.fila, entrada.sector, entrada.fecha))
            throw new EntradaNoVendidaException("La entrada no puede anularse")
        entradasVendidas -= entrada
    }
}
