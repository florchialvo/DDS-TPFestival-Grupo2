package festival.model

object ConstructorEntrada {
  
  def crearEntrada(valorBase: Int, noche: Noche, persona: TipoPersona, sector: Char, fila: Int):Entrada=
  {
   return new Entrada(valorBase: Int, noche: Noche, persona: TipoPersona, sector: Char, fila: Int)
  }
  
    def crearEntradaAnticipada(valorBase: Int, noche: Noche, persona: TipoPersona, sector: Char, fila: Int):EntradaAnticipada= 
  {
   return new EntradaAnticipada(valorBase: Int, noche: Noche, persona: TipoPersona, sector: Char, fila: Int)
  }
  
  

}