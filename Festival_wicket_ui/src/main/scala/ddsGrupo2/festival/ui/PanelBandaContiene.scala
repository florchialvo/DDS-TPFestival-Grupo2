package ddsGrupo2.festival.ui

import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.CompoundPropertyModel
import ddsGrupo2.festival.model._
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.markup.html.form.DropDownChoice
import org.apache.wicket.model.ComponentPropertyModel
import scala.collection.JavaConverters._
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.markup.html.basic.Label

class PanelBandaContiene extends PanelBuscador {

  val filtroContiene = new FiltroBandaContiene
  val form: Form[Buscador[Banda]] = new Form("buscador", createModel)
  form.add(new TextField("contiene", new PropertyModel[String](this.filtroContiene, "bandaContiene")))
  add(form)

  def createModel: CompoundPropertyModel[Buscador[Banda]] = {
    new CompoundPropertyModel(
      new Buscador(new GeneradorBandasPorFestival(FestivalesHome.getFestival), filtroContiene))
  }

  def buscador = form.getModelObject()
}


