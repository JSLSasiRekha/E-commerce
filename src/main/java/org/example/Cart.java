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
                System.out.println("---------------------------------------------------------------");
                if(TableName.equalsIgnoreCase("Accessories"))
                {
                    String itemName = item1.getString("name");
                    String itemBrand = item1.getString("brand");
                    String itemColor = item1.getString("color");
                    String itemPrice = item1.getString("price");

                    System.out.printf("Item Name: %-20s%n", itemName);
                    System.out.printf("Item Brand: %-20s%n", itemBrand);
                    System.out.printf("Item Color: %-20s%n", itemColor);
                    System.out.printf("Item Price: %-20s%n", itemPrice);
                }
              else if(TableName.equalsIgnoreCase("Clothes")){
                    String itemName = item1.getString("name");
                    String itemSize = item1.getString("size");
                    String itemColor = item1.getString("color");
                    String itemPrice = item1.getString("price");

                    System.out.printf("Item Name: %-20s%n", itemName);
                    System.out.printf("Item Size: %-20s%n", itemSize);
                    System.out.printf("Item Color: %-20s%n", itemColor);
                    System.out.printf("Item Price: %-20s%n", itemPrice);
                }
              else{
                    String itemName = item1.getString("name");
                    String itemPrice = item1.getString("price");

                    System.out.printf("Item Name: %-20s%n", itemName);
                    System.out.printf("Item Price: %-20s%n", itemPrice);
                }
                TotalPrice+=Double.parseDouble(item1.getString("price"));
            }


        }
        System.out.println("-------------------------------------------------------");
        System.out.println("Total Price: "+TotalPrice);

    }
    public static void main (String[] args) {
        AddToCart("sasirekha@gmail.com","pasta","Food");
        DisplayCart("sasirekha@gmail.com");
    }
}
