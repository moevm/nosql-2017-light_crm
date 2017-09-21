package alpha

import com.arangodb.ArangoCollection
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException
import com.arangodb.entity.BaseDocument

class SimpleCRUD{
    companion object {
        @JvmStatic fun main(args : Array<String>){
            var arangoDB = ArangoDB.Builder().user("foo").password("bar").build();

            val dbName = "testDB";
            try {
                val collectionName = "testCollection";
                var arangoObject = BaseDocument();
                arangoObject.setKey("testKey");
                arangoObject.addAttribute("a", 123);
                arangoObject.addAttribute("b", 345);

                if(!arangoDB.accessibleDatabases.contains(dbName)) {

                    arangoDB.createDatabase(dbName);
                    println("Database " + dbName + " created");
                    var arangoCollection = arangoDB.db(dbName).createCollection(collectionName);
                    println("Collection " + collectionName + " created");
                    arangoDB.db(dbName).collection(collectionName).insertDocument(arangoObject);
                }
                if(!arangoDB.db(dbName).collection(collectionName).documentExists("testKey")) arangoDB.db(dbName).collection(collectionName).insertDocument(arangoObject);

                var objectToRead = arangoDB.db(dbName).collection(collectionName).getDocument("testKey", BaseDocument::class.java);
                println("Key: " + objectToRead.key);
                println("a: " + objectToRead.getAttribute("a"));
                println("b: " + objectToRead.getAttribute("b"));

                arangoObject.addAttribute("c", 567);
                arangoDB.db(dbName).collection(collectionName).updateDocument("testKey", arangoObject);

                objectToRead = arangoDB.db(dbName).collection(collectionName).getDocument("testKey", BaseDocument::class.java);
                println("Key: " + objectToRead.key);
                println("a: " + objectToRead.getAttribute("a"));
                println("b: " + objectToRead.getAttribute("b"));
                println("c: " + objectToRead.getAttribute("c"));

                arangoDB.db(dbName).collection(collectionName).deleteDocument("testKey");

            } catch (e : ArangoDBException){
                println("Failed to process database " + dbName + "\n" + e.message);
            }


        }
    }
}