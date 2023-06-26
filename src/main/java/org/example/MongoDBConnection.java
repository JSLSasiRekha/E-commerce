package org.example;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;


import java.util.ArrayList;
import java.util.List;

public class MongoDBConnection {
    public static MongoCollection<Document> GetCollection(String TableName){
        String uri = "mongodb+srv://jslsasirekha:e-commerce@cluster0.8zhcbz0.mongodb.net/?retryWrites=true&w=majority";

        try  {
            MongoClient mongoClient = MongoClients.create(uri);
            MongoDatabase database = mongoClient.getDatabase("E-commerce");
            MongoCollection<Document> collection = database.getCollection(TableName);
            return collection;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }

    }
    public static void insertDocument(String tableName, Document document) {
        MongoCollection<Document> collection = GetCollection(tableName);

        if (collection != null) {
            try {
                // Insert the document into the collection
                collection.insertOne(document);
                System.out.println("Product Added to cart Successfully.");
            } catch (Exception e) {
                System.out.println("An error occurred while inserting the document: " + e.getMessage());
            }
        } else {
            System.out.println("Failed to retrieve the collection.");
        }
    }
    public static Document findDocuments(String tableName,Document filter) {
        MongoCollection<Document> collection = GetCollection(tableName);

        if (collection != null) {

            // Find documents in the collection based on the provided filter
           Document documents = collection.find(filter).first();
           // System.out.println(documents);
            return documents;
        } else {
            System.out.println("Failed to retrieve the collection.");
            return null;
        }
    }
    public static FindIterable<Document> findMultipleDocuments(String tableName,Document filter) {
        MongoCollection<Document> collection = GetCollection(tableName);

        if (collection != null) {

            // Find documents in the collection based on the provided filter
            FindIterable<Document> documents = collection.find(filter);


            // System.out.println(documents);
            return documents;
        } else {
            System.out.println("Failed to retrieve the collection.");
            return null;
        }
    }
    public static void updateDocument(String tableName, String field, String value, String updateField, Object updateValue) {
        MongoCollection<Document> collection = GetCollection(tableName);

        if (collection != null) {
            // Create the filter using the field and value
            Document filter = new Document(field, value);

            // Create the update document using the updateField and updateValue
            Document updateDoc = new Document("$set", new Document(updateField, updateValue));

            // Update the matching document in the collection
            collection.updateMany(filter, updateDoc);


        } else {
            System.out.println("Failed to retrieve the collection.");
        }
    }
    public static void deleteDocuments(String tableName, String field, String value) {
        MongoCollection<Document> collection = GetCollection(tableName);

        if (collection != null) {
            // Create the filter using the field and value
            Document filter = new Document(field, value);

            // Delete the matching document(s) from the collection
            collection.deleteMany(filter);

            System.out.println("Document(s) deleted successfully.");
        } else {
            System.out.println("Failed to retrieve the collection.");
        }
    }
    public static void displayTable(String tableName) {
        MongoCollection<Document> collection = GetCollection(tableName);

        if (collection != null) {
            // Find all documents in the collection
            MongoCursor<Document> cursor = collection.find().iterator();
            if(tableName.equalsIgnoreCase("clothes")) {
                System.out.println("Product Name          Product Color       Product Size     Product Prize");
                // Print each document
                while (cursor.hasNext()) {
                    Document document = cursor.next();
                    System.out.println(document.getString("name") + "          " + document.getString("color") + "       " + document.getString("size") + "     " + document.getDouble("price"));
                }
            }
                else if(tableName.equalsIgnoreCase("food")){
                    System.out.println("Item Name               Item Prize");
                    // Print each document
                    while (cursor.hasNext()) {
                        Document document = cursor.next();
                        System.out.println(document.getString("name")+"              "+document.getDouble("price"));
                    }
                }
            else if(tableName.equalsIgnoreCase("accessories")){
                System.out.println("Product Name          Product Brand          Product Color          Product Prize");
                // Print each document
                while (cursor.hasNext()) {
                    Document document = cursor.next();
                    System.out.println(document.getString("name")+"          "+document.getString("brand")+"          "+document.getString("color")+"          "+document.getDouble("price"));
                }
            }



            // Close the cursor
            cursor.close();
        } else {
            System.out.println("Failed to retrieve the collection.");
        }
    }
    public static void main(String[] args) {

        // Replace the placeholder with your MongoDB deployment's connection string

        MongoCollection<Document> collection = GetCollection("Accessories");

   //     System.out.println("Accessories collection 'accessories' created successfully.");

        // Insert 10 accessories items into the collection
//        List<Document> accessoriesItems = new ArrayList<>();
//        accessoriesItems.add(new Document("name", "watch").append("brand", "Fossil").append("color", "Silver").append("price", 99.99).append("quantity", 10));
//        accessoriesItems.add(new Document("name", "handbag").append("brand", "Michael Kors").append("color", "Black").append("price", 199.99).append("quantity", 5));
//        accessoriesItems.add(new Document("name", "necklace").append("brand", "Tiffany & Co.").append("material", "Silver").append("price", 249.99).append("quantity", 8));
//        accessoriesItems.add(new Document("name", "sunglasses").append("brand", "Ray-Ban").append("color", "Brown").append("price", 129.99).append("quantity", 15));
//        accessoriesItems.add(new Document("name", "wallet").append("brand", "Louis Vuitton").append("color", "Brown").append("price", 299.99).append("quantity", 3));
//        accessoriesItems.add(new Document("name", "hat").append("brand", "Nike").append("color", "Black").append("price", 24.99).append("quantity", 20));
//        accessoriesItems.add(new Document("name", "belt").append("brand", "Gucci").append("color", "Brown").append("price", 149.99).append("quantity", 12));
//        accessoriesItems.add(new Document("name", "earrings").append("brand", "Pandora").append("material", "Silver").append("price", 79.99).append("quantity", 6));
//        accessoriesItems.add(new Document("name", "scarf").append("brand", "Hermes").append("color", "Blue").append("price", 199.99).append("quantity", 9));
//        accessoriesItems.add(new Document("name", "tie").append("brand", "Armani").append("color", "Red").append("price", 59.99).append("quantity", 18));
//
//        collection.insertMany(accessoriesItems);
//
//        System.out.println("Accessories items inserted successfully.");

//            MongoCollection<Document> collection = GetCollection("Clothes");
////        String tableName = "Accessories";
////
////        // Example filter document
////        Document filter = new Document("color", "Brown");
////
////        findDocuments(tableName,filter);
//        String tableName = "Accessories";
//        String field = "name";
//        String value = "Necklace";
//
//
//        // Example usage of the updateDocument() function
//        String updateField = "price";
//        int updateValue = 150;

//        updateDocument(tableName, field, value, updateField, updateValue);
//        deleteDocuments(tableName, field, value);
//        displayTable(tableName);
    }
}
