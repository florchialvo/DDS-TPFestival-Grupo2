package ddsGrupo2.festival.model

import collection.JavaConversions._

abstract class Generador[T] extends Serializable {
  def listaBase : List[T]
}

class BandasPorFestival extends Generador[Banda] {
	var festival: Festival = FestivalesHome.festivales.head
			
	def listaFestivales: java.util.List[Festival]  = FestivalesHome.festivales//.map(f => f.nombre)
	def listaBase = festival.bandas.toList
}

class EntradasPorCliente extends Generador[Entrada] {
  var cliente:String = ""
  def listaBase = FestivalesHome.entradasDeCliente(cliente).toList
}

//class EntradasPuestoVta extends Generador[Entrada] {
//  var puesto: Int = 0
//  def listaBase = FestivalesHome.entradasPuesto(puesto).toList
//}
