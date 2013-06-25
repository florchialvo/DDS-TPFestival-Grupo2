package manejoStock

class ProductoFinal extends Producto with ProductoCompuesto {

	def vender(cantidad: Int) = {
		if(this.hayStock())
			this.consumir(cantidad)
		else
			this.construir(cantidad)
	}
	
}