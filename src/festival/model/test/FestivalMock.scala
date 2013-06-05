package festival.model.test
import festival.model._
import scala.collection.mutable.Set


class FestivalMock(entradasVendidas: Set[Entrada] = Set(), fechaVtoEntradasAnticipadas: Fecha,
    noches: Set[Noche], valoresBase: Map[Char, Array[Int]],
  var fechaActual: Fecha) extends Festival(entradasVendidas: Set[Entrada], fechaVtoEntradasAnticipadas: Fecha, noches: Set[Noche], valoresBase: Map[Char, Array[Int]]) {

  override def esAnticipada(fechaVto: Fecha) = !(fechaActual > fechaVto)

}