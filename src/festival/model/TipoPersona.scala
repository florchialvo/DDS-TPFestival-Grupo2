package festival.model

abstract class TipoPersona {
 def descuento(valorBase: Int): Double
}

class Menor extends TipoPersona {
 def descuento(valorBase: Int): Double = {
   if (valorBase > 100) return valorBase * 0.20
   return 10
 }
}

class Mayor extends TipoPersona {
 def descuento(valorBase: Int) = 0
}

class Jubilado extends TipoPersona {
 def descuento(valorBase: Int) = valorBase * 0.15
}