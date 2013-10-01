package ddsGrupo2.festival.ui


import org.apache.wicket.markup.html.WebPage
import org.apache.wicket._

import org.apache.wicket.protocol.http._
import org.apache.wicket.markup.html._
import org.apache.wicket.markup.html.form._
import org.apache.wicket.model._
import org.apache.wicket.markup.html.basic.Label
import collection.JavaConversions._

import ddsGrupo2.festival.model._
import ddsGrupo2.festival.model.exception._

class AnularPage extends EntradaBasicPage {
  
    val buttonAnular = new ButtonAction[EntradaNoVendidaException](this, "anular", {() => this.entradaAnular()})
       
	setUp(buttonAnular)
	
    def entradaAnular() {
	  this.entrada.anularEntrada()
	  this.info("Entrada anulada con éxito")
	}

}