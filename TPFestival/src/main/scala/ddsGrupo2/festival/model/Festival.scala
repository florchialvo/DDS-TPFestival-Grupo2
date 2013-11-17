package ddsGrupo2.festival.model

import ddsGrupo2.festival.model.exception._
import scala.collection.mutable.Set
import org.uqbar.commons.utils.Observable
import java.io.Serializable
import org.uqbar.commons.model.Entity
import uqbar.arena.persistence.annotations.Relation
import collection.JavaConversions._
import uqbar.arena.persistence.annotations.PersistentField
import uqbar.arena.persistence.annotations.PersistentClass

@PersistentClass
class Festival(var valoresBase: Set[Sector], var fechaVtoEntradasAnticipadas: Fecha,
    var nombre:String)  extends Entity {
  
  //Persistencia
  def this() = this(Set(), null, null)
  
  @PersistentField
  def getNombre():String = nombre
  def setNombre(n:String) = (nombre = n)
  
//  @Relation
//  def getValoresBase(): java.util.Set[Sector] = valoresBase
//  def setValoresBase(s: java.util.Set[Sector]) = valoresBase = s
  
  //@Relation
  //def getEntradasVendidas():java.util.Set[Entrada]  = entradasVendidas
  
  //@Relation
  //def getNoches():java.util.Set[Noche] = noches
  //

  var entradasVendidas: Set[Entrada] = Set()
  var noches: Set[Noche] = Set()
  var descuentosValidos: Set[TipoPersona] = Set()

  override def toString() = nombre

  def sectores: scala.collection.Set[String] = valoresBase.map(s => s.nombre)
  def fechas = {
    val colMapeada = noches.map(n => n.fecha)
    colMapeada.toList.sortWith(_ < _)
  }

  def agregarNoche(n: Noche) = noches += n
  def agregarNoches(ns: List[Noche]) = noches ++= ns
  def agregarDescuento(t: TipoPersona) = descuentosValidos += t
  def agregarDescuentos(ts: List[TipoPersona]) = descuentosValidos ++= ts

  def sectorPorNombre(n:String) = valoresBase.filter(s => s.nombre == n).head
  
  //fila-1 porque el indice empieza en 0
  def valorBase(fila: Int, sector: String): Int = sectorPorNombre(sector).filas(fila - 1).precio

  def cantButacas(sector: String, fila: Int): Int = sectorPorNombre(sector).filas(fila - 1).cantidadButacas

  def cantFilas(sector: String): Int = sectorPorNombre(sector).cantFilas

  def estaVendida(fila: Int, sector: String, numButaca: Int, fecha: Fecha) =
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
  def nuevaEntrada(fila: Int, sector: String, numButaca: Int, fecha: Fecha, persona: TipoPersona) = {
    if (this.esAnticipada)
      new EntradaAnticipada(this, valorBase(fila, sector), noche(fecha), persona, sector, fila, numButaca)
    else
      new Entrada(this, valorBase(fila, sector), noche(fecha), persona, sector, fila, numButaca)
  }

  def bandas = noches.flatten(noche => noche.bandas)

  def entradasDeCliente(nombre: String): List[Entrada] = entradasVendidas.filter(entrada => entrada.nombre == nombre).toList
  def entradasDePuestoDeVenta(puesto: Int): List[Entrada] = entradasVendidas.filter(entrada => entrada.puestoDeVenta == puesto).toList
}
