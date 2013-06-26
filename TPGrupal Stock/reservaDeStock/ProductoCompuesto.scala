package reservaDeStock

import scala.collection.mutable.ArrayBuffer

class ProductoCompuesto extends Producto {

  val componentes: ArrayBuffer[Producto] = ArrayBuffer()

    override def fabricate(inventario: Inventario) = {
    componentes.foreach(componente =>
      componente.reservate(inventario))
    inventario.agregarFabricado(this)
  }
  
  def agregarComponente(comp:Producto)={
    componentes+=comp
  }

}