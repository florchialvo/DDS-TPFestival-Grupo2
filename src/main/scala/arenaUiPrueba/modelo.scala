package arenaUiPrueba

import org.uqbar.commons.utils.Observable
import festival.model.Entrada
import scala.collection.mutable.ArrayBuffer


abstract class Operacion
{
 var operandos: ArrayBuffer[Int] = new ArrayBuffer()
  
 def operar():Int
 
 def agregarOperando(elem: Int) = operandos+=elem;
  
}

class Suma extends Operacion
{
 
override def operar():Int = return operandos(0)+operandos(1);  

}
class Resta extends Operacion
{
override def operar() :Int = return operandos(0)-operandos(1);  
  
}
class Multiplicacion extends Operacion
{
override def operar() :Int = return operandos(0)*operandos(1);  

}
class Divicion extends Operacion
{
override def operar() :Int = return operandos(0)/operandos(1);  

}


@Observable
class modelo {

  var resultado="";
  var operador : Operacion =null;
  var puedeOperar = false;
  

 
  
  def obtenerResultado() =
    { 
    if (puedeOperar){
    operador.agregarOperando(resultado.toInt)
    
    resultado = operador.operar().toString()
  
    
    
    }
    
    }
  
  def agregarNumero(s:String)={
    
  resultado+=s;
  
  puedeOperar=true;
   
  }
  
 
  def agregarNumero1()= agregarNumero("1")
  def agregarNumero2()= agregarNumero("2")
  def agregarNumero3()= agregarNumero("3")
  def agregarNumero4()= agregarNumero("4")
  def agregarNumero5()= agregarNumero("5")
  def agregarNumero6()= agregarNumero("6")
  def agregarNumero7()= agregarNumero("7")
  def agregarNumero8()= agregarNumero("8")
  def agregarNumero9()= agregarNumero("9")
  def agregarNumero0()= agregarNumero("0")

  def crearOperacion(operacion:Operacion)
  {
    if (puedeOperar)
	    {
			    operador = operacion
			    
			    operador.agregarOperando(resultado.toInt)
			    
			    resultado=""
			      
			    puedeOperar=false;
	    }
  }
  
  def sumar()= crearOperacion(new Suma())
  def restar()= crearOperacion(new Resta())
  def multiplicar()= crearOperacion(new Multiplicacion())
  def dividir()= crearOperacion(new Divicion())
  def resetear()= 	
  {
    resultado="";
	operador  =null;
	puedeOperar = false;
  }
  
  
  
}