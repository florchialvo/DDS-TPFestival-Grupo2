package ddsGrupo2.festival.model

import scala.collection.mutable.Set
import scala.collection.mutable.ArrayBuffer

object FestivalesHome {
  val festivales: ArrayBuffer[Festival] = ArrayBuffer()

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

  val valoresBase = Map('A' -> Array((100, 15), (100, 15), (100, 15)),
    'B' -> Array((500, 10), (500, 10), (500, 10)))

  var festivalRock: Festival = new Festival(valoresBase, new Fecha().fechaActual())
  festivalRock.nombre = "Festival Rock"
  festivalRock.agregarNoches(List(noche1, noche2, noche3, noche4))
  festivalRock.agregarDescuentos(List(Dama, Jubilado, Menor, Mayor))

  var quilmesRock: Festival = new Festival(Map(
    'A' -> Array((500, 10), (400, 10), (300, 10)),
    'B' -> Array((500, 10), (500, 10), (500, 10)),
    'C' -> Array((500, 100))),
    new Fecha().fechaActual())
  val nocheq1 = new Noche(Set(ironMaiden), new Fecha(18, 10, 2014))
  val nocheq2 = new Noche(Set(elIndio), new Fecha(19, 10, 2014))

  quilmesRock.nombre = "Quilmes Rock"
  quilmesRock.agregarNoches(List(nocheq1, nocheq2))
  quilmesRock.agregarDescuentos(List(Dama, Menor, Mayor))

  festivales += (festivalRock)
  festivales += (quilmesRock)

  def entradasDeCliente(nombre: String) = entradas.filter(e => e.cliente == nombre)

  def entradas =
    festivales.flatten(unFestival => unFestival.entradasVendidas)

  def getFestival(nombre: String) = this.festivales.find(unFestival => unFestival.nombre == nombre)

  //TODO: borrar esto
  def getFestival = festivalRock
  
  def getBandas = this.festivalRock.bandas

  def getNoche = noche1
}