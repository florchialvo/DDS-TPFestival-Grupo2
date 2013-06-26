package manejoStock

import scala.collection.mutable.Map
import exceptions.StockException

class Fabrica {
  def registrarPedido(componente: Producto, puntoDePedido: Int) = {}

  def reservar(producto: Producto) = {
    try {
      val inventario = new Inventario()
      producto.reservate(inventario)
    } catch (StockException exception) {
      inventario.rollback()
    }
  }
}

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