package ddsGrupo2.festival.ui

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.panel._
import org.apache.wicket._
import org.apache.wicket.protocol.http._
import org.apache.wicket.markup.html._
import org.apache.wicket.markup.html.form._
import org.apache.wicket.model._
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior
import ddsGrupo2.festival.model._
import org.apache.wicket.markup.html.list.ListView
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink
import collection.JavaConversions._

class BuscadorPage extends WebPage  {

  var buscador = new Buscador(List(
      new Entrada(FestivalesHome.getFestival,10,FestivalesHome.getNoche,Dama,'A',1,1),
      new Entrada(FestivalesHome.getFestival,100,FestivalesHome.getNoche,Mayor,'B',2,1),
      new Entrada(FestivalesHome.getFestival,1000,FestivalesHome.getNoche,Menor,'A',3,1)
      
      )
      
  )
  
  
   val form = new Form("buscadorForm",  this.createModel)
  
  
		val resultSection = this.createResultsSection();
		form.add(this.resultSection);


	def  createResultsSection(): WebMarkupContainer = {
		val panel = new WebMarkupContainer("resultSection");
		panel.setOutputMarkupId(true);
		panel.add( 
		 
		    new ListView("listaResultados",new ComponentPropertyModel("resultado")){
		    	
		    		def populateItem(  item:ListItem[Nothing]) = 
					 	{
					  item.add(new Label("precio"))
					   
					 	}
		    }
		);
		return panel;
	}
		
	
			
		  val botonBuscar = new AjaxSubmitLink("buscar"){
    override def onSubmit(destino: AjaxRequestTarget, form: Form[_]) {
      buscador.buscar;
       destino.add(resultSection)
    }
}
 
		  
		  form.add(botonBuscar)
		  
		  this.add(form)
  
  
   def createModel: CompoundPropertyModel[Buscador] = {
    new CompoundPropertyModel(this.buscador)
  }
  
		  
		  
def resultado:java.util.List[_] = buscador.resultado
		  
}