package reservaDeStock


class Inventario {
  val reservados: Map[Producto, Int] = Map()
  val fabricados: Map[Producto, Int] = Map()

  def agregarFabricado(prod: Producto) = agregar(fabricados, prod)
  def agregarReservado(prod: Producto) = agregar(reservados, prod)

  def rollback = {
    this.actualizarStock
    this.borrarEntradas
  }
  def actualizarStock = {
    for ((prod, cant) <- fabricados) prod.incrementarStock(cant)
    for ((prod, cant) <- reservados) prod.incrementarStock(cant)
  }
  def borrarEntradas() = {
    reservados.clear
    fabricados.clear
  }

  def agregar(diccionario: Map[Producto, Int], prod: Producto) = {
    if (diccionario.contains(prod))
      diccionario(prod) = diccionario(prod) + 1
    else
      diccionario += (prod -> 1)
  }

}