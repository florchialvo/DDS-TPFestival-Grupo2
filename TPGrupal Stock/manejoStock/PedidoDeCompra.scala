package manejoStock

object PedidoDeCompra extends Interesado {
	
	override def salida(componente: Producto, cantidad: Int) = {
		fabrica.registrarPedido(componente, componente.puntoDePedido)
	}
	
	override def cumpleRequisitosSalida(componente: Producto, cantidad: Int) :Boolean = {
		! componente.hayStockMinimo()
	}
}