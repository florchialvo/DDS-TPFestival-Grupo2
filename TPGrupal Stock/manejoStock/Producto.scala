package manejoStock

class Producto {
  var stockMinimo: Int
  var stockMaximo: Int
  var stock: Int
  var puntoDePedido: Int
  var nombre: String
  var logger: Logger
  var mailSender: MailSender
  
  def entraComponente(cant:Int)={
		if(stock>stockMinimo)
			if(this.soyAudtiable) 
				this.logger.log(“El producto ” + 
				componente.nombre + 
				“ subio del stock minimo”)
			if (stock>stockMax && this.registroExcesos){
				println(“Se excedio el stock en “+ componente.excedente(cantidad))
			}
  		}
  
  def saleComponente(cant:Int)={
		if(stock<stockMinimo){
			this.bajoStockMinimo()
			if(this.soyAudtiable) 
				this.logger.log(“El producto ” + 
				componente.nombre + 
				“ bajo del stock minimo”)
		}
		if (cant>cantidadLimite && this.puedoMandarMail)
			this.mailSender.enviarMail(componente,cantidad)
  }


}