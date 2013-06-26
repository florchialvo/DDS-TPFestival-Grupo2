package stock.model

import scala.collection.mutable.Set
import stock.model.exception.StockException




class Fabrica(){
  
  val historial: Set[Inventario] = Set()
  
  def reservar(producto:Producto){
    val inventario = new Inventario()
    try {
        producto.reservate(inventario)     
    }catch  {
      case e:StockException => inventario.rollBack()
    }
  } 
}