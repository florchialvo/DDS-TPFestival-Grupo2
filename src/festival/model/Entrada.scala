package festival.model

class Entrada(valorBase: Int, noche: Noche, persona: TipoPersona, sector: Char, fila: Int) {

  def valorExtraPorNoche = noche.valorExtra

  def descuento = persona.descuento(valorBase)

  def precio = this.valorExtraPorNoche + valorBase - this.descuento 
  
  def estasVendida(unaFila: Int, unSector: Char, unaFecha: Int) = 
    unaFila == fila && unSector == sector && noche.correspondeA(unaFecha)

}

class EntradaAnticipada(valorBase: Int, noche: Noche, persona: TipoPersona, sector: Char, fila: Int) 
extends Entrada(valorBase: Int, noche: Noche, persona: TipoPersona, sector: Char, fila: Int) {
  
	override def precio = super.precio*(1-EntradaAnticipada.descuento)
}

object EntradaAnticipada {
  
  val descuento : Double = 0.15
  
}