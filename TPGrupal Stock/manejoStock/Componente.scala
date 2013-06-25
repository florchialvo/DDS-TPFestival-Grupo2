package manejoStock

import scala.collection.immutable.List

trait Componente {

	var interesados: Array[Interesado] = Array()
	
	def excedente(cantidad: Int) = {}//Creo que tendria que estar en Producto
	
	def utilizar(cantidad: Int) = {}//Idem excedente
	
	def saleComponente(cantidad: Int) = {
		for(interesado <- this.interesados)
			interesado.sale(this, cantidad)
	}
	
	def entraComponente(cantidad: Int) = {
	  	for(interesado <- this.interesados)
	  		interesado.entra(this,cantidad)
	}
	
}