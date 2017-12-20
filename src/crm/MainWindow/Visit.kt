package crm.MainWindow

import crm.ArangoCRUD
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class Visit(key: String = "", user_id: String = "", browser: String = "", visit: String = "") {
    val keyProperty = SimpleStringProperty(key)
    var key by keyProperty
    val user_idProperty = SimpleStringProperty(user_id)
    var user_id by user_idProperty
    val browserProperty = SimpleStringProperty(browser)
    var browser by browserProperty
    val visitProperty = SimpleStringProperty(visit)
    var visit by visitProperty

}

class VisitModel : ItemViewModel<Visit>() {
    val key = bind { item?.keyProperty }
    val user_id = bind { item?.user_idProperty }
    val browser = bind { item?.browserProperty }
    val visit = bind { item?.visitProperty }
}