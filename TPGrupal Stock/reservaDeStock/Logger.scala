package reservaDeStock
import scala.collection.mutable.ArrayBuffer

class Logger {

  val fecha = new Fecha(0, 0, 0)

  val logueos: ArrayBuffer[Log] = ArrayBuffer()

  def loguear(mensaje: String) {

<<<<<<< HEAD
    logueos+= new Log(fecha.fechaActualAsInt, mensaje) 
=======
    logueos += new Log(fecha.fechaActualAsInt, mensaje) 
>>>>>>> 62ac88272456879a8d9fc700a5725e3aba58c62d
  }
  
  def mostrarLogueos = logueos.foreach(_.mostrarLog)
  
}

class Log(fecha: Int, mensaje: String) {

  def mostrarLog = println(fecha.toString() + mensaje)
}
