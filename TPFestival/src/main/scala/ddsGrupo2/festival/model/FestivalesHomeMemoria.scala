package ddsGrupo2.festival.model

import scala.collection.mutable.Set
import collection.JavaConversions._
import scala.collection.mutable.ArrayBuffer
import scala.collection.immutable.Map

object FestivalesHomeMemoria {
  var festivales: ArrayBuffer[Festival] = ArrayBuffer()

  Categoria.crearCategoria('categoria1, 0)
  Categoria.crearCategoria('categoria2, 50)
  Categoria.crearCategoria('categoria3, 100)
  Categoria.crearCategoria('categoria4, 200)

  val ledZeppelin = new Banda(Categoria('categoria4), "Led Zeppelin")
  val ironMaiden = new Banda(Categoria('categoria4), "Iron Maiden")
  val sodaStereo = new Banda(Categoria('categoria3), "Soda Stereo")
  val rollingStones = new Banda(Categoria('categoria4), "Rollings Stones")
  val bonJovi = new Banda(Categoria('categoria4), "Bon Jovi")
  val elIndio = new Banda(Categoria('categoria2), "Indio Solari")

  val noche1 = new Noche(Set(ledZeppelin, sodaStereo), new Fecha(18, 10, 2014))
  val noche2 = new Noche(Set(bonJovi), new Fecha(19, 10, 2014))
  val noche3 = new Noche(Set(ledZeppelin, ironMaiden), new Fecha(20, 10, 2014))
  val noche4 = new Noche(Set(ledZeppelin, sodaStereo), new Fecha(21, 10, 2014))

  val valoresBaseQuilmes = Set(
    Sector("A", Fila(500, 10), Fila(400, 10), Fila(300, 10)),
    Sector("B", Fila(500, 10), Fila(500, 10), Fila(500, 10)),
    Sector("C", Fila(500, 100)))
    
  val valoresBaseRock = Set(
      Sector("A" , Fila(100, 15), Fila(100, 15), Fila(100, 15)),
    Sector("B" , Fila(500, 10), Fila(500, 10), Fila(500, 10)))

  var festivalRock: Festival = new Festival(valoresBaseRock, new Fecha().fechaActual(), "Festival Rock")
  festivalRock.agregarNoches(List(noche1, noche2, noche3, noche4))
  festivalRock.agregarDescuentos(Dama(), Jubilado(), Menor(), Mayor())

  var quilmesRock: Festival = new Festival(valoresBaseQuilmes,
    new Fecha().fechaActual(),
    "Quilmes Rock")
  val nocheq1 = new Noche(Set(ironMaiden), new Fecha(18, 10, 2014))
  val nocheq2 = new Noche(Set(elIndio), new Fecha(19, 10, 2014))

  quilmesRock.agregarNoches(List(nocheq1, nocheq2))
  quilmesRock.agregarDescuentos(Dama(), Menor(), Mayor())

  festivales += festivalRock
  festivales += quilmesRock

  def noches =
    festivales.flatten(unFest => unFest.noches)

  def fechas: List[Fecha] = {
    var fechas = this.noches.map(noche => noche.fecha)
    this.quitarRepetidos(fechas).sortWith(_ < _)
  }

  def quitarRepetidos(fechas: ArrayBuffer[Fecha]) = fechas.groupBy { _.toInt }.map { _._2.head }.toList

  def entradasDeCliente(nombre: String): List[Entrada] = entradas.filter(e => (nombre == null) || (e.cliente.toLowerCase() == nombre.toLowerCase())).toList

  def entradasPuesto(puesto: Int) = entradas.filter(e => e.puestoDeVenta == puesto)
  def entradas =
    festivales.flatten(unFestival => unFestival.entradasVendidas)

  var festivalActual = festivales.head
  def getFestivales: java.util.List[Festival] = festivales

  def getFestival(nombre: String) = festivales.find(unFestival => unFestival.nombre == nombre).get

  def clientes: java.util.List[String] = List("Pablo", "Florencia", "Nicolas", "Kevin")
  def puestosDeVenta: java.util.List[Int] = List(1, 2, 3, 4, 5)
}
