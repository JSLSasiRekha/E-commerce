package org.example;

import org.bson.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import static org.example.Authentication.Login;
import static org.example.Cart.AddToCart;
import static org.example.Cart.DisplayCart;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader d = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan=new Scanner(System.in);
        System.out.println("Welcome to StyleHive");
        String UserEmail=Login();
        boolean isLogin=true;
        do {


            System.out.println(" Select one option");
            System.out.println("1.To Shop");
            System.out.println("2.Go to Cart");
            System.out.println("3. To exit");
            int opt = scan.nextInt();
            switch (opt) {
                case 1:
                    boolean IsShop = true;
                    do {
                        System.out.println("Select which item to shop");
                        System.out.println("1.Clothes");
                        System.out.println("2.Accessories");
                        System.out.println("3.Food");
                        System.out.println("4.Exit");
                        int opt1 = scan.nextInt();
                        switch (opt1) {
                            case 1:
                                boolean isClothes = true;
                                do {
                                    MongoDBConnection.displayTable("Clothes");
                                    System.out.println("select an option to add it into cart or no to exit");
                                    String selectedOption = scan.next();

                                    if (selectedOption.equalsIgnoreCase("no")) {
                                        isClothes = false;
                                        System.out.println("false");
                                    } else
                                        AddToCart(UserEmail, selectedOption.toLowerCase(), "Clothes");

                                } while (isClothes);

                                break;
                            case 2:
                                boolean isAccessories = true;
                                do {
                                    MongoDBConnection.displayTable("Accessories");
                                    System.out.println("select an option to add it into cart or no to exit");
                                    String selectedOption = scan.next();

                                    if (selectedOption.equalsIgnoreCase("no"))
                                        isAccessories = false;
                                    AddToCart(UserEmail, selectedOption.toLowerCase(), "Accessories");

                                } while (isAccessories);

                                break;
                            case 3:
                                boolean isFood = true;
                                do {
                                    MongoDBConnection.displayTable("Food");
                                    System.out.println("select an option to add it into cart or no to exit");
                                    String selectedOption = scan.next();
                                    ;

                                    if (selectedOption.equalsIgnoreCase("no"))
                                        isFood = false;
                                    AddToCart(UserEmail, selectedOption.toLowerCase(), "Food");

                                } while (isFood);

                                break;
                            case 4:
                                IsShop = false;
                                System.out.println("false");
                                break;
                            default:
                                System.out.println("Invalid Option");
                        }

                    } while (IsShop);
                    break;
                case 2:
                    boolean ShowCart = true;

                    do {
                        System.out.println("1.See cart");
                        System.out.println("2. back");
                        int opt2 = scan.nextInt();
                        switch (opt2) {
                            case 1:
                                DisplayCart(UserEmail);
                                break;
                            case 2:
                                ShowCart = false;
                                break;
                            default:
                                System.out.println("Invalid Option");
                                break;
                        }
                    } while (ShowCart);


                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid Option");


            }
        }while(isLogin);
        }
    }
