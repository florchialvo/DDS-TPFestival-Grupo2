package festival.model

class Entrada(valorBase: Int, noche: Noche, persona: TipoPersona, sector: Char) {

  def valorExtra = noche.valorExtra

  def descuento = persona.descuento(valorBase)

  def precio = this.valorExtra + valorBase - this.descuento 

}

class EntradaAnticipada(valorBase: Int, noche: Noche, persona: TipoPersona, sector: Char) 
extends Entrada(valorBase: Int, noche: Noche, persona: TipoPersona, sector: Char) {
  
	override def precio = super.precio*(1-EntradaAnticipada.descuento)
}

object EntradaAnticipada {
  
  val descuento : Double = 0.1
  
}