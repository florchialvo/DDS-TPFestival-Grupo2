package manejoStock

class MateriaPrima extends Producto with ProductoCompuesto {

	def utilizar(cantidad: Int) = {
		if(this.hayStock)
			this.consumir(cantidad)
		else
			new Exception("No Hay Materia Prima Suficiente")
	}
}