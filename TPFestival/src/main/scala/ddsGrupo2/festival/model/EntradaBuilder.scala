package ddsGrupo2.festival.model

class EntradaBuilder(val festival:Festival) {
    var _entrada:Entrada
    
	var fecha:Fecha
	var tipoPersona:TipoPersona
	var sector:Char
	var fila:Int
	
	def entrada:Entrada = {
        if (_entrada == null){
        	this.validar
        	_entrada = festival.nuevaEntrada(fila, sector, fecha, tipoPersona)
        }
        return _entrada
    }
    
    def validar ={
        if(fecha==null)
            throw new RuntimeException("La entrada no tiene una fecha")
        if(tipoPersona==null)
            throw new RuntimeException("La entrada no tiene un tipo de persona")
        if(sector==null)
            throw new RuntimeException("La entrada no tiene sector")
        if(fila==null)
            throw new RuntimeException("La entrada no tiene fila")        
    }
    
    def precio = {
        
    }
    	
}