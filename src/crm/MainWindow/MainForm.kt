package crm.MainWindow

import javafx.scene.layout.GridPane
import tornadofx.*

class MainForm : View("CRM") {
    val model : SiteModel by inject()

    override val root = form {
        tabpane {
            /*gridpaneConstraints {
                vhGrow = Priority.ALWAYS
            }*/
            tab("Main page", GridPane()) {
                vbox(10.0) {
                    label("About site"){
                        style = "-fx-font: 50px Tahoma;"
                    }
                    hbox(10.0) {
                        label("Site name"){
                            prefWidth = 150.0
                            style = "-fx-font: 20px Tahoma;"
                        }
                        textfield(model.sitename) {
                            prefWidth = 400.0
                        }
                    }
                    hbox(10.0) {
                        label("Site address"){
                            prefWidth = 150.0
                            style = "-fx-font: 20px Tahoma;"
                        }
                        textfield(model.siteadress) {
                            prefWidth = 400.0
                        }
                    }
                    hbox(10.0) {
                        label("IP address"){
                            prefWidth = 150.0
                            style = "-fx-font: 20px Tahoma;"
                        }
                        textfield(model.ipaddress) {
                            prefWidth = 400.0
                        }
                    }
                    hbox(10.0) {
                        label("Hosting"){
                            prefWidth = 150.0
                            style = "-fx-font: 20px Tahoma;"
                        }
                        textfield(model.hosting) {
                            prefWidth = 400.0
                        }
                    }
                    hbox(10.0) {
                        label("Site description"){
                            prefWidth = 150.0
                            style = "-fx-font: 20px Tahoma;"
                        }
                        textfield(model.description) {
                            prefWidth = 400.0
                            prefHeight = 200.0
                        }
                    }

                }
            }
            tab("User stats", GridPane()) {
                label("User stats go here!")
            }

            tab("Visitor stats", GridPane()) {
                label("Visitor stats go here!")
            }
            tab("Search", GridPane()) {
                label("Search here!")
            }
        }
        /*hbox(30.0) {
                button("Save") {
                    action {
                        model.commit {
                            val site = model.item
                            print("Saved")

                        }
                    }

                    enableWhen(model.valid)
                }
            }
        }*/
    }

}