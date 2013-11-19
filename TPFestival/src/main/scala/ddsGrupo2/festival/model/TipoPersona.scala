package ddsGrupo2.festival.model

import uqbar.arena.persistence.annotations.PersistentClass
import org.uqbar.commons.model.Entity

@PersistentClass
abstract class TipoPersona extends Entity{
    def descuento(valorBase: Int): Double
    def esPosibleEn(f: Festival): Boolean
    
    override def toString = this.getClass().getSimpleName()
}

@PersistentClass
abstract class TipoPersonaSinCondicion extends TipoPersona {
    def esPosibleEn(f: Festival) = true
}

@PersistentClass
case class Menor extends TipoPersonaSinCondicion {
    def descuento(valorBase: Int): Double = {
        if (valorBase > 100)
            return valorBase * 0.20
        return 10
    }
}

@PersistentClass
case class Mayor extends TipoPersonaSinCondicion {
    def descuento(valorBase: Int) = 0
}

@PersistentClass
case class Jubilado extends TipoPersonaSinCondicion {
    def descuento(valorBase: Int) = valorBase * 0.15
}

@PersistentClass
case class Dama extends TipoPersona {
    val porcentajeMaximo = 20
    def descuento(valorBase: Int): Double = valorBase * 0.2

    def esPosibleEn(unFestival: Festival) =
        unFestival.porcentajeVendidoDamas < porcentajeMaximo
}

@PersistentClass
case class MenorDe12Acompaniado extends TipoPersonaSinCondicion {
    def descuento(valorBase: Int): Double = valorBase * 0.5
}