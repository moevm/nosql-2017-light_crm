package crm.MainWindow

import javafx.beans.property.Property
import javafx.beans.property.StringProperty
import tornadofx.*
import java.time.LocalDate

class Site {
    var sitename by property<String>()
    fun sitenameProperty() = getProperty(Site::sitename)

    var siteadress by property<LocalDate>()
    fun siteadressProperty() = getProperty(Site::siteadress)

    var ipaddress by property<String>()
    fun ipaddressProperty() = getProperty(Site::ipaddress)

    var hosting by property<String>()
    fun hostingProperty() = getProperty(Site::hosting)

    var description by property<String>()
    fun descriptionProperty() = getProperty(Site::description)

    override fun toString() = sitename
}

class SiteModel : ItemViewModel<Site>(Site()) {
    val sitename: StringProperty = bind { item?.sitenameProperty() }
    val siteadress: Property<LocalDate> = bind { item?.siteadressProperty() }
    val ipaddress: StringProperty = bind { item?.ipaddressProperty() }
    val hosting:StringProperty = bind { item?.hostingProperty() }
    val description:StringProperty = bind { item?.descriptionProperty() }
}