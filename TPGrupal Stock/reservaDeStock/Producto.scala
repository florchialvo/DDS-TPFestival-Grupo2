package reservaDeStock

abstract class Producto {

	var stockMax: Int
	var stockMin: Int
	var stock: Int
	var nombre: String
	var puntoDePedido: Int
	
	def stockMax_(max: Int) = stockMax = max
	def stockMin_(min: Int) = stockMin = min
	def stock_(cantidad: Int) = stock = cantidad
	def nombre_(nomb: String) = nombre= nomb
	def puntoDePedido_(cantidad: Int) = puntoDePedido = cantidad
	
	
	def hayStock() = stock > 0
	
	def consumir(cantidad: Int) = stock -= cantidad
	
	def hayStockMinimo() = stock > stockMin
	
	def excedente(cantidad: Int): Int = 
	  	if(this.excede(cantidad))
	  		return stock + cantidad - stockMax
	  	else return 0
  
	def excede(cantidad: Int): Boolean = {
	  	stock + cantidad > stockMax
	}
	
	def reservate(inventario:Inventario)={
	  
	   if(this.hayStock()){
	    this.descontarStock(1)
	    inventario.agregarReservado(this)
	  }else{
		  this.fabricate(inventario)
	  }

	  }
	
	def descontarStock(cant:Int)={

	  this.stock-=cant

	}
	def incrementarStock(cant:Int)={
	  
	  this.stock+=cant
	 
	}
	
	def fabricate(inventario:Inventario)
	
	
}