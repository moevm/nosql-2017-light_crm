package crm.MainWindow

import crm.ArangoCRUD
import javafx.beans.property.Property
import javafx.beans.property.StringProperty
import tornadofx.*

class Site {
    val db :ArangoCRUD = crm.ArangoCRUD()
    db.ReadDoc("124957", "Site")

    fun setData(){
        sitename = db.getAttribute("site_name").toString()
        siteadress = db.getAttribute("site_address").toString()
        ipaddress = db.getAttribute("ip_address").toString()
        hosting = db.getAttribute("hosting").toString()
        description = db.getAttribute("site_descr").toString()
    }

    var sitename by property<String>()
    fun sitenameProperty() = getProperty(Site::sitename)

    var siteadress by property<String>()
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
    val siteadress: Property<String> = bind { item?.siteadressProperty() }
    val ipaddress: StringProperty = bind { item?.ipaddressProperty() }
    val hosting:StringProperty = bind { item?.hostingProperty() }
    val description:StringProperty = bind { item?.descriptionProperty() }
}