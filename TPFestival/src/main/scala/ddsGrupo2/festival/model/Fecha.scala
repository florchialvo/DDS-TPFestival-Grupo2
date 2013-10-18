package ddsGrupo2.festival.model

import java.util.Calendar
import java.text.SimpleDateFormat
import java.io.Serializable

class Fecha(var dia: Int, var mes: Int, var anio: Int) extends Serializable{

    def this() = this(0, 0, 0)

    def fechaActual(): Fecha = {
        val today = Calendar.getInstance().getTime()

        val diaFormat = new SimpleDateFormat("dd")
        val mesFormat = new SimpleDateFormat("MM")
        val anioFormat = new SimpleDateFormat("yyyy")

        val diaObtenido = Integer.parseInt(diaFormat.format(today))
        val mesObtenido = Integer.parseInt(mesFormat.format(today))
        val anioObtenido = Integer.parseInt(anioFormat.format(today))

        return new Fecha(diaObtenido, mesObtenido, anioObtenido)
    }

    override def toString():String= this.dia.toString() + "/" +
    					 this.mes.toString() + "/" +
    					 this.anio.toString()
    
    def toInt = this.anio * 10000 + this.mes * 100 + this.dia
    def ==(otraFecha: Fecha) = (this.anio == otraFecha.anio) && 
    						   (this.mes == otraFecha.mes) &&
    						   (this.dia == otraFecha.dia)
    def >(otraFecha: Fecha) = this.toInt > otraFecha.toInt
    def >=(otraFecha: Fecha) = this.toInt >= otraFecha.toInt
    def <(otraFecha: Fecha) = this.toInt < otraFecha.toInt
    def <=(otraFecha: Fecha) = this.toInt <= otraFecha.toInt
}