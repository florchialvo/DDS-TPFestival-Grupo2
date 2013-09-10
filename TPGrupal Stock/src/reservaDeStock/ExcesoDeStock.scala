package reservaDeStock

class ExcesoDeStock(var loggear: Boolean) extends Interesado {

  val logger = new Logger

  override def entrada(componente: Producto, cantidad: Int) =
    if (loggear) {
      logger.loguear("Se excedio el stock en " + componente.excedente(cantidad))
    }

  override def cumpleRequisitoEntrada(componente: Producto,
    cantidad: Int): Boolean = componente.excedente(cantidad) > 0

}
