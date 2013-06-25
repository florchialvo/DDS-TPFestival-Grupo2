package manejoStock

object Auditoria extends Interesado {
	
	override def salida(componente: Producto, cantidad: Int) = 
		this.logger.log("El producto "+
						componente.nombre+
						" bajo del stock minimo") 

	override def entrada(componente: Producto, cantidad: Int) = 
		this.logger.log("El producto "+
						componente.nombre+
						" dejo de estar por debajo del stock minimo") 

	override def cumpleRequisitoEntrada(componente: Producto, cantidad: Int) : Boolean = 
	  	componente.hayStockMinimo

	override def cumpleRequisitoSalida(componente: Producto, cantidad: Int) : Boolean = 
	  	!componente.hayStockMinimo

}