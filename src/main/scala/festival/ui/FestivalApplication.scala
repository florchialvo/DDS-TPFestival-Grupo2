package festival.ui

import org.uqbar.arena.Application
import festival.model._
import scala.collection.mutable.Set

object FestivalApplication extends Application with App {
	
    var festival:Festival = null
    
	override def createMainWindow() = {
	    this.setUp
	    new NuevaEntradaWindow(this, festival)
	}
	
	def setUp = {
		Categoria.crearCategoria('categoria1, 0)
	    Categoria.crearCategoria('categoria2, 50)
	    Categoria.crearCategoria('categoria3, 100)
	    Categoria.crearCategoria('categoria4, 200)
	
	    val ledZeppelin = new Banda(Categoria('categoria4))
	    val ironMaiden = new Banda(Categoria('categoria4))
	    val sodaStereo = new Banda(Categoria('categoria3))
	    val noche1 = new Noche(Set(ledZeppelin, sodaStereo), new Fecha(2, 10, 2013))
	
	    val valoresBase = Map('A' -> Array(100, 100, 100), 'B' -> Array(500, 500, 500))
	    festival = new Festival(valoresBase, new Fecha(1, 12, 2013))
	    festival.agregarNoche(noche1)
	    festival.agregarDescuento(Dama)
	    festival.agregarDescuento(Jubilado)
	    festival.agregarDescuento(Menor)
	    festival.agregarDescuento(Mayor)
	}
	
	start()
}