package ddsGrupo2.festival.model


abstract class Generador[T] {

  def listaBase : List[T]
  
}

class BandasPorFestival(var festival: Festival) extends Generador[Banda] {
	def listaBase = festival.bandas.toList
}


class EntradasPorCliente extends Generador[Entrada] {
  var cliente:String = ""
  def listaBase = FestivalesHome.entradasDeCliente(cliente).toList
}
