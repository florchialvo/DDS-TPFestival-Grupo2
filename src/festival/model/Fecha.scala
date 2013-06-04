package festival.model

import java.util.Calendar
import java.text.SimpleDateFormat

class Fecha(_dia: Int, _mes: Int, _año: Int) {
  
  def this() = this(0,0,0)
  
  def dia = _dia
  def mes = _mes
  def año = _año
  
  def fechaActual():Fecha =  {
    val today = Calendar.getInstance().getTime()
    
    val diaFormat = new SimpleDateFormat("dd")
    val mesFormat = new SimpleDateFormat("MM")
    val añoFormat = new SimpleDateFormat("yyyy")
    
    val diaObtenido = Integer.parseInt(diaFormat.format(today))
    val mesObtenido = Integer.parseInt(mesFormat.format(today))
    val añoObtenido = Integer.parseInt(añoFormat.format(today))
    
    return new Fecha(diaObtenido,mesObtenido,añoObtenido)    
  }
  
  
  def toInt = this.año*10000+this.mes*100+this.dia
  def ==(otraFecha: Fecha) = (this.año==otraFecha.año)&&(this.mes==otraFecha.mes)&&(this.dia==otraFecha.dia)
  def >(otraFecha: Fecha) = this.toInt>otraFecha.toInt
}