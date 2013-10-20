package ddsGrupo2.festival.model

import ddsGrupo2.festival.model.exception._
import scala.collection.mutable.Set
import org.uqbar.commons.utils.Observable
import scala.collection.immutable.Map
import java.io.Serializable

class Festival(var valoresBase: Map[Char, Array[(Int, Int)]], var fechaVtoEntradasAnticipadas: Fecha) extends Serializable {

  var entradasVendidas: Set[Entrada] = Set()
  var noches: Set[Noche] = Set()
  var descuentosValidos: Set[TipoPersona] = Set()

  var nombre:String = ""
  override def toString() = nombre
    
    
  def sectores: scala.collection.immutable.Set[Char] = valoresBase.keySet
  def fechas = {
    val colMapeada = noches.map(n => n.fecha)
    colMapeada.toList.sortWith(_ < _)
  }

  def agregarNoche(n: Noche) = noches += n
  def agregarNoches(ns: List[Noche]) = noches++= ns
  def agregarDescuento(t: TipoPersona) = descuentosValidos += t
  def agregarDescuentos(ts: List[TipoPersona]) = descuentosValidos++= ts
  

  //fila-1 porque el indice empieza en 0
  def valorBase(fila: Int, sector: Char): Int = valoresBase(sector)(fila - 1)._1

  def cantButacas(sector: Char, fila: Int): Int = valoresBase(sector)(fila - 1)._2

  def cantFilas(sector: Char): Int = valoresBase(sector).length

  def estaVendida(fila: Int, sector: Char, numButaca: Int, fecha: Fecha) =
    entradasVendidas.exists(entrada => entrada.estasVendida(fila, sector, numButaca, fecha))

  def esAnticipada = !(new Fecha().fechaActual > fechaVtoEntradasAnticipadas)

  def vender(entrada: Entrada) {
    validarEntrada(entrada)
    entradasVendidas += entrada
  }

  def vender(unCombo: Combo): Unit = {
    unCombo.venderEn(this)
  }

  def noche(unaFecha: Fecha): Noche = noches.find(_.correspondeA(unaFecha)).get

  def validarEntrada(entrada: Entrada) =
    if (this.estaVendida(entrada.fila, entrada.sector, entrada.numButaca, entrada.fecha))
      throw new EntradaYaVendidaException("La entrada seleccionada ya está vendida")

  def porcentajeVendidoDamas =
    if (entradasTotales == 0) 0
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

  def validarEntradaNoVendida(entrada: Entrada) =
    if (!this.estaVendida(entrada.fila, entrada.sector, entrada.numButaca, entrada.fecha))
      throw new EntradaNoVendidaException("La entrada no puede anularse, no ha sido vendida")

  def cancelar(entrada: Entrada) = {
    validarEntradaNoVendida(entrada)
    entradasVendidas --=
      entradasVendidas.filter(vendida => vendida.estasVendida(entrada.fila, entrada.sector,
        entrada.numButaca, entrada.fecha))
  }

  //    TODO: Esto lo hace el EntradaBuilder ahora pero no lo saco
  //    porque lo usan todos los tests!   
  def nuevaEntrada(fila: Int, sector: Char, numButaca: Int, fecha: Fecha, persona: TipoPersona) = {
    if (this.esAnticipada)
      new EntradaAnticipada(this, valorBase(fila, sector), noche(fecha), persona, sector, fila, numButaca)
    else
      new Entrada(this, valorBase(fila, sector), noche(fecha), persona, sector, fila, numButaca)
  }

  def bandas = noches.flatten(noche => noche.bandas)

  def entradasDeCliente(nombre: String): List[Entrada] = entradasVendidas.filter(entrada => entrada.nombre == nombre).toList
  def entradasDePuestoDeVenta(puesto: Int): List[Entrada] = entradasVendidas.filter(entrada => entrada.puestoDeVenta == puesto).toList
}
