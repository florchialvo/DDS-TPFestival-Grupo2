package festival.model

class Noche (bandas: List[Banda], fecha: Int) {
  
  def agregarBanda(banda: Banda) = bandas :+ banda
  
  def valorExtra = this.mayorValorBandaMayorCategoria.getValorCategoria
  
  def mayorValorBandaMayorCategoria = this.bandas.maxBy({ banda => banda.getValorCategoria })
  

}