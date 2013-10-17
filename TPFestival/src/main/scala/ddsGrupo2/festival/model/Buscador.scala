package ddsGrupo2.festival.model

class Buscador[T](val generador: Generador[T], val filtro: Filtro[T]) {
	def buscar = generador.listaBase.filter(e => filtro.condicion(e))
}