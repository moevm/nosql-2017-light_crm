package crm.MainWindow

import crm.ArangoCRUD
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class User(key: String = "", username: String = "", sex: String = "", birthday: String = "", registration_date: String = "" ) {
    val keyProperty = SimpleStringProperty(key)
    var key by keyProperty
    val usernameProperty = SimpleStringProperty(username)
    var username by usernameProperty
    val sexProperty = SimpleStringProperty(sex)
    var sex by sexProperty
    val birthdayProperty = SimpleStringProperty(birthday)
    var birthday by birthdayProperty
    val registration_dateProperty = SimpleStringProperty(registration_date)
    var registration_date by registration_dateProperty

}

class UserModel : ItemViewModel<User>() {
    val key = bind { item?.keyProperty }
    val username = bind { item?.usernameProperty }
    val sex = bind { item?.sexProperty }
    val birthday = bind { item?.birthdayProperty }
    val registration_date = bind { item?.registration_dateProperty }
}