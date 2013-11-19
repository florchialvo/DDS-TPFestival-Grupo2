package ddsGrupo2.festival.model

import scala.collection.mutable.Buffer
import uqbar.arena.persistence.annotations.PersistentClass
import org.uqbar.commons.model.Entity
import uqbar.arena.persistence.annotations.Relation
import collection.JavaConversions._
import java.util.Arrays
import uqbar.arena.persistence.annotations.PersistentField

@PersistentClass
class Sector(var nombre: String, var filas: Buffer[Fila]) extends Entity {
  def cantFilas = filas.length

  //Persistencia
  def this() = this("", Buffer())

  @PersistentField
  def getNombre(): String = nombre
  def setNombre(s: String) = nombre = s

  @Relation
  def getFilas(): java.util.List[Fila] = filas
  def setFilas(f: java.util.List[Fila]) = (filas = f)
  //

  def precio(fila: Int) = filas(fila).precio
  def cantidadButacas(fila: Int) = filas(fila).cantidadButacas
}

object Sector {
  def apply(nombre: String, filas: Fila*) = new Sector(nombre, filas.toBuffer)
}