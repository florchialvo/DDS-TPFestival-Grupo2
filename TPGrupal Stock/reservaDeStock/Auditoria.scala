package reservaDeStock

object Auditoria extends Interesado {

  val logger = new Logger

  override def salida(componente: Producto, cantidad: Int) =
    this.logger.loguear("El producto " + componente.nombre + " bajo del stock minimo")

  override def entrada(componente: Producto, cantidad: Int) =
    this.logger.loguear("El producto " + componente.nombre + " dejo de estar por debajo del stock minimo")

  override def cumpleRequisitoEntrada(componente: Producto, cantidad: Int): Boolean =
    componente.tenesStockMinimo

  override def cumpleRequisitoSalida(componente: Producto, cantidad: Int): Boolean =
    !componente.tenesStockMinimo

}
