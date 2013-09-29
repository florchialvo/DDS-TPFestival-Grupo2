package ddsGrupo2.festival.model
import java.io.Serializable

class Entrada(var festival: Festival, valorBase: Int, noche: Noche, var persona: TipoPersona,
              sector: Char, fila: Int) extends Serializable{
    def fila_ = fila
    def sector_ = sector
    def fecha_ = noche.fecha_
    def valorExtraPorNoche = noche.valorExtra
    def descuento = festival.descuento(persona, valorBase)
    def precio = this.valorExtraPorNoche + valorBase - this.descuento

    def estasVendida(unaFila: Int, unSector: Char, unaFecha: Fecha) =
        unaFila == fila && unSector == sector && noche.correspondeA(unaFecha)
}

class EntradaAnticipada(festival: Festival, valorBase: Int, noche: Noche, persona: TipoPersona, sector: Char, fila: Int)
        extends Entrada(festival: Festival, valorBase: Int, noche: Noche, persona: TipoPersona, sector: Char, fila: Int) {

    override def precio = super.precio * (1 - EntradaAnticipada.descuento)
}

object EntradaAnticipada {
    val descuento: Double = 0.15
}