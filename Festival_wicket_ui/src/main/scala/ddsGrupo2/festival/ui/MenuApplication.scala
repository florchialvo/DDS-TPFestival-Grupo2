package ddsGrupo2.festival.ui

import org.apache.wicket._
import org.apache.wicket.protocol.http._
import org.apache.wicket.markup.html._
import org.apache.wicket.markup.html.form._
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.markup.html.basic.Label
import ddsGrupo2.festival.model.FiltroBandaContiene
import org.apache.wicket.model.CompoundPropertyModel
import ddsGrupo2.festival.model.FestivalesHome
import org.apache.wicket.model.ComponentPropertyModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import collection.JavaConversions._
import uqbar.arena.persistence.Configuration
//
import ddsGrupo2.festival.model._
import scala.collection.mutable.Set
import scala.collection.mutable.Buffer

class MenuApplication extends WebApplication {
    Configuration.configure()

  //Categorias
  Categoria.crearCategoria('categoria1, 0)
  Categoria.crearCategoria('categoria2, 50)
  Categoria.crearCategoria('categoria3, 100)
  Categoria.crearCategoria('categoria4, 200)

  //Bandas
  val ledZeppelin = new Banda(Categoria('categoria4), "Led Zeppelin")
  val ironMaiden = new Banda(Categoria('categoria4), "Iron Maiden")
  val sodaStereo = new Banda(Categoria('categoria3), "Soda Stereo")
  val rollingStones = new Banda(Categoria('categoria4), "Rollings Stones")
  val bonJovi = new Banda(Categoria('categoria4), "Bon Jovi")
  val elIndio = new Banda(Categoria('categoria2), "Indio Solari")

  //Festivales
  val valoresBaseQuilmes = Set(
    Sector("A", Fila(500, 10), Fila(400, 10), Fila(300, 10)),
    Sector("B", Fila(500, 10), Fila(500, 10), Fila(500, 10)),
    Sector("C", Fila(500, 100)))

  val valoresBaseRock = Set(
    Sector("A", Fila(100, 15), Fila(100, 15), Fila(100, 15)),
    Sector("B", Fila(500, 10), Fila(500, 10), Fila(500, 10)))

  val quilmesRock = new Festival(valoresBaseQuilmes, new Fecha(10, 12, 2013), "Quilmes Rock")
  val festivalRock = new Festival(valoresBaseRock, new Fecha(10, 12, 2013), "Festival Rock")

  //Noches
  val noche1 = new Noche(Set(ledZeppelin, sodaStereo), new Fecha(18, 10, 2014))
  val noche2 = new Noche(Set(bonJovi), new Fecha(19, 10, 2014))
  val noche3 = new Noche(Set(ledZeppelin, ironMaiden), new Fecha(20, 10, 2014))
  val noche4 = new Noche(Set(ledZeppelin, sodaStereo), new Fecha(21, 10, 2014))

  val nocheq1 = new Noche(Set(ironMaiden), new Fecha(18, 10, 2014))
  val nocheq2 = new Noche(Set(elIndio), new Fecha(19, 10, 2014))

  festivalRock.agregarNoches(List(noche1, noche2, noche3, noche4))
  quilmesRock.agregarNoches(List(nocheq1, nocheq2))

  //Descuentos
  festivalRock.agregarDescuentos(Dama(), Jubilado(), Menor(), Mayor())
  quilmesRock.agregarDescuentos(Dama(), Menor(), Mayor())
  Console.println(quilmesRock.descuentosValidos)

  FestivalesHome.agregarSiNoExiste(festivalRock)
  FestivalesHome.agregarSiNoExiste(quilmesRock)
  def getHomePage = classOf[MenuPage]
}
