package ddsGrupo2.festival.ui

import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.CompoundPropertyModel
import ddsGrupo2.festival.model._
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.PropertyModel

class PanelBandaContiene(val id: String) extends PanelBuscador(id) {

  val form: Form[Buscador[Banda]] = new Form("buscador", createModel)
  val filtroContiene = new FiltroBandaContiene
  
  form.add(new TextField("contiene", new PropertyModel[String](this.filtroContiene, "contiene")))
  
  def createModel: CompoundPropertyModel[Buscador[Banda]] = {
    new CompoundPropertyModel(
    new Buscador(new GeneradorBandasPorFestival(FestivalesHome.getFestival), filtroContiene))}
 
  def buscador = form.getModelObject()
}