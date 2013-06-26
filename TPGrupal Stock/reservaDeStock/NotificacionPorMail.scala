package reservaDeStock

class NotificacionPorMail(val cantidadLimite: Int) extends Interesado {

  val mailSender = new MailSender()

  override def salida(componente: Producto, cantidad: Int) =
    this.mailSender.enviarMail(componente, cantidad)

  override def cumpleRequisitoSalida(componente: Producto, cantidad: Int): Boolean =
    cantidad > cantidadLimite
}
