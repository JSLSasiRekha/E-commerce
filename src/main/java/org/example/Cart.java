package org.example;


import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.sql.SQLOutput;

import static org.example.MongoDBConnection.*;

public class Cart {
    public static void AddToCart(String email,String name,String TableName){
        Document cartItem = new Document()
                .append("email", email)
                .append("table", TableName)
                .append("itemName", name);


        // Insert the cart item document into the cart collection



        Document filter=new Document("email",email);
        Document filter2= new Document("name",name);
        Document result= findDocuments( TableName,filter2);
        System.out.println(result.toJson());
        if (result!=null){
            int newQuantity=result.getInteger("quantity");
            System.out.println(newQuantity);
            newQuantity=newQuantity-1;
            System.out.println(newQuantity);
            if(newQuantity>0)
            {
                insertDocument("Cart", cartItem);
                updateDocument(TableName,"name",name,"quantity",newQuantity);
            }
            else{
                System.out.println("Product is out of stock");
            }


        }


        // Print each document

    }
    public static void DisplayCart(String email){
        Document filter=new Document("email",email);
       FindIterable<Document> result= findMultipleDocuments("Cart",filter);
       Double TotalPrice=0.00;
        MongoCursor<Document> cursor = result.iterator();

        // Print each document
        while (cursor.hasNext()) {
            Document document = cursor.next();
            Document filter1= new Document("name",document.getString("itemName"));
            String TableName= document.getString("table");
            Document item1=findDocuments(TableName,filter1);
            if (item1!=null){
                System.out.println(item1.toJson());
                TotalPrice+=item1.getDouble("price");
            }


        }
        System.out.println("Total Price: "+TotalPrice);

    }
    public static void main (String[] args) {
        AddToCart("sasirekha@gmail.com","pasta","Food");
    }
}
