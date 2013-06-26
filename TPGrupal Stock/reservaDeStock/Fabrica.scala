package reservaDeStock

import scala.collection.mutable.Map
import exceptions.StockException

class Fabrica {
  def registrarPedido(componente: Producto, puntoDePedido: Int) = {}

  def reservar(producto: Producto) = {
    try {
      val inventario = new Inventario()
      producto.reservate(inventario)
    } catch (StockException exception) {
      inventario.rollback()
    }
  }
}
