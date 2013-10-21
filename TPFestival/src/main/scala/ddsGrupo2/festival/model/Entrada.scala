package ddsGrupo2.festival.model
import java.io.Serializable

class Entrada(var festival: Festival, valorBase: Int, noche: Noche, var persona: TipoPersona,
              var sector: Char, var fila: Int, var numButaca: Int,val cliente:String = "", val puestoDeVenta:Int = 0) extends Serializable{
    def fecha = noche.fecha
    def valorExtraPorNoche = noche.valorExtra
    def descuento = festival.descuento(persona, valorBase)
    def precio = this.valorExtraPorNoche + valorBase - this.descuento
    def nombre = "Fecha:" + this.fecha.toString() +
      			 "Fila:" + this.fila.toString() +
    			 "-Sector:" +  this.sector.toString() +
    			 "-Butaca:" +  this.numButaca.toString()

	def nombreFestival = festival.nombre

    def estasVendida(unaFila: Int, unSector: Char, unaButaca:Int, unaFecha: Fecha) =
        unaFila == fila && unSector == sector && 
        unaButaca == numButaca && noche.correspondeA(unaFecha)
        
    def bandas = noche.bandas
}


class EntradaAnticipada(festival: Festival, valorBase: Int, noche: Noche, persona: TipoPersona, sector: Char, fila: Int, numButaca: Int,cliente:String = "", puntoDeVenta:Int = 0)
        extends Entrada(festival: Festival, valorBase: Int, noche: Noche, persona: TipoPersona, sector: Char, fila: Int, numButaca: Int,cliente:String, puntoDeVenta:Int) {

    override def precio = super.precio * (1 - EntradaAnticipada.descuento)
}

object EntradaAnticipada {
    val descuento: Double = 0.15
}