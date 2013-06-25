package manejoStock

class Compuesto extends Producto with ProductoCompuesto {
  
  val componentes:List[Producto] = List()

	def utilizar(cantidad: Int) = {
		if(this.hayStock)
			this.consumir(cantidad)
		else
			this.construir(cantidad)
	}
	
	override def fabricate(inventario:Inventario) = {
	   componentes.foreach (componente =>
	   componente.reservate(inventario))
    inventario.agregarFabricado(this)

	  
	}  
	
	
	  
}