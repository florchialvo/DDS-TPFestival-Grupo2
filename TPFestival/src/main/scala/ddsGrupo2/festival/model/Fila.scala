package ddsGrupo2.festival.model

import uqbar.arena.persistence.annotations.PersistentClass
import uqbar.arena.persistence.annotations.PersistentField
import org.uqbar.commons.model.Entity

@PersistentClass
case class Fila(var precio: Integer, var cantidadButacas: Integer) extends Entity{

  override def toString() = "(Precio: " + precio + ", CantButacas: " + cantidadButacas + ")"
  
  def this() = this(0, 0)
  
  @PersistentField
  def getPrecio():Integer = precio
  def setPrecio(p:Integer) = (precio = p)
  
  @PersistentField
  def getcantidadButacas():Integer = cantidadButacas
  def setcantidadButacas(c:Integer) = (cantidadButacas = c)  
}