package crm.MainWindow

import tornadofx.*

class MainForm : View("CRM") {
    val model : SiteModel by inject()

    override val root = form {
        hbox(30.0) {
            vbox (10.0) {
                button("Main page") {
                    prefWidth = 200.0
                    action {
                        print("Hello")
                    }
                }
                button("User stats") {
                    prefWidth = 200.0
                    action {
                        print("Hello")
                    }
                }
                button("Visitors stats") {
                    prefWidth = 200.0
                    action {
                        print("Hello")
                    }
                }
            }
            vbox(10.0) {
                fieldset("About site") {
                    field("Site name") {
                        textfield(model.sitename) {
                            prefWidth = 400.0
                        }
                    }

                    field("Site address") {
                        textfield(model.siteadress){
                            prefWidth = 400.0
                        }
                    }

                    field("IP address") {
                        textfield(model.ipaddress){
                            prefWidth = 400.0
                        }
                    }

                    field("Hosting") {
                        textfield(model.hosting){
                            prefWidth = 400.0
                        }
                    }

                    field("Site description") {
                        textfield(model.description){
                            prefWidth = 400.0
                        }
                    }
                }

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
        }
    }

}