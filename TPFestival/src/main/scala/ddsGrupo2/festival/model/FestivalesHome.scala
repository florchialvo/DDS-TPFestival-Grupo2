package ddsGrupo2.festival.model

import scala.collection.mutable.Set
import collection.JavaConversions._
import scala.collection.mutable.ArrayBuffer
import uqbar.arena.persistence.PersistentHome
import uqbar.arena.persistence.Configuration
import scala.collection.mutable.Buffer

object FestivalesHome extends PersistentHome[Festival] with Serializable {
 
  //Persistencia
  override def getEntityType(): Class[Festival] = classOf[Festival]

  override def createExample(): Festival = new Festival()
  //

  def festivales: Buffer[Festival] = allInstances()
  
  def agregarSiNoExiste(f:Festival) {
    if (getFestival(f.nombre) == null)
      create(f)
  }
  
  def noches =
    festivales.flatten(unFest => unFest.noches)

  def fechas: List[Fecha] = {
    var fechas = this.noches.map(noche => noche.fecha)
    this.quitarRepetidos(fechas).sortWith(_ < _)
  }

  def quitarRepetidos(fechas: scala.collection.mutable.Buffer[Fecha]) = fechas.groupBy { _.toInt }.map { _._2.head }.toList

  def entradasDeCliente(nombre: String): List[Entrada] = entradas.filter(e => (nombre == null) || (e.cliente.toLowerCase() == nombre.toLowerCase())).toList

  def entradasPuesto(puesto: Int) = entradas.filter(e => e.puestoDeVenta == puesto)
  def entradas =
    festivales.flatten(unFestival => unFestival.entradasVendidas)
  
  def getFestivales: java.util.List[Festival] = festivales

  def getFestival(nombre: String) : Festival = {
    val resultado =  festivales.filter(unFestival => unFestival.nombre == nombre)
    if (resultado.isEmpty)
      return null
    else
      return festivales.head
  }

  def clientes: java.util.List[String] = List("Pablo", "Florencia", "Nicolas", "Kevin")
  def puestosDeVenta: java.util.List[Int] = List(1, 2, 3, 4, 5)
}