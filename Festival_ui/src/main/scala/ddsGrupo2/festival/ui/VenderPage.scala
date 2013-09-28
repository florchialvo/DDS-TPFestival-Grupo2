package ddsGrupo2.festival.ui


import org.apache.wicket.markup.html.WebPage
import org.apache.wicket._

import org.apache.wicket.protocol.http._
import org.apache.wicket.markup.html._
import org.apache.wicket.markup.html.form._
import org.apache.wicket.model._
import org.apache.wicket.markup.html.basic.Label
import collection.JavaConversions._
import org.apache.wicket.feedback.FeedbackMessage

import ddsGrupo2.festival.model._
import ddsGrupo2.festival.model.exception._


class VenderPage extends TBasicPage {
  
	val self = this
	
    val buttonVender = new Button("vender") {
      override def onSubmit() {
        try{
          self.entradaAVender()
        }catch{
          case e: EntradaYaVendidaException =>
          self.error(e.getMessage())
        }
      }
    }
	
	setUp(buttonVender)
	
    def entradaAVender() = this.entrada.venderEntrada()
    
    override def setUp(buttonVender: Button){
      super.setUp(buttonVender)
      this.addOptions()
	}	
}
