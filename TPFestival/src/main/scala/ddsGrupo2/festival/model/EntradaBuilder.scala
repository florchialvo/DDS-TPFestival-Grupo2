package ddsGrupo2.festival.model

class EntradaBuilder(val festival:Festival) {
	var noche:Noche = null
	var tipoPersona:TipoPersona = null
	var fecha:Fecha = null
	var sector:Char = 0
	var fila:Int = 0

	def build:Entrada = festival.nuevaEntrada(fila, sector, fecha, tipoPersona)
}