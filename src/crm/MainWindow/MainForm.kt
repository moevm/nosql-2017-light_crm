package crm.MainWindow

import tornadofx.*

class MainForm : View("CRM") {
    val model : SiteModel by inject()

    /*val db = crm.ArangoCRUD.CreateDB()
    val db1 = crm.ArangoCRUD.ReadDoc("124957", "Site")*/
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
                        //Заглушка
                        textfield("Онлайн чебуреки")

                        //Подключить БД к модели Site
                        //textfield(model.sitename)
                    }

                    field("Site address") {
                        //Заглушка
                        textfield("Cheburekoff.net")

                        //Подключить БД к модели Site
                        //textfield(model.siteadress)
                    }

                    field("IP address") {
                        //Заглушка
                        textfield("192.121.123.12")

                        //Подключить БД к модели Site
                        //textfield(model.ipaddress)
                    }

                    field("Hosting") {
                        //Заглушка
                        textfield("Ucoz.ru")

                        //Подключить БД к модели Site
                        //textfield(model.hosting)
                    }

                    field("Site description") {
                        //Заглушка
                        textfield("Купить чебурек онлайн")

                        //Подключить БД к модели Site
                        //textfield(model.description)
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