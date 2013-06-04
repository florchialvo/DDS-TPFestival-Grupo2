package festival.model

import java.util.Calendar
import java.text.SimpleDateFormat

class Festival(
    entradasVendidas: List[Entrada], 
    fechaVtoEntradasAnticipadas: Fecha, 
    noches: List[Noche], 
    valoresBase: Map[Char, Array[Int]]) {
  
  def valorBase(fila: Int, sector: Char) : Int = valoresBase.apply(sector).apply(fila)
  
  def estaVendida(fila: Int, sector: Char, fecha: Int) = 
    entradasVendidas.exists(entrada => entrada.estasVendida(fila, sector, fecha))
  
  def obtenerFechaDelSistema():Fecha =
  {
    val today = Calendar.getInstance().getTime()
    
    val diaFormat = new SimpleDateFormat("dd")
    val mesFormat = new SimpleDateFormat("MM")
    val añoFormat = new SimpleDateFormat("yyyy")
    
    val diaObtenido = Integer.parseInt(diaFormat.format(today))
    val mesObtenido = Integer.parseInt(diaFormat.format(today))
    val añoObtenido = Integer.parseInt(diaFormat.format(today))
    
    var fechaDelSistema = new Fecha(diaObtenido,mesObtenido,añoObtenido)
    
    
    return fechaDelSistema
    
  }
  
  
  def venderEntrada(fila: Int, sector: Char,noche:Noche, persona:TipoPersona)
  ={
    
    if (this.puedoCrearEntradaAnticipada(this.fechaVtoEntradasAnticipadas,this.obtenerFechaDelSistema))
      this.entradasVendidas.+:(ConstructorEntrada.crearEntrada(this.valorBase(fila,sector), noche, persona, sector, fila))
      
    else
       this.entradasVendidas.+:(ConstructorEntrada.crearEntradaAnticipada(this.valorBase(fila,sector), noche, persona, sector, fila))
     
  }
  
  
  def puedoCrearEntradaAnticipada(fechaVto:Fecha, fechaActual:Fecha): Boolean = return (fechaActual.año<=fechaVto.año)&&(fechaActual.mes<=fechaVto.mes)&&(fechaActual.dia<=fechaVto.dia)
 
 
  
  
}