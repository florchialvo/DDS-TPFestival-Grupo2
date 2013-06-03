package festival.model

class Festival(entradasVendidas: List[Entrada], fechaVtoEntradasAnticipadas: Int, 
    noches: List[Noche], valoresBase: Map[Char, Array[Int]] {
  
  def valorBase(fila: Int, sector: Char)=  valoresBase.apply(key: sector)
	
	
  
}