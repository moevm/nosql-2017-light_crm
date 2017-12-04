package crm


import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException
import com.arangodb.entity.BaseDocument
import com.arangodb.entity.CollectionEntity

class ArangoCRUD {
    constructor(user: String, password: String) {
        //Сделать подключение к базе по произвольному логин/паролю из gui
    }
    companion object {

        @JvmStatic fun main(args: Array<String>) {
            CreateDB();
        }

        @JvmStatic val arangoDB = ArangoDB.Builder().user("root").password("root").build();
        @JvmStatic val dbName = "crmDB";
        @JvmStatic val collectionNameUsers = "Users";
        @JvmStatic val collectionNameVisitors = "Visitors";
        @JvmStatic val collectionNameSite = "Site";
        lateinit var arangoCollectionUsers : CollectionEntity;
        lateinit var arangoCollectionVisitors : CollectionEntity;
        lateinit var arangoCollectionSite : CollectionEntity;

        @JvmStatic fun CreateDB() {
            try {

                if (!arangoDB.accessibleDatabases.contains(dbName)) {
                    arangoDB.createDatabase(dbName);
                    arangoCollectionUsers = arangoDB.db(dbName).createCollection(collectionNameUsers);
                    arangoCollectionVisitors = arangoDB.db(dbName).createCollection(collectionNameVisitors);
                    arangoCollectionSite = arangoDB.db(dbName).createCollection(collectionNameSite);
                }

            } catch (e: ArangoDBException) {
                println("Failed to create database " + dbName + "\n" + e.message);
            }
        }
        //Users Collection
        @JvmOverloads fun UpdateDB(id: Int, username: String, sex: String, birthday: String, registration_date: String) {
            try {
                var arangoObject = BaseDocument();
                arangoObject.setKey("user");
                arangoObject.addAttribute("id", id);
                arangoObject.addAttribute("username", username);
                arangoObject.addAttribute("sex", sex);
                arangoObject.addAttribute("birthday", birthday);
                arangoObject.addAttribute("registration_date", registration_date);

                if (arangoDB.accessibleDatabases.contains(dbName)) {
                    arangoDB.db(dbName).collection(collectionNameUsers).insertDocument(arangoObject);
                }

            } catch (e: ArangoDBException) {
                println("Failed to update database " + dbName + "\n" + e.message);
            }
        }
        //Visitores Collection
        @JvmOverloads fun UpdateDB(num: Int, user_id: Int, visit: String, browser: String) {
            try {
                var arangoObject = BaseDocument();
                arangoObject.setKey("visitor");
                arangoObject.addAttribute("num", num);
                arangoObject.addAttribute("user_id", user_id);
                arangoObject.addAttribute("visit", visit);
                arangoObject.addAttribute("browser", browser);

                if (arangoDB.accessibleDatabases.contains(dbName)) {
                    arangoDB.db(dbName).collection(collectionNameVisitors).insertDocument(arangoObject);
                }

            } catch (e: ArangoDBException) {
                println("Failed to update database " + dbName + "\n" + e.message);
            }
        }
        //Site Collection
        @JvmOverloads fun UpdateDB(site_name: String, site_address: String, hosting: String, ip_address: String, site_descr: String, site_logo: String) {
            try {
                var arangoObject = BaseDocument();
                arangoObject.setKey("site");
                arangoObject.addAttribute("site_name", site_name);
                arangoObject.addAttribute("site_address", site_address);
                arangoObject.addAttribute("hosting", hosting);
                arangoObject.addAttribute("ip_address", ip_address);
                arangoObject.addAttribute("site_descr", site_descr);
                arangoObject.addAttribute("site_logo", site_logo);

                if (arangoDB.accessibleDatabases.contains(dbName)) {
                    arangoDB.db(dbName).collection(collectionNameSite).insertDocument(arangoObject);
                }

            } catch (e: ArangoDBException) {
                println("Failed to update database " + dbName + "\n" + e.message);
            }
        }
    }
}