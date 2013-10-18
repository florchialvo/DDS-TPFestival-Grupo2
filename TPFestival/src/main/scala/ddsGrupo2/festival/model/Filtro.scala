package ddsGrupo2.festival.model


abstract class Filtro[T] {

  def condicion(generico: T): Boolean

}

class FiltroBandaContiene extends Filtro[Banda] {
  var bandaContiene: String = ""
  def condicion(banda: Banda) = bandaContiene==null || banda.nombre.toLowerCase().contains(bandaContiene.toLowerCase())
}

class FiltroEntradaContiene extends Filtro[Entrada] {
  var clienteContiene: String = ""
  var fechaDesde:Fecha = new Fecha(0,0,0)
  var fechaHasta:Fecha = new Fecha(0,0,0)
  
  def condicion(entrada: Entrada) = (clienteContiene==null || entrada.cliente.toLowerCase().contains(clienteContiene.toLowerCase())) && this.cumpleFecha(entrada)

  def cumpleFecha(entrada:Entrada)= entrada.fecha>=fechaDesde && entrada.fecha<=fechaHasta

}

