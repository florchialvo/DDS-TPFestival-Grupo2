package festival.model

class Noche (bandas: List[Banda], fecha: Fecha) {
  
  def agregarBanda(banda: Banda) = bandas :+ banda
  
  def valorExtra = this.mayorValorBandaMayorCategoria.getValorCategoria
  
  def mayorValorBandaMayorCategoria = this.bandas.maxBy({ banda => banda.getValorCategoria })
  
  def correspondeA(unaFecha: Fecha) = unaFecha == fecha

}