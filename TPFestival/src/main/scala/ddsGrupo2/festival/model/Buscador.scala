package ddsGrupo2.festival.model

import ddsGrupo2.festival.model.exception.NoHayResultadosException

class Buscador[T](val generador: Generador[T], val filtro: Filtro[T]) extends Serializable {
	def buscar:List[T] = {
	  var encontrados = generador.listaBase.filter(e => filtro.condicion(e))
	  validarQueHayResultados(encontrados)
	  encontrados
	}
	
	def validarQueHayResultados(lista : List[T]) =
 	  if (lista.isEmpty) 
	    throw new NoHayResultadosException("No hay resultados para mostrar")

	
}