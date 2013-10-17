package ddsGrupo2.festival.model

import scala.collection.mutable.Set

object FestivalesHome {
    Categoria.crearCategoria('categoria1, 0)
    Categoria.crearCategoria('categoria2, 50)
    Categoria.crearCategoria('categoria3, 100)
    Categoria.crearCategoria('categoria4, 200)

    val ledZeppelin = new Banda(Categoria('categoria4), "Led Zeppelin")
    val ironMaiden = new Banda(Categoria('categoria4), "Iron Maiden")
    val sodaStereo = new Banda(Categoria('categoria3), "Soda Stereo")
    val noche1 = new Noche(Set(ledZeppelin, sodaStereo), new Fecha().fechaActual())

    val valoresBase = Map('A' -> Array((100,15), (100,15), (100,15)),
    					  'B' -> Array((500,10), (500,10), (500,10)))
    					  
    
    
    var festival: Festival = new Festival(valoresBase, new Fecha().fechaActual())
    festival.agregarNoche(noche1)
    festival.agregarDescuento(Dama)
    festival.agregarDescuento(Jubilado)
    festival.agregarDescuento(Menor)
    festival.agregarDescuento(Mayor)
    
    def getFestival = this.festival
    
    def getBandas = this.festival.bandas
        
    def getNoche = noche1
}