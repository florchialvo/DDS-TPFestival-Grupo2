package ddsGrupo2.festival.model

abstract class Filtro[T] {

  def condicion(generico: T): Boolean

}

class FiltroBandaContiene extends Filtro[Banda] {
  var bandaContiene: String = ""
  def condicion(banda: Banda) = bandaContiene==null || banda.nombre.contains(bandaContiene)
}
