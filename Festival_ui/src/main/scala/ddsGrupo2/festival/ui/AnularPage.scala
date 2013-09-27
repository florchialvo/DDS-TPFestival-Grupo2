package ddsGrupo2.festival.ui

import org.apache.wicket._
import org.apache.wicket.protocol.http._
import org.apache.wicket.markup.html._
import org.apache.wicket.markup.html.form._
import org.apache.wicket.model._
import org.apache.wicket.markup.html.basic.Label

import ddsGrupo2.festival.model._

class AnularPage extends WebPage {
	 val form = new Form("form")
	 form.add(new Label("label", 
	     "Seleccione Fecha, Sector y Fila de la entrada que desea anular"))
	 addLabels(form)
	 addOptions(form)
	 addActions(form)
	 add(form) 
	 
	 def addLabels(form: Form){
	   form.add(new Label("labelFecha", "Fecha"))
	   form.add(new Label("labelSector", "Sector"))
	   form.add(new Label("labelFila", "Fila"))
	 }
	 
	 def addOptions(form: Form){
	    
	 }
	 
	 def addActions(form: Form){
	   
	 }

}