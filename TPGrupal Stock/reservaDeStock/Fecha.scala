package reservaDeStock

import java.util.Calendar
import java.text.SimpleDateFormat

class Fecha(_dia: Int, _mes: Int, _anio: Int) {
  
  def this() = this(0,0,0)
  
  def dia = _dia
  def mes = _mes
  def anio = _anio
  
  def fechaActual():Fecha =  {
    val today = Calendar.getInstance().getTime()
    
    val diaFormat = new SimpleDateFormat("dd")
    val mesFormat = new SimpleDateFormat("MM")
    val anioFormat = new SimpleDateFormat("yyyy")
    
    val diaObtenido = Integer.parseInt(diaFormat.format(today))
    val mesObtenido = Integer.parseInt(mesFormat.format(today))
    val anioObtenido = Integer.parseInt(anioFormat.format(today))
    
    return new Fecha(diaObtenido,mesObtenido,anioObtenido)    
  }
  
  def fechaActualAsInt() = this.fechaActual.toInt
  
  
  def toInt = this.anio*10000+this.mes*100+this.dia
  
}
