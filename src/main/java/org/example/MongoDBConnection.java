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
                System.out.println("---------------------------------------------------------------------------------------------------------------------");
                System.out.println("Product Name\t\t\t\t\t\tProduct Color\t\t\tProduct Size\t\tProduct Price");
                System.out.println("---------------------------------------------------------------------------------------------------------------------");
                // Print each document
                while (cursor.hasNext()) {
                    Document document = cursor.next();
                    String name = document.getString("name");
                    String color = document.getString("color");
                    String size = document.getString("size");
                    String price = String.valueOf(document.getDouble("price"));

                    String formattedRow = String.format("%-40s %-20s %-20s %-10s", name, color, size, price);
                    System.out.println(formattedRow);
                }
            }
                else if(tableName.equalsIgnoreCase("food")){
                System.out.println("---------------------------------------------------------------------------------------------------------------------");
                System.out.println("Item Name\t\t\t\t\t\t\tItem Price");
                System.out.println("-----------------------------------------------------------------------------------------------------------------------");
                   // Print each document
                while (cursor.hasNext()) {
                    Document document = cursor.next();
                    String itemName = document.getString("name");
                    String itemPrice = String.valueOf(document.getDouble("price"));

                    String formattedRow = String.format("%-40s %-10s", itemName, itemPrice);
                    System.out.println(formattedRow);
                }
                }
            else if(tableName.equalsIgnoreCase("accessories")){
                System.out.println("---------------------------------------------------------------------------------------------------------------------");
                System.out.println("Product Name\t\t\t\t\t\tProduct Brand\t\t\tProduct Color\t\tProduct Price");
                System.out.println("------------------------------------------------------------------------------------------------------------------------");
// Print each document
                while (cursor.hasNext()) {
                    Document document = cursor.next();
                    String productName = document.getString("name");
                    String productBrand = document.getString("brand");
                    String productColor = document.getString("color");
                    String productPrice = String.valueOf(document.getDouble("price"));

                    String formattedRow = String.format("%-40s %-20s %-20s %-10s", productName, productBrand, productColor, productPrice);
                    System.out.println(formattedRow);
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

        MongoCollection<Document> collection = GetCollection("Clothes");

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
//        displayTable("Clothes");
//        displayTable("Accessories");
//        displayTable("Food");
        List<Document> clothesItems = new ArrayList<>();
        clothesItems.add(new Document("name", "t-shirt").append("color", "red").append("size", "M").append("price", "19.99").append("quantity", 10));
        clothesItems.add(new Document("name", "jeans").append("color", "blue").append("size", "30").append("price", "49.99").append("quantity", 5));
        clothesItems.add(new Document("name", "dress").append("color", "black").append("size", "S").append("price", "39.99").append("quantity", 8));
        clothesItems.add(new Document("name", "sweater").append("color", "gray").append("size", "L").append("price", "29.99").append("quantity", 15));
        clothesItems.add(new Document("name", "skirt").append("color", "pink").append("size", "XS").append("price", "24.99").append("quantity", 3));
        clothesItems.add(new Document("name", "shirt").append("color", "white").append("size", "XL").append("price", "22.99").append("quantity", 20));
        clothesItems.add(new Document("name", "shorts").append("color", "green").append("size", "32").append("price", "17.99").append("quantity", 12));
        clothesItems.add(new Document("name", "jacket").append("color", "brown").append("size", "M").append("price", "59.99").append("quantity", 6));
        clothesItems.add(new Document("name", "sweatpants").append("color", "navy").append("size", "L").append("price", "34.99").append("quantity", 9));
        clothesItems.add(new Document("name", "blouse").append("color", "yellow").append("size", "S").append("price", "27.99").append("quantity", 18));

        collection.insertMany(clothesItems);
    }
}
