package crm.MainWindow

import tornadofx.*

class MainForm : View("CRM") {
    val model : SiteModel by inject()

    override val root = form {
        hbox {
            vbox {
                button("Main page") {
                    action {
                        print("Hello")
                    }
                }
                button("User stats") {
                    action {
                        print("Hello")
                    }
                }
                button("Visitors stats") {
                    action {
                        print("Hello")
                    }
                }
            }
            vbox {
                fieldset("About site") {
                    field("Site name") {
                        textfield(model.sitename)
                    }

                    field("Site address") {
                        textfield(model.siteadress)
                    }

                    field("IP address") {
                        textfield(model.ipaddress)
                    }

                    field("Hosting") {
                        textfield(model.hosting)
                    }

                    field("Site description") {
                        textfield(model.description)
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