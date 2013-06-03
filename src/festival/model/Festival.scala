package festival.model

class Festival(
    entradasVendidas: List[Entrada], 
    fechaVtoEntradasAnticipadas: Int, 
    noches: List[Noche], 
    valoresBase: Map[Char, Array[Int]]) {
  
  def valorBase(fila: Int, sector: Char) : Int = valoresBase.apply(sector).apply(fila)
  
  def estaVendida(fila: Int, sector: Char, fecha: Int) = 
    entradasVendidas.exists(entrada => entrada.estasVendida(fila, sector, fecha))
  
}