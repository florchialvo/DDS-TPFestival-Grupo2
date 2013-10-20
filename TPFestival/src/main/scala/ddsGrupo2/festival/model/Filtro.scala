package ddsGrupo2.festival.model
import collection.JavaConversions._


abstract class Filtro[T] extends Serializable {

  def condicion(generico: T): Boolean

}

class FiltroBandaContiene extends Filtro[Banda] {
  var bandaContiene: String = ""
  def condicion(banda: Banda) = bandaContiene==null || banda.nombre.toLowerCase().contains(bandaContiene.toLowerCase())
}

class FiltroEntradaFecha extends Filtro[Entrada] {
  var fechaDesde:Fecha = new Fecha(0,0,0)
  var fechaHasta:Fecha = new Fecha(0,0,0)
  def condicion(entrada:Entrada)= entrada.fecha>=fechaDesde && entrada.fecha<=fechaHasta
}

class FiltroPorFestival extends Filtro[Entrada] {
  var festival:Festival = null
  def festivales: java.util.List[Festival] = FestivalesHome.festivales
  def condicion(entrada: Entrada) = festival == null || entrada.nombreFestival == festival.nombre
}