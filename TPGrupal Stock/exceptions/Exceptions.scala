package exceptions
import java.lang.RuntimeException

class StockException(mensaje: String) extends RuntimeException(mensaje)