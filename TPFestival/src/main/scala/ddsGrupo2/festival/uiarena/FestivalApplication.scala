package ddsGrupo2.festival.uiarena

import org.uqbar.arena.Application
import ddsGrupo2.festival.model._
import scala.collection.mutable.Set

object FestivalApplication extends Application with App {

  var festival: Festival = null
  var entradaBuilder: EntradaBuilder = null

  override def createMainWindow() = {
    this.setUp
    new NuevaEntradaWindow(this, entradaBuilder)
  }

  def setUp = {
    Categoria.crearCategoria('categoria1, 0)
    Categoria.crearCategoria('categoria2, 50)
    Categoria.crearCategoria('categoria3, 100)
    Categoria.crearCategoria('categoria4, 200)

    val ledZeppelin = new Banda(Categoria('categoria4))
    val ironMaiden = new Banda(Categoria('categoria4))
    val sodaStereo = new Banda(Categoria('categoria3))
    val noche1 = new Noche(Set(ledZeppelin, sodaStereo), new Fecha(27, 9, 2013))

    val valoresBase = Map('A' -> Array((100, 15), (100, 15), (100, 15)),
    					  'B' -> Array((500, 10), (500, 10), (500, 10)))
      
    festival = new Festival(valoresBase, new Fecha(3, 8, 2013))
    entradaBuilder = new EntradaBuilder(festival)
    festival.agregarNoche(noche1)
    festival.agregarDescuento(Dama)
    festival.agregarDescuento(Jubilado)
    festival.agregarDescuento(Menor)
    festival.agregarDescuento(Mayor)
  }
  start()
}