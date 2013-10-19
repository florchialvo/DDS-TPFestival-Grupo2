package ddsGrupo2.festival.model

abstract class Generador[T] extends Serializable {

  def listaBase : List[T]
  
}

class BandasPorFestival(var festival: Festival) extends Generador[Banda] {
	def listaBase = festival.bandas.toList
}

class EntradasPorCliente extends Generador[Entrada] {
  var cliente:String = ""
  def listaBase = FestivalesHome.entradasDeCliente(cliente).toList
}

class EntradasPuestoVta extends Generador[Entrada] {
  var puesto: Int = 0
  def listaBase = FestivalesHome.entradasPuesto(puesto).toList
}