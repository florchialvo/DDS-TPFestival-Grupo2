package reservaDeStock

class Fabrica {
  def registrarPedido(componente: Producto, puntoDePedido: Int) = {}
  
  val inventario = new Inventario()

  def reservar(producto: Producto) = {
    try {
      producto.reservate(inventario)
    } catch {
      case e: StockException => inventario.rollback
    }
  }
}
