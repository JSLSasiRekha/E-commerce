package org.example;


import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import static org.example.Encryption.encryption;
import static org.example.MongoDBConnection.GetCollection;

public class Authentication {
    public static boolean authenticateUser(String email, String password) {
        MongoCollection<Document> collection = GetCollection("users");

        if (collection != null) {
            // Create the filter using the email and password
            Document filter = new Document("email", email)
                    .append("password", password);

            // Find a document in the collection based on the provided filter
            Document user = collection.find(filter).first();

            if (user != null) {
                // User found, authentication successful
                return true;
            } else {
                // User not found, authentication failed
                return false;
            }
        } else {
            System.out.println("Failed to retrieve the collection.");
            return false;
        }
    }
    public static boolean login(String email, String password) {
        MongoCollection<Document> collection = GetCollection("users");
         String enpassword=encryption(password);
        if (collection != null) {
            // Create the filter using the email and password
            Document filter = new Document("email", email)
                    .append("password", enpassword);

            // Find a document in the collection based on the provided filter
            Document user = collection.find(filter).first();

            if (user != null) {
                // User found, login successful
                return true;
            } else {
                // User not found, login failed
                return false;
            }
        } else {
            System.out.println("Failed to retrieve the collection.");
            return false;
        }
    }

    public static void signup(String name, String email, String password) {
        MongoCollection<Document> collection = GetCollection("users");

        if (collection != null) {
            // Check if the user already exists
            Document existingUser = collection.find(new Document("email", email)).first();

            if (existingUser != null) {
                System.out.println("User with the provided email already exists.");
            } else {
                String enpassword=encryption(password);
                // Create a new user document
                Document newUser = new Document("name", name)
                        .append("email", email)
                        .append("password", enpassword);

                // Insert the new user document into the collection
                collection.insertOne(newUser);

                System.out.println("User signup successful.");
            }
        } else {
            System.out.println("Failed to retrieve the collection.");
        }
    }
    public static String getUserId(String email){
        Document filter=new Document("email",email);
      Document result= MongoDBConnection.findDocuments("users",filter);
        String usermail=result.getString("email");
       return usermail;
    }
    public static String Login() throws IOException {
        BufferedReader d = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);
        boolean isAuthenticated=false;
        do{
            System.out.println("Choose an option:");
            System.out.println("1. Login");
            System.out.println("2. Sign up");
            System.out.println("3. Exit");
            int option =scanner.nextInt();

            if (option == 1) {
                // Login
                System.out.print("Enter your email: ");
                String email = scanner.next();
                System.out.print("Enter your password: ");
                String password = scanner.next();

                 isAuthenticated = login(email, password);

                if (isAuthenticated) {
                    System.out.println("Login successful.");
                     return getUserId(email);

                } else {
                    System.out.println("Login failed. Invalid email or password.");
                    return null;
                }
            } else if (option == 2) {
                System.out.print("Enter your name: ");
                String name = d.readLine();
                System.out.print("Enter your email: ");
                String email = d.readLine();
                System.out.print("Enter your password: ");
                String password = d.readLine();

                signup(name, email, password);
                return null;
            }
            else if(option==3)
            {
                System.exit(0);
                return null;
            }
            else {
                System.out.println("Invalid option.");
                return null;
            }
        }while(!isAuthenticated);

    }
}
