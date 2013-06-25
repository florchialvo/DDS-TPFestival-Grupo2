package manejoStock

trait ProductoCompuesto {
  
	var componentes: Set[Componente] = Set()
	
	def agregarComponente(componente: Componente) = 
	  	this.componentes += componente
	  	
	def construir(cantidad: Int) = 
	  	for(componente <- componentes)
	  		componente.utilizar(cantidad)

}