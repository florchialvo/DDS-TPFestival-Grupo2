package ddsGrupo2.festival.model

import scala.collection.immutable.Map

object AsientosHome {
  
  val asientos = Map('A' -> Array(10, 20, 30), 'B' -> Array(50, 60, 70, 80), 'C' -> Array(90,100))
  
  def getAsientos() = asientos
  
  def getSectores() = asientos.keySet
  
}