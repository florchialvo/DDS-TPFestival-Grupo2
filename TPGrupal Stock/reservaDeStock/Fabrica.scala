package reservaDeStock
import scala.collection.mutable.Map

class Fabrica {
  val inventario = new Inventario()

  val pedidos: List[Producto] = List()

  def registrarPedido(producto: Producto) {
    pedidos :+ producto
  }

  def reservar(producto: Producto) = {
    val inventarioTemp = new Inventario()
    try {
      producto.reservate(inventarioTemp)
    } catch {
      case e: StockException =>
        {
          inventarioTemp.rollback
          throw e
        }
    } finally {
      actualizarInventario(inventarioTemp)
    }
  }

  def actualizarInventario(unInventario: Inventario) = {
    actualizar(inventario.reservados, unInventario.reservados)
    actualizar(inventario.fabricados, unInventario.fabricados)

  }

  def actualizar(diccFinal: Map[Producto, Int], diccTemporal: Map[Producto, Int]) = {
    for ((prod, cant) <- diccTemporal) {
      if (diccFinal.contains(prod))
        diccFinal(prod) += cant
      else
        diccFinal += (prod -> cant)
    }
  }
}



  

