package crm.MainWindow

import crm.ArangoCRUD
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.layout.GridPane
import tornadofx.*
import java.awt.ScrollPane
import tornadofx.ListMenu



class MainForm : View("CRM") {
    val model : SiteModel by inject()

    override val root = form {
        tabpane {
            /*gridpaneConstraints {
                vhGrow = Priority.ALWAYS
            }*/
            tab("Main page", GridPane()) {
                hbox {
                    vbox(10.0) {
                        imageview(model.sitelogo) {
                            scaleX = .70
                            scaleY = .70
                        }
                    }
                    vbox(10.0) {
                        label("About site") {
                            style = "-fx-font: 50px Tahoma;"
                        }
                        hbox(10.0) {
                            label("Site name") {
                                prefWidth = 150.0
                                style = "-fx-font: 20px Tahoma;"
                            }
                            textfield(model.sitename) {
                                prefWidth = 400.0
                            }
                        }
                        hbox(10.0) {
                            label("Site address") {
                                prefWidth = 150.0
                                style = "-fx-font: 20px Tahoma;"
                            }
                            textfield(model.siteadress) {
                                prefWidth = 400.0
                            }
                        }
                        hbox(10.0) {
                            label("IP address") {
                                prefWidth = 150.0
                                style = "-fx-font: 20px Tahoma;"
                            }
                            textfield(model.ipaddress) {
                                prefWidth = 400.0
                            }
                        }
                        hbox(10.0) {
                            label("Hosting") {
                                prefWidth = 150.0
                                style = "-fx-font: 20px Tahoma;"
                            }
                            textfield(model.hosting) {
                                prefWidth = 400.0
                            }
                        }
                        hbox(10.0) {
                            label("Site description") {
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
            }
            tab("User stats", ScrollPane()) {
                label("User stats go here!")
                hbox(10.0) {
                    label("Hosting"){
                        prefWidth = 150.0
                        style = "-fx-font: 20px Tahoma;"
                    }
                    textfield(model.hosting) {
                        prefWidth = 400.0
                    }
                }
            }

            tab("Visitor stats", GridPane()) {
                label("Visitor stats go here!")
            }
            tab("Search", GridPane()) {
                label("Search here!")
            }
            tab("View", GridPane()) {
                vbox(10.0) {
                    /*var userTable = tableview<String> {
                        //items = FXCollections.observableArrayList("Key","Username","Sex","Birthday","Registration Date")
                    }
                    userTable.hide()*/
                    var userField =textarea {
                        prefWidth = 400.0
                    }
                    userField.hide()
                    var visitField =textarea {
                        prefWidth = 400.0
                    }
                    visitField.hide()
                    hbox(10.0) {
                        val tables = FXCollections.observableArrayList("Users", "Visitors")

                        val selectedTable = SimpleStringProperty()

                        combobox(selectedTable, tables)
                        button("Show") {
                            action {
                                println(selectedTable.value)
                                val db : ArangoCRUD = crm.ArangoCRUD()
                                if (selectedTable.value == "Users") {
                                    val z = db.GetAllUserDocs()

                                    //userTable.show()
                                    userField.show()
                                    visitField.hide()
                                    userField.clear()
                                    z.forEach{user ->userField.setText(userField.getText() + "\n"+ user.key + " "+user.username+ " "+
                                            user.sex+ " "+user.birthday+ " "+user.registration_date)}
                                    z.clear()

                                }
                                if (selectedTable.value == "Visitors") {
                                    val z = db.GetAllVisitDocs()
                                    userField.hide()
                                    visitField.show()
                                    visitField.clear()
                                    z.forEach{visit ->visitField.setText(visitField.getText() + "\n"+ visit.key + " "+visit.user_id+ " "+
                                            visit.browser+ " "+visit.visit)}
                                    z.clear()
                                }
                            }
                        }

                    }


                }
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