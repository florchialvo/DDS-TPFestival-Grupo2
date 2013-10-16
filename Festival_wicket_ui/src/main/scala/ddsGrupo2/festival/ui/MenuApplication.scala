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

class MenuPage extends WebPage {
  val form = new Form[Nothing]("form")

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

  val buttonCombo = new Button("venderCombo") {
    override def onSubmit() {
      this.setResponsePage(classOf[VenderComboPage])
    }
  }

  val buttonBuscarEntradas = new Button("buscarEntradas") {
    override def onSubmit() {
      this.setResponsePage(classOf[BuscadorEntradasPage])
    }
  }

  val buttonBuscarBandas = new Button("buscarBandas") {
    override def onSubmit() {
      this.setResponsePage(classOf[BuscadorBandasPage])
    }
  }

  addFields(form)
  addActions(form)
  add(form)

  def addActions(form: Form[Nothing]) {
    form.add(buttonVender)
    form.add(buttonBuscarEntradas)
    form.add(buttonBuscarBandas)    
    form.add(buttonAnular)
    form.add(buttonCombo)
  }

  def addFields(form: Form[Nothing]) {
    val label = new Label("label", "Elija una operación a realizar")
    form.add(label)
  }
}

