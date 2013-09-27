package ddsGrupo2.festival.model

import scala.collection.mutable.Set

class FestivalMock(valoresBase: Map[Char, Array[Int]], fechaVtoEntradasAnticipadas: Fecha, var fechaActual:Fecha)
 	   extends Festival(valoresBase: Map[Char, Array[Int]], fechaVtoEntradasAnticipadas: Fecha) {

  override def esAnticipada(fechaVto: Fecha) = !(fechaActual > fechaVto)

  var porcentajeDamas: Int = 0
  
  def setPorcentajeDamas(valor: Int) = porcentajeDamas = valor
  
  override def porcentajeVendidoDamas() = porcentajeDamas
}