package ddsGrupo2.festival.ui

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.panel._
import org.apache.wicket._

import org.apache.wicket.protocol.http._
import org.apache.wicket.markup.html._
import org.apache.wicket.markup.html.form._
import org.apache.wicket.model._
import org.apache.wicket.markup.html.basic.Label
import collection.JavaConversions._

import ddsGrupo2.festival.model._

class TBasicPage extends WebPage {
 
    var entrada: EntradaBuilder = null
	val form = new Form("entradaForm", this.createModel)
	val buttonVolver = new Button("volver"){
      override def onSubmit(){
       
      }
    }
    
	def setUp(actionButton: Button) {
		form.add(new FeedbackPanel("feedback"))
	    form.add(new DropDownChoice("sector", this.sectores))
	    form.add(new TextField("fila"))
	    form.add(buttonVolver)
	    form.add(actionButton)
	    this.add(form)
	}
    
	def addOptions(){
	  form.add(new DropDownChoice("tipoPersona", this.descuentosValidos))
	}
	
    def descuentosValidos: java.util.List[TipoPersona] =
        new java.util.ArrayList[TipoPersona](entrada.festival.descuentosValidos)

    def sectores: java.util.List[Char] =
        new java.util.ArrayList[Char](entrada.festival.sectores)
    
    def createModel: CompoundPropertyModel = {
	    this.entrada = new EntradaBuilder(FestivalesHome.getFestival)
	    new CompoundPropertyModel(this.entrada)
    }
    
}