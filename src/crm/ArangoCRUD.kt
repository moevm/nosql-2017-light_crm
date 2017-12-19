package crm


import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException
import com.arangodb.entity.BaseDocument
import com.arangodb.entity.CollectionEntity
import com.arangodb.util.MapBuilder
import crm.MainWindow.User
import crm.MainWindow.Visit


class ArangoCRUD {
    /*constructor(user: String, password: String) {
        //Сделать подключение к базе по произвольному логин/паролю из gui
    }*/


    //Временная функция для наполения базы и тестирования CRUD
    /*@JvmStatic fun main(args: Array<String>) {
        CreateDB();
        //Удалим юзеров
        deleteDoc(122001, "User")
        deleteDoc(121998, "User")
        deleteDoc(121994, "User")
        deleteDoc(122004, "User")

        //Добавим юзеров
        UpdateDB("kek", "male", "1999-10-12", "2017-11-30");
        UpdateDB("cheburek", "female", "2000-01-12", "2017-10-30");
        UpdateDB("lol", "male", "1996-01-01", "2017-11-29");
        UpdateDB("lul", "female", "1997-06-12", "2017-11-30");

        //Удалим инфу о сайте
        deleteDoc(122007, "Site")

        //Добавим инфу о сайте
        UpdateDB("Сайт о чебуреках", "cheburekoff.net", "ucoz.ru", "96.98.181.0", "Вкусные чебуреки только тут!", "https://i.ytimg.com/vi/3lqlc-ooJpE/hqdefault.jpg");

        //Удалим посетителей
        deleteDoc(122725, "Visitor")
        deleteDoc(122735, "Visitor")
        deleteDoc(122732, "Visitor")
        deleteDoc(122729, "Visitor")

        //Добавим посетителей
        UpdateDB(121998, "2017-04-12 13:22", "Google Chrome");
        UpdateDB(122001, "2017-04-12 14:15", "Microsoft Edge");
        UpdateDB(121998, "2017-04-12 15:54", "Google Chrome");
        UpdateDB(122004, "2017-04-12 15:56", "Google Chrome");

        return
    }*/

    val arangoDB = ArangoDB.Builder().user("root").password("root").build();
    //lateinit var db : ArangoDatabase;
    val dbName = "crmDB";
    val collectionNameUsers = "Users";
    val collectionNameVisitors = "Visitors";
    val collectionNameSite = "Site";
    lateinit var arangoCollectionUsers : CollectionEntity;
    lateinit var arangoCollectionVisitors : CollectionEntity;
    lateinit var arangoCollectionSite : CollectionEntity;

    fun CreateDB() {
        try {

            if (!arangoDB.accessibleDatabases.contains(dbName)) {
                arangoDB.createDatabase(dbName);
                //db = arangoDB.db(dbName)
                arangoCollectionUsers = arangoDB.db(dbName).createCollection(collectionNameUsers);
                arangoCollectionVisitors = arangoDB.db(dbName).createCollection(collectionNameVisitors);
                arangoCollectionSite = arangoDB.db(dbName).createCollection(collectionNameSite);
            }
            else
                println("DB is already exists!");

        } catch (e: ArangoDBException) {
            println("Failed to create database " + dbName + "\n" + e.message);
        }
    }
    //Users Collection
    @JvmOverloads fun UpdateDB(username: String, sex: String, birthday: String, registration_date: String) {
        try {
            var arangoObject = BaseDocument();
            arangoObject.addAttribute("username", username);
            arangoObject.addAttribute("sex", sex);
            arangoObject.addAttribute("birthday", birthday);
            arangoObject.addAttribute("registration_date", registration_date);

            if (arangoDB.accessibleDatabases.contains(dbName)) {
                arangoDB.db(dbName).collection(collectionNameUsers).insertDocument(arangoObject);
            }
            else
                println("Can't find DB! Adding a document failed.");

        } catch (e: ArangoDBException) {
            println("Failed to update database " + dbName + "\n" + e.message);
        }
    }
    //Visitores Collection
    @JvmOverloads fun UpdateDB(user_id: Int, visit: String, browser: String) {
        try {
            var arangoObject = BaseDocument();
            arangoObject.addAttribute("user_id", user_id);
            arangoObject.addAttribute("visit", visit);
            arangoObject.addAttribute("browser", browser);

            if (arangoDB.accessibleDatabases.contains(dbName)) {
                arangoDB.db(dbName).collection(collectionNameVisitors).insertDocument(arangoObject);
            }
            else
                println("Can't find DB! Adding a document failed.");

        } catch (e: ArangoDBException) {
            println("Failed to update database " + dbName + "\n" + e.message);
        }
    }
    //Site Collection
    @JvmOverloads fun UpdateDB(site_name: String, site_address: String, hosting: String, ip_address: String, site_descr: String, site_logo: String) {
        try {
            var arangoObject = BaseDocument();
            arangoObject.addAttribute("site_name", site_name);
            arangoObject.addAttribute("site_address", site_address);
            arangoObject.addAttribute("hosting", hosting);
            arangoObject.addAttribute("ip_address", ip_address);
            arangoObject.addAttribute("site_descr", site_descr);
            arangoObject.addAttribute("site_logo", site_logo);

            if (arangoDB.accessibleDatabases.contains(dbName)) {
                arangoDB.db(dbName).collection(collectionNameSite).insertDocument(arangoObject);
            }
            else
                println("Can't find DB! Adding a document failed.");

        } catch (e: ArangoDBException) {
            println("Failed to update database " + dbName + "\n" + e.message);
        }
    }

    fun DeleteDoc(doc_id: Int, type: String) {
        try {
            if (arangoDB.accessibleDatabases.contains(dbName)) {
                if (type == "User")
                    arangoDB.db(dbName).collection(collectionNameUsers).deleteDocument(doc_id.toString());
                if (type == "Visitor")
                    arangoDB.db(dbName).collection(collectionNameVisitors).deleteDocument(doc_id.toString());
                if (type == "Site")
                    arangoDB.db(dbName).collection(collectionNameSite).deleteDocument(doc_id.toString());
            }
            else
                println("Can't find DB! Deleting a document failed.");

        } catch (e: ArangoDBException) {
            println("Failed to update database " + dbName + "\n" + e.message);
        }
    }
    fun ReadDoc(key: String, type: String) : BaseDocument {
        var objectToRead : BaseDocument
        if (arangoDB.accessibleDatabases.contains(dbName)) {
            if (type == "User") {
                objectToRead = arangoDB.db(dbName).collection(collectionNameUsers).getDocument(key, BaseDocument::class.java)
                /*println("username: " + objectToRead.getAttribute("username"));
                println("sex: " + objectToRead.getAttribute("sex"));
                println("registration_date: " + objectToRead.getAttribute("registration_date"));
                println("birthday: " + objectToRead.getAttribute("birthday"));*/
                return objectToRead;
            }
            if (type == "Visitor") {
                objectToRead = arangoDB.db(dbName).collection(collectionNameVisitors).getDocument(key, BaseDocument::class.java);
                /*println("user_id: " + objectToRead.getAttribute("user_id"));
                println("visit: " + objectToRead.getAttribute("visit"));
                println("browser: " + objectToRead.getAttribute("browser"));*/
                return objectToRead;
            }
            if (type == "Site") {
                objectToRead = arangoDB.db(dbName).collection(collectionNameSite).getDocument(key, BaseDocument::class.java);
                /*println("site_name: " + objectToRead.getAttribute("site_name"));
                println("site_descr: " + objectToRead.getAttribute("site_descr"));
                println("site_address: " + objectToRead.getAttribute("site_address"));
                println("site_logo: " + objectToRead.getAttribute("site_logo"));
                println("hosting: " + objectToRead.getAttribute("hosting"));
                println("ip_address: " + objectToRead.getAttribute("ip_address"));*/
                return objectToRead;
            }
        }
        return arangoDB.db(dbName).collection(collectionNameSite).getDocument(key, BaseDocument::class.java);
    }

    fun GetAllUserDocs(): ArrayList<User> {
        val users = ArrayList<User>()
        if (arangoDB.accessibleDatabases.contains(dbName)) {
            //if (type == "Users") {
                val query = "FOR user IN Users RETURN user"
                val bindVars = MapBuilder().get()
                val cursor = arangoDB.db(dbName).query(query, bindVars, null, BaseDocument::class.java)

                cursor.forEachRemaining {aDocument ->
                    users += User(aDocument.key, aDocument.getAttribute("username").toString(), aDocument.getAttribute("sex").toString(),
                            aDocument.getAttribute("birthday").toString(),aDocument.getAttribute("registration_date").toString()) }


            //}
            /*if (type == "Visitors") {
                val query = "FOR visitor IN Visitors RETURN visitor"
                val bindVars = MapBuilder().get()
                val cursor = arangoDB.db(dbName).query(query, bindVars, null, BaseDocument::class.java)
                cursor.forEachRemaining { aDocument -> println(aDocument.key + " " + aDocument.getAttribute("user_id")
                        + " " + aDocument.getAttribute("browser") + " " + aDocument.getAttribute("visit")) }
            }*/
        }
        return users
    }

    fun GetAllVisitDocs(): ArrayList<Visit> {
        val visits = ArrayList<Visit>()
        if (arangoDB.accessibleDatabases.contains(dbName)) {
            val query = "FOR visit IN Visitors RETURN visit"
            val bindVars = MapBuilder().get()
            val cursor = arangoDB.db(dbName).query(query, bindVars, null, BaseDocument::class.java)

            cursor.forEachRemaining {aDocument ->
                visits += Visit(aDocument.key, aDocument.getAttribute("user_id").toString(), aDocument.getAttribute("browser").toString(),
                        aDocument.getAttribute("visit").toString()) }
        }
        return visits
    }
}

/*val query = "FOR user IN Users FILTER user.username == @name RETURN user"
                val bindVars = MapBuilder().put("name", "lol").get()
                val cursor = arangoDB.db(dbName).query(query, bindVars, null, BaseDocument::class.java)
                cursor.forEachRemaining { aDocument -> println(aDocument.key + " " + aDocument.getAttribute("username")
                        + " " + aDocument.getAttribute("sex") + " " + aDocument.getAttribute("birthday")+ " "
                        + aDocument.getAttribute("registration_date")) }*/