package manejoStock

class Compuesto extends Producto with ProductoCompuesto {

	def utilizar(cantidad: Int) = {
		if(this.hayStock)
			this.consumir(cantidad)
		else
			this.construir(cantidad)
	}
	
	  
}