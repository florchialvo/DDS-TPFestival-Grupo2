package reservaDeStock
import scala.collection.mutable.ArrayBuffer

class Logger {

  val fecha = new Fecha(0, 0, 0)

  val logueos: ArrayBuffer[Log] = ArrayBuffer()

  def loguear(mensaje: String) {

    logueos += new Log(fecha.fechaActualAsInt, mensaje) 
  }
  
  def mostrarLogueos = logueos.foreach(_.mostrarLog)
  
}

class Log(fecha: Int, mensaje: String) {

  def mostrarLog = println(fecha.toString() + mensaje)
}
