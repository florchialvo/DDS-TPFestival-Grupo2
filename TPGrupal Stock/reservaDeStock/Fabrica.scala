package reservaDeStock
import scala.collection.mutable.ListBuffer
class Fabrica {
  def registrarPedido(componente: Producto, puntoDePedido: Int) = {}
  
  val inventario = new Inventario()


  val pedidos: List[Producto] = List()

  def registrarPedido(producto: Producto){
	pedidos :+ producto
  }


  def reservar(producto: Producto) = {
    try {
      producto.reservate(inventario)
    } catch {
      case e: StockException => inventario.rollback
    }
  }
}



  

