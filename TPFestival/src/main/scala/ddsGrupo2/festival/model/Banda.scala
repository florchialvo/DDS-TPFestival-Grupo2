package ddsGrupo2.festival.model

import uqbar.arena.persistence.annotations.PersistentClass
import org.uqbar.commons.model.Entity
import uqbar.arena.persistence.annotations.PersistentField

@PersistentClass
class Banda(var categoria: Categoria, var nombre: String) extends Entity {

  //Persistencia
  def this() = this(null, "")

  @PersistentField
  def getNombre(): String = nombre
  def setNombre(n: String) = (nombre = n)
  //

  def getValorCategoria = categoria.getValor

  def cambiarCategoria(nuevaCategoria: Categoria) = {
    this.categoria = nuevaCategoria
  }
}