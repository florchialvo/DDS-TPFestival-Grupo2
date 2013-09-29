package ddsGrupo2.festival.model

import scala.collection.mutable.Set
import java.io.Serializable

class Noche(var bandas: Set[Banda], fecha: Fecha) extends Serializable {
    def fecha_ = fecha

    def agregarBanda(banda: Banda) = bandas += banda

    def valorExtra = this.mayorValorBandaMayorCategoria.getValorCategoria

    def mayorValorBandaMayorCategoria = this.bandas.maxBy({ banda => banda.getValorCategoria })

    def correspondeA(unaFecha: Fecha) = unaFecha == fecha
}