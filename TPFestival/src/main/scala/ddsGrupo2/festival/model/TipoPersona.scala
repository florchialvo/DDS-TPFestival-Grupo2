package ddsGrupo2.festival.model

abstract class TipoPersona {
    def descuento(valorBase: Int): Double
    def esPosibleEn(f: Festival): Boolean
    
    override def toString = this.getClass().getSimpleName()
}

abstract class TipoPersonaSinCondicion extends TipoPersona {
    def esPosibleEn(f: Festival) = true
}

object Menor extends TipoPersonaSinCondicion {
    def descuento(valorBase: Int): Double = {
        if (valorBase > 100)
            return valorBase * 0.20
        return 10
    }
}

object Mayor extends TipoPersonaSinCondicion {
    def descuento(valorBase: Int) = 0
}

object Jubilado extends TipoPersonaSinCondicion {
    def descuento(valorBase: Int) = valorBase * 0.15
}

object Dama extends TipoPersona {
    val porcentajeMaximo = 20
    def descuento(valorBase: Int): Double = valorBase * 0.2

    def esPosibleEn(unFestival: Festival) =
        unFestival.porcentajeVendidoDamas < porcentajeMaximo
}

object MenorDe12Acompaniado extends TipoPersonaSinCondicion {
    def descuento(valorBase: Int): Double = valorBase * 0.5
}