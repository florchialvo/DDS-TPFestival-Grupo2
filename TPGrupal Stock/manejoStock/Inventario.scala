package stock.model

import scala.collection.mutable.Map

class Inventario() {
	val productosReservados: collection.mutable.Map[Producto, Int] = Map() 
	val productosFabricados: collection.mutable.Map[Producto, Int] = Map()
	
	def agregar(producto:Producto, diccionario: Map[Producto,Int]){
	 if (diccionario.isDefinedAt(producto: Producto)){
	   diccionario(producto)= diccionario(producto)+1
	 }else{
	   diccionario += (producto -> 1)
	 }
	}
	
	def agregarReservado(producto:Producto) = this.agregar(producto, productosReservados)
	
	def agregarFabricado(producto:Producto) = this.agregar(producto, productosFabricados) 

	
	def rollBack(){
	  this.actualizarStock(productosReservados);
	  productosFabricados.clear()
	}
	
	def actualizarStock(diccionario: Map[Producto,Int]){
	  diccionario.foreach{case (producto:Producto, cantidad:Int) => producto.incrementarStock(cantidad)}
	  diccionario.clear()
	}
	
}

