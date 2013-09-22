package arenaUiPrueba

import org.uqbar.arena.actions.MessageSend
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.Application
import java.awt.Color
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.Selector
import org.uqbar.arena.windows.MainWindow
import org.uqbar.arena.bindings.NotNullObservable
import org.uqbar.arena.bindings.ContextFreeObservableProperty

object CalculadoraRunner extends Application with App {
    def createMainWindow(): Window[_] = new CalculadoraSimpleWindow(this)
    start()
}
