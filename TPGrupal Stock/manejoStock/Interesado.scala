package manejoStock

abstract class Interesado {

	var fabrica: Fabrica
	val cantidadLimite: Int
	var mailSender: MailSender
	var loggear: Boolean
	var logger: Logger
  
	def entrada(componente: Componente, cantidad: Int) = {}
	
	def salida(componente: Componente, cantidad: Int) = {}
  
	def cumpleRequisitoEntrada(componente: Componente, cantidad: Int): Boolean = return false
	
	def cumpleRequisitoSalida(componetne: Componente, cantidad: Int): Boolean = return false
	
	def entra(componente: Componente, cantidad: Int) = 
		if(this.cumpleRequisitoEntrada(componente, cantidad))
			this.entrada(componente, cantidad)
			
	def sale(componente: Componente, cantidad: Int) =
	  	if(this.cumpleRequisitoSalida(componente, cantidad))
	  		this.salida(componente, cantidad)
}