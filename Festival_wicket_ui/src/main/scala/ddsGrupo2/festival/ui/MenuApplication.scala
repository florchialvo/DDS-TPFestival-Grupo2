package ddsGrupo2.festival.ui

import org.apache.wicket._
import org.apache.wicket.protocol.http._
import org.apache.wicket.markup.html._
import org.apache.wicket.markup.html.form._
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.markup.html.basic.Label
import ddsGrupo2.festival.model.EntradaApplicationModel

class MenuApplication extends WebApplication { 
   def getHomePage = classOf[MenuPage]
}

class MenuPage extends WebPage{
   val form = new Form[EntradaApplicationModel]("form")
   val buttonVender = new Button("vender") {
     override def onSubmit() {
       this.setResponsePage(classOf[VenderPage])
     }
   }
   
   val buttonAnular = new Button("anular") {
   override def onSubmit() {
       this.setResponsePage(classOf[AnularPage])
     }
   } 
   
   
     val buttonCombo = new Button("vender_combo") {
   override def onSubmit() {
       this.setResponsePage(classOf[VenderComboPage])
     }
   } 
     
     
        val buttonBuscar = new Button("buscar") {
   override def onSubmit() {
       this.setResponsePage(classOf[BuscadorPage])
     }
   } 
     
   addFields(form)  
   addActions(form)
   add(form)
   

   def addActions(form: Form[EntradaApplicationModel]){
     form.add(buttonVender)
      form.add(buttonBuscar)
     form.add(buttonAnular)
     form.add(buttonCombo)
   }
   
   def addFields(form: Form[EntradaApplicationModel]){
     val label = new Label("label", "Elija una operación a realizar")
     form.add(label)
     
   }
   
}
