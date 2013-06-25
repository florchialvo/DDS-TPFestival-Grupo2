package manejoStock

object NotificacionPorMail extends Interesado {

	override def salida(componente: Producto, cantidad: Int) = 
		this.mailSender.enviarMail(componente, cantidad)

	override def cumpleRequisitoSalida(componente: Producto, cantidad: Int) : Boolean =
	  	cantidad > cantidadLimite
}