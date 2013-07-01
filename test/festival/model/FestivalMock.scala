package festival.model
import festival.model._
import scala.collection.mutable.Set


class FestivalMock(valoresBase: Map[Char, Array[Int]], fechaVtoEntradasAnticipadas: Fecha, var fechaActual:Fecha)
 	   extends Festival(valoresBase: Map[Char, Array[Int]], fechaVtoEntradasAnticipadas: Fecha) {

  override def esAnticipada(fechaVto: Fecha) = !(fechaActual > fechaVto)

}