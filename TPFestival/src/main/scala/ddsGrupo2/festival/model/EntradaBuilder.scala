package ddsGrupo2.festival.model

class EntradaBuilder(val festival:Festival) {
	var noche:Noche
	var tipoPersona:TipoPersona
	var fecha:Fecha
	var sector:Char
	var fila:Int

	def build:Entrada = festival.nuevaEntrada(fila, sector, fecha, tipoPersona)
}