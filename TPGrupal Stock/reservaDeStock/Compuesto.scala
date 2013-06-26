package reservaDeStock

class ProductoCompuesto extends Producto {

  val componentes: List[Producto] = List()

  override def fabricate(inventario: Inventario) = {
    componentes.foreach(componente =>
      componente.reservate(inventario))
    inventario.agregarFabricado(this)
  }
}