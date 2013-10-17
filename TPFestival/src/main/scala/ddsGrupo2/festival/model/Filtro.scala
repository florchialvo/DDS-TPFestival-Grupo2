package ddsGrupo2.festival.model


abstract class Filtro[T] {
  def condicion(e:T);
}


