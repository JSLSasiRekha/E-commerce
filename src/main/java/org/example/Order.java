package org.example;


import org.bson.Document;

import static org.example.MongoDBConnection.findDocuments;
import static org.example.MongoDBConnection.insertDocument;

public class Order {
    public static void AddToCart(Document filter){
        insertDocument("Order",filter);

    }
    public static void DisplayCart(Document filter){
        findDocuments("Order",filter);
    }
}