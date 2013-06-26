package reservaDeStock

class MateriaPrima extends Producto {

  def utilizar(cantidad: Int) = {
    if (this.hayStock)
      this.consumir(cantidad)
    else
      new Exception("No Hay Materia Prima Suficiente")
  }

  override def fabricate(inventario: Inventario) = {
    throw new StockException("La materia prima no se puede fabricar!")

  }

}