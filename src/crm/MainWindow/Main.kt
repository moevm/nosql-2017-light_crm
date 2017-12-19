package crm.MainWindow

import javafx.application.Application
import tornadofx.*

class Main : App(MainForm::class, MainStyles::class)

fun main(args: Array<String>) {
    Application.launch(Main::class.java, *args)
    return
}