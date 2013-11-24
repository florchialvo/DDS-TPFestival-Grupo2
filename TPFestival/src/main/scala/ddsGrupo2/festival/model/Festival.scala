package ddsGrupo2.festival.model

import ddsGrupo2.festival.model.exception._
import scala.collection.mutable.Set
import org.uqbar.commons.utils.Observable
import java.io.Serializable
import org.uqbar.commons.model.Entity
import collection.JavaConversions._
import uqbar.arena.persistence.annotations.PersistentField
import uqbar.arena.persistence.annotations.PersistentClass
import uqbar.arena.persistence.annotations.Relation

@PersistentClass
class Festival(var valoresBase: Set[Sector], var fechaVtoEntradasAnticipadas: Fecha,
  var nombre: String) extends Entity {

  var entradasVendidas: Set[Entrada] = Set()
  var noches: Set[Noche] = Set()
  var descuentosValidos: Set[TipoPersona] = Set()

  //Persistencia
  def this() = this(Set(), new Fecha, "")

  @PersistentField
  def getNombre(): String = nombre
  def setNombre(n: String) = (nombre = n)

  @Relation
  def getValoresBase(): java.util.Set[Sector] = valoresBase
  def setValoresBase(s: java.util.Set[Sector]) = (valoresBase = s)

  @Relation
  def getNoches(): java.util.Set[Noche] = noches
  def setNoches(ns: java.util.Set[Noche]) = noches = ns

  @Relation
  def getDescuentosValidos(): java.util.Set[TipoPersona] = descuentosValidos
  def setDescuentosValidos(ns: java.util.Set[TipoPersona]) = descuentosValidos = ns

  @Relation
  def getEntradasVendidas(): java.util.Set[Entrada] = entradasVendidas
  def setEntradasVendidas(es: java.util.Set[Entrada]) = entradasVendidas = es

  @Relation
  def getFechaAnticipada(): Fecha = fechaVtoEntradasAnticipadas
  def setFechaAnticipada(fecha: Fecha) = fechaVtoEntradasAnticipadas = fecha
  //

  override def toString() = nombre

  def sectores = valoresBase.map(_.nombre)
  def fechas = {
    val colMapeada = noches.map(_.fecha)
    colMapeada.toList.sortWith(_ < _)
  }

  def agregarNoche(n: Noche) = noches += n
  def agregarNoches(ns: List[Noche]) = noches ++= ns
  def agregarDescuento(t: TipoPersona) = descuentosValidos += t
  def agregarDescuentos(ts: TipoPersona*) = descuentosValidos ++= ts.toSet

  def sectorPorNombre(n: String) = valoresBase.filter(_.nombre == n).head

  //fila-1 porque el indice empieza en 0
  def valorBase(fila: Int, sector: String): Int = sectorPorNombre(sector).precio(fila - 1)

  def cantButacas(sector: String, fila: Int): Int = sectorPorNombre(sector).cantidadButacas(fila - 1)

  def cantFilas(sector: String): Int = sectorPorNombre(sector).cantFilas

  def estaVendida(fila: Int, sector: String, numButaca: Int, fecha: Fecha) =
    entradasVendidas.exists(_.estasVendida(fila, sector, numButaca, fecha))

  def esAnticipada = !(new Fecha().fechaActual > fechaVtoEntradasAnticipadas)

  def vender(entrada: Entrada) {
    validarEntrada(entrada)
    entradasVendidas += entrada
    FestivalesHome.update(this)
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
      Mayor().descuento(valorBase)
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
      entradasVendidas.filter(_.estasVendida(entrada.fila, entrada.sector,
        entrada.numButaca, entrada.fecha))
    FestivalesHome.update(this)
  }

  //    TODO: Esto lo hace el EntradaBuilder ahora pero no lo saco
  //    porque lo usan todos los tests!   
  def nuevaEntrada(fila: Int, sector: String, numButaca: Int, fecha: Fecha, persona: TipoPersona) = {
    if (this.esAnticipada)
      new EntradaAnticipada(this, valorBase(fila, sector), noche(fecha), persona, sector, fila, numButaca)
    else
      new Entrada(this, valorBase(fila, sector), noche(fecha), persona, sector, fila, numButaca)
  }

  def bandas = noches.flatten(_.bandas)

  def entradasDeCliente(nombre: String): List[Entrada] = entradasVendidas.filter(_.nombre == nombre).toList
  def entradasDePuestoDeVenta(puesto: Int): List[Entrada] = entradasVendidas.filter(_.puestoDeVenta == puesto).toList
}
