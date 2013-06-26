package reservaDeStock

class ExesoDeStock(var loggear: Boolean) extends Interesado {

  override def entrada(componente: Producto, cantidad: Int) =
    println("Se excedio el stock en " + componente.excedente(cantidad))

  override def cumpleRequisitoEntrada(componente: Producto,
    cantidad: Int): Boolean = componente.excedente(cantidad) > 0

}
