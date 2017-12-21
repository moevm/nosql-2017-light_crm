package crm.MainWindow

import crm.ArangoCRUD
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.layout.GridPane
import tornadofx.*
import java.awt.ScrollPane

class MainForm : View("CRM") {
    val model : SiteModel by inject()
    val db : ArangoCRUD = crm.ArangoCRUD()

    override val root = form {
        tabpane {
            tab("Main page", GridPane()) {
                hbox {
                    style {
                        padding = box(20.px)
                    }
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
                                style = "-fx-font: 18px Tahoma;"
                            }
                            textfield(model.sitename) {
                                prefWidth = 400.0
                            }
                        }
                        hbox(10.0) {
                            label("Site address") {
                                prefWidth = 150.0
                                style = "-fx-font: 18px Tahoma;"
                            }
                            textfield(model.siteadress) {
                                prefWidth = 400.0
                            }
                        }
                        hbox(10.0) {
                            label("IP address") {
                                prefWidth = 150.0
                                style = "-fx-font: 18px Tahoma;"
                            }
                            textfield(model.ipaddress) {
                                prefWidth = 400.0
                            }
                        }
                        hbox(10.0) {
                            label("Hosting") {
                                prefWidth = 150.0
                                style = "-fx-font: 18px Tahoma;"
                            }
                            textfield(model.hosting) {
                                prefWidth = 400.0
                            }
                        }
                        hbox(10.0) {
                            label("Site description") {
                                prefWidth = 150.0
                                style = "-fx-font: 18px Tahoma;"
                            }
                            textarea(model.description) {
                                prefWidth = 400.0
                                prefHeight = 200.0
                            }
                        }

                    }
                }
            }
            tab("User stats", ScrollPane()) {
                vbox(10.0) {
                    style {
                        padding = box(20.px)
                    }
                    val z = db.GetBrowserDocs()
                    piechart("Browser") {
                        for (item in z) {
                            data(item.key, item.value)
                        }
                    }
                }
            }

            tab("Visitor stats", GridPane()) {
                vbox(10.0) {
                    style {
                        padding = box(20.px)
                    }
                    val z = db.GetSexDocs()
                    piechart("Sex") {
                        for (item in z) {
                            data(item.key, item.value)
                        }
                    }
                }
            }
            tab("Search", GridPane()) {
                vbox(10.0) {
                    style {
                        padding = box(20.px)
                    }
                    var uSearch =vbox{}
                    var vSearch =vbox{}

                    hbox(10.0) {
                        val tables = FXCollections.observableArrayList("Users", "Visitors")
                        val selectedTable = SimpleStringProperty()

                        combobox(selectedTable, tables) {
                            prefWidth = 200.0
                        }
                        button("Select") {
                            action {
                                val db: ArangoCRUD = crm.ArangoCRUD()
                                if (selectedTable.value == "Users") {
                                    uSearch.show()
                                    vSearch.hide()
                                }
                                if (selectedTable.value == "Visitors") {
                                    vSearch.show()
                                    uSearch.hide()
                                }
                            }
                        }
                    }
                    var userFieldSearch =textarea {}
                    var visitFieldSearch =textarea {}
                    userFieldSearch.hide()
                    visitFieldSearch.hide()
                    uSearch = vbox(10.0) {
                        hbox(10.0){
                            label("Username"){
                                prefWidth = 200.0
                                style = "-fx-font: 18px Tahoma;"
                            }
                            label("Sex"){
                                prefWidth = 200.0
                                style = "-fx-font: 18px Tahoma;"
                            }
                            label("Birthday"){
                                prefWidth = 200.0
                                style = "-fx-font: 18px Tahoma;"
                            }
                            label("Registration Date"){
                                prefWidth = 200.0
                                style = "-fx-font: 18px Tahoma;"
                            }
                        }
                        hbox(10.0){
                            val username = textfield() {
                                prefWidth = 200.0
                            }
                            val sex = textfield() {
                                prefWidth = 200.0
                            }
                            val birthday = textfield() {
                                prefWidth = 200.0
                            }
                            val registration_date = textfield() {
                                prefWidth = 200.0
                            }
                            button("Search") {
                                action {
                                    userFieldSearch.clear()
                                    val z = db.SearchUserDocs(username.text.toString(), sex.text.toString(), birthday.text.toString(), registration_date.text.toString())
                                    z.forEach{user ->userFieldSearch.setText(userFieldSearch.getText() + "\n"+ user.key + " "+user.username+ " "+
                                            user.sex+ " "+user.birthday+ " "+user.registration_date)}
                                    userFieldSearch.show()
                                    visitFieldSearch.hide()
                                }
                            }
                        }
                    }
                    uSearch.hide()
                    vSearch = vbox(10.0) {
                        hbox(10.0){
                            label("User_id"){
                                prefWidth = 200.0
                                style = "-fx-font: 18px Tahoma;"
                            }
                            label("Visit"){
                                prefWidth = 200.0
                                style = "-fx-font: 18px Tahoma;"
                            }
                            label("Browser"){
                                prefWidth = 200.0
                                style = "-fx-font: 18px Tahoma;"
                            }
                        }
                        hbox(10.0){
                            val user_id = textfield() {
                                //Заменить на username
                                prefWidth = 200.0
                            }
                            val visit = textfield() {
                                prefWidth = 200.0
                            }
                            val browser = textfield() {
                                prefWidth = 200.0
                            }
                            button("Search") {
                                action {
                                    visitFieldSearch.clear()
                                    val z = db.SearchVisitDocs(user_id.text.toString(), browser.text.toString(), visit.text.toString())
                                    z.forEach{visit ->visitFieldSearch.setText(visitFieldSearch.getText() + "\n"+ visit.key + " "+visit.user_id+ " "+
                                            visit.browser+ " "+visit.visit)}
                                    visitFieldSearch.show()
                                    userFieldSearch.hide()
                                }
                            }
                        }
                    }
                    vSearch.hide()
                    userFieldSearch =textarea {
                        prefWidth = 400.0
                    }
                    userFieldSearch.hide()
                    visitFieldSearch =textarea {
                        prefWidth = 400.0
                    }
                    visitFieldSearch.hide()
                }
            }
            tab("View", GridPane()) {
                vbox(10.0) {
                    style {
                        padding = box(20.px)
                    }
                    /*var userTable = tableview<String> {
                        //items = FXCollections.observableArrayList("Key","Username","Sex","Birthday","Registration Date")
                    }
                    userTable.hide()*/
                    var userField =textarea {}
                    var visitField =textarea {}
                    userField.hide()
                    visitField.hide()
                    hbox(10.0) {
                        val tables = FXCollections.observableArrayList("Users", "Visitors")
                        val selectedTable = SimpleStringProperty()

                        combobox(selectedTable, tables){
                            prefWidth = 200.0
                        }
                        button("Show") {
                            action {
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
                    userField =textarea {
                        prefWidth = 400.0
                    }
                    userField.hide()
                    visitField =textarea {
                        prefWidth = 400.0
                    }
                    visitField.hide()
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