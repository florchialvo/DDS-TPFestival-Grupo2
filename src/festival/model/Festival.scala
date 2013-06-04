package festival.model

import festival.model.exception.BusinessException

class Festival(
    var entradasVendidas: List[Entrada], 
    fechaVtoEntradasAnticipadas: Fecha, 
    noches: List[Noche], 
    valoresBase: Map[Char, Array[Int]]) {
  
  def valorBase(fila: Int, sector: Char) : Int = valoresBase.apply(sector).apply(fila)
  
  def estaVendida(fila: Int, sector: Char, fecha: Fecha) = 
    entradasVendidas.exists(entrada => entrada.estasVendida(fila, sector, fecha))
 
  def venderEntrada(fila: Int, sector: Char, fecha: Fecha, persona: TipoPersona) = {
    validarEntrada(fila, sector, fecha)
    def entrada = 
	    if (fechaVtoEntradasAnticipadas.seVencio)
	      new EntradaAnticipada(valorBase(fila, sector), noche(fecha), persona, sector, fila)
	    else
	      new Entrada(valorBase(fila, sector), noche(fecha), persona, sector, fila)
    entradasVendidas :+ entrada
  }
  
  
  def noche(unaFecha: Fecha) : Noche = noches.find(_.correspondeA(unaFecha)).get
  
  def validarEntrada(fila: Int, sector: Char, fecha: Fecha) = 
    if (this.estaVendida(fila, sector, fecha)) 
      throw new BusinessException("La entrada ya está vendida")
}