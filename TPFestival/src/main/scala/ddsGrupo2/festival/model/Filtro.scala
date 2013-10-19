package ddsGrupo2.festival.model


abstract class Filtro[T] {

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

