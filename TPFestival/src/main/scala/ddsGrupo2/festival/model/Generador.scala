package ddsGrupo2.festival.model


abstract class Generador[T] {

  def listaBase : List[T]
  
}

class GeneradorBandasPorFestival(var festival: Festival) extends Generador[Banda] {
  
	def listaBase = festival.bandas.toList
}
