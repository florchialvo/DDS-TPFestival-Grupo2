package ddsGrupo2.festival.ui


import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;

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

class VenderComboPage extends VenderPage {
  

    
   
	val combo = new Combo(FestivalesHome.getFestival)
	
	val botonCombo = new AjaxSubmitLink("agregarAlCombo") {
	  
	 override def onSubmit(destino:AjaxRequestTarget , form:Form ) {
		 		agregarAlCombo()
				destino.addComponent(panelFeedback);
			}

	  
	  
	}
	
	
	form.add(botonCombo)
	

   override def entradaAVender() {
      this.entrada.venderCombo(combo)
       this.info("El Combo fue vendido con éxito")
	}
   
   
   def agregarAlCombo()
   {
     this.entrada.agregarEntradaAlCombo(combo)
      this.info(combo.entradas.map(ent => ent.nombre).toString)
     
   }
   

  
    
 
  
}