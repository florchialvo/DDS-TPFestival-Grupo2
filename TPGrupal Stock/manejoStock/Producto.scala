package manejoStock

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
}