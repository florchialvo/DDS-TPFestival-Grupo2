package reservaDeStock

class PedidoDeCompra(fabrica: Fabrica) extends Interesado {

  override def salida(componente: Producto, cantidad: Int) =
    fabrica.registrarPedido(componente)

  override def cumpleRequisitoSalida(componente: Producto, cantidad: Int): Boolean =
    !componente.tenesStockMinimo
}
