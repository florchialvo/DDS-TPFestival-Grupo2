package ddsGrupo2.festival.model

import uqbar.arena.persistence.annotations.PersistentClass
import uqbar.arena.persistence.annotations.PersistentField
import org.uqbar.commons.model.Entity

@PersistentClass
class Fila(var precio: Integer, var cantidadButacas: Integer) extends Entity{

  def this() = this(null, null)
  
  @PersistentField
  def getPrecio():Integer = precio
  def setPrecio(p:Integer) = (precio = p)
  
  @PersistentField
  def getcantidadButacas():Integer = cantidadButacas
  def setcantidadButacas(c:Integer) = (cantidadButacas = c)  
}

object Fila {
  def apply(precio: Int, cant: Int) = new Fila(precio, cant)
}
