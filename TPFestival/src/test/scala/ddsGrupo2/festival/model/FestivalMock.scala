package ddsGrupo2.festival.model

import scala.collection.mutable.Set

class FestivalMock(valoresBase: Map[Char, Array[(Int, Int)]], fechaVtoEntradasAnticipadas: Fecha, var fechaActual:Fecha)
 	   extends Festival(valoresBase: Map[Char, Array[(Int, Int)]], fechaVtoEntradasAnticipadas: Fecha) {

  override def esAnticipada = !(fechaActual > fechaVtoEntradasAnticipadas)

  var porcentajeDamas: Int = 0
  
  def setPorcentajeDamas(valor: Int) = porcentajeDamas = valor
  
  override def porcentajeVendidoDamas() = porcentajeDamas
}