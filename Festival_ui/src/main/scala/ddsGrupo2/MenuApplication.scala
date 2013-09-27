package ddsGrupo2;

import org.apache.wicket._
import org.apache.wicket.protocol.http._
import org.apache.wicket.markup.html._
import org.apache.wicket.markup.html.form._
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.markup.html.basic.Label

class MenuApplication extends WebApplication { 
   def getHomePage = classOf[MenuPage]
}

class MenuPage extends WebPage{
   val form = new Form("form")
   val buttonVender = new Button("vender") {
     override def onSubmit() {
       //this.setResponsePage(classOf[VenderPage])
     }
   }
   
   val buttonAnular = new Button("anular") {
   override def onSubmit() {
       this.setResponsePage(classOf[AnularPage])
     }
   } 
   addFields(form)  
   addActions(form)
   add(form)
   

   def addActions(form : Form){
     form.add(buttonVender)
     form.add(buttonAnular)
   }
   
   def addFields(form: Form){
     val label = new Label("label", "Elija una operación a realizar")
     form.add(label)
     
   }
   
}

