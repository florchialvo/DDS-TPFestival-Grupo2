package festival.model

import festival.model.exception.BusinessException
import scala.collection.mutable.Set

class Festival(var valoresBase: Map[Char, Array[Int]], var fechaVtoEntradasAnticipadas: Fecha) {

  var entradasVendidas: Set[Entrada] = Set()
  var noches: Set[Noche] = Set()
  
  def agregarNoche = noches+=(_:Noche)
    
  def valorBase(fila: Int, sector: Char): Int = valoresBase.apply(sector).apply(fila)

  def estaVendida(fila: Int, sector: Char, fecha: Fecha) =
    entradasVendidas.exists(entrada => entrada.estasVendida(fila, sector, fecha))

  def venderEntrada(fila: Int, sector: Char, fecha: Fecha, persona: TipoPersona): Entrada = {
    validarEntrada(fila, sector, fecha)
    def entrada =
      if (this.esAnticipada(fechaVtoEntradasAnticipadas))
        new EntradaAnticipada(valorBase(fila, sector), noche(fecha), persona, sector, fila)
      else
        new Entrada(valorBase(fila, sector), noche(fecha), persona, sector, fila)
    entradasVendidas += entrada

    return entrada
  }

  def esAnticipada(fechaVto: Fecha) = !(new Fecha().fechaActual > fechaVto)

  def noche(unaFecha: Fecha): Noche = noches.find(_.correspondeA(unaFecha)).get

  def validarEntrada(fila: Int, sector: Char, fecha: Fecha) =
    if (this.estaVendida(fila, sector, fecha))
      throw new BusinessException("La entrada ya está vendida")
}