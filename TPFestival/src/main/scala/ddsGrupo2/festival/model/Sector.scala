package ddsGrupo2.festival.model

import scala.collection.mutable.ArrayBuffer
import uqbar.arena.persistence.annotations.PersistentClass
import org.uqbar.commons.model.Entity
import uqbar.arena.persistence.annotations.Relation
import collection.JavaConversions._
import java.util.Arrays

@PersistentClass
class Sector (var nombre:String, var filas: Array[Fila]) extends Entity{
	def cantFilas = filas.length
	
	//Persistencia
	def this() = this(null, null)
	
	@Relation
	def getFilas():java.util.List[Fila] = filas.toList
	def setFilas(f:java.util.List[Fila]) = (filas = f.toList.toArray)
}

object Sector {
  def apply(nombre: String, filas: Fila*) = new Sector(nombre, filas.toArray)
}