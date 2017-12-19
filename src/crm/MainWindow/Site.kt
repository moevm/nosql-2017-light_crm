package crm.MainWindow

import crm.ArangoCRUD
import javafx.beans.property.Property
import javafx.beans.property.StringProperty
import tornadofx.*

class Site {
    val db :ArangoCRUD = crm.ArangoCRUD()
    val objectToRead = db.ReadDoc("124957", "Site")

    constructor(){
        SetData()
    }

    fun SetData() {
        sitename = objectToRead.getAttribute("site_name").toString()
        siteadress = objectToRead.getAttribute("site_address").toString()
        ipaddress = objectToRead.getAttribute("ip_address").toString()
        hosting = objectToRead.getAttribute("hosting").toString()
        description = objectToRead.getAttribute("site_descr").toString()
        sitelogo = objectToRead.getAttribute("site_logo").toString()
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

    var sitelogo by property<String>()
    fun sitelogoProperty() = getProperty(Site::sitelogo)

    override fun toString() = sitename
}

class SiteModel : ItemViewModel<Site>(Site()) {
    val sitename: StringProperty = bind { item?.sitenameProperty() }
    val siteadress: Property<String> = bind { item?.siteadressProperty() }
    val ipaddress: StringProperty = bind { item?.ipaddressProperty() }
    val hosting:StringProperty = bind { item?.hostingProperty() }
    val description:StringProperty = bind { item?.descriptionProperty() }
    val sitelogo:StringProperty = bind { item?.sitelogoProperty() }
}