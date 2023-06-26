package org.example;

import org.bson.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import static org.example.Authentication.Login;
import static org.example.Cart.AddToCart;
import static org.example.Cart.DisplayCart;
import static org.example.MongoDBConnection.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader d = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan=new Scanner(System.in);
        System.out.println("-------------------------------------------Welcome to SSS----------------------------------------------");
        System.out.println("   __o__      __o__      __o__ \n" +
                "    />  \\      />  \\      />  \\  \n" +
                "    \\o         \\o         \\o     \n" +
                "     v\\         v\\         v\\    \n" +
                "      <\\         <\\         <\\   \n" +
                " _\\o__</    _\\o__</    _\\o__</   \n" +
                "                              ");
        Login();
        Authentication.Auth user = new Authentication.Auth();
        String UserEmail=user.getUserEmail();
        System.out.println(UserEmail);
        boolean isLogin=true;
        do {

            System.out.println("Enter as( select number):");
            System.out.println("1.USER");
            System.out.println("2.ADMIN");
            int userType= scan.nextInt();
            if(userType==1) {
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
                                        displayTable("Clothes");
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
                                        displayTable("Accessories");
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
                                        displayTable("Food");
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
            }
            else if(userType==2)
            {
                boolean isAdmin=true;
                do {
                    System.out.println("Select an option: ");
                    System.out.println("1.See stock");
                    System.out.println("2.Update stock");
                    System.out.println("3.Delete Stock");
                    System.out.println("4.exit");
                    int opt = scan.nextInt();
                    switch (opt) {
                        case 1:
                            boolean tosee=true;
                            do {
                                System.out.println("Select which stock to see");
                                System.out.println("1.Clothes");
                                System.out.println("2.Accessories");
                                System.out.println("3.Food");
                                System.out.println("4.Exit");
                                int opt1 = scan.nextInt();
                                switch (opt1) {
                                    case 1:
                                        displayTable("Clothes");
                                        break;
                                    case 2:
                                        displayTable("Accessories");
                                        break;
                                    case 3:
                                        displayTable("Food");
                                        break;
                                    case 4:
                                        tosee=false;
                                        break;
                                    default:
                                        System.out.println("Invalid option");
                                        break;
                                }
                            }while(tosee);
                            break;
                        case 2:
                            boolean toUpdate=true;
                            do {
                                System.out.println("Select which stock to update");
                                System.out.println("1.Clothes");
                                System.out.println("2.Accessories");
                                System.out.println("3.Food");
                                System.out.println("4.Exit");
                                int opt2 = scan.nextInt();

                                switch (opt2) {
                                    case 1:
                                        System.out.println("Enter field name of condition to update: ");
                                        String field = scan.next();
                                        System.out.println("Enter field value of condition to update:");
                                        String value = scan.next();
                                        System.out.println("Enter field name to update: ");
                                        String Updatedfield = scan.next();
                                        System.out.println("Enter field value to update:");
                                        String Updatedvalue = scan.next();
                                        updateDocument("Clothes", field, value, Updatedfield, Updatedvalue);
                                        break;
                                    case 2:
                                        System.out.println("Enter field name of condition to update: ");
                                        String field1 = scan.next();
                                        System.out.println("Enter field value of condition to update:");
                                        String value1 = scan.next();
                                        System.out.println("Enter field name to update: ");
                                        String Updatedfield1 = scan.next();
                                        System.out.println("Enter field value to update:");
                                        String Updatedvalue1 = scan.next();
                                        updateDocument("Accessories", field1, value1, Updatedfield1, Updatedvalue1);
                                        break;
                                    case 3:
                                        System.out.println("Enter field name of condition to update: ");
                                        String field2 = scan.next();
                                        System.out.println("Enter field value of condition to update:");
                                        String value2 = scan.next();
                                        System.out.println("Enter field name to update: ");
                                        String Updatedfield2 = scan.next();
                                        System.out.println("Enter field value to update:");
                                        String Updatedvalue2 = scan.next();
                                        updateDocument("Food", field2, value2, Updatedfield2, Updatedvalue2);
                                        break;
                                    case 4:
                                        toUpdate=false;
                                        break;
                                    default:
                                        System.out.println("Invalid option");
                                        break;
                                }
                            }while(toUpdate);
                            break;
                        case 3:
                            boolean todelete=true;
                            do {
                                System.out.println("Select which stock to delete");
                                System.out.println("1.Clothes");
                                System.out.println("2.Accessories");
                                System.out.println("3.Food");
                                System.out.println("4.back");
                                int opt3 = scan.nextInt();


                                switch (opt3) {
                                    case 1:
                                        System.out.println("Enter field name of condition to delete: ");
                                        String dfield = scan.next();
                                        System.out.println("Enter field value of condition to delete:");
                                        String dvalue = scan.next();
                                        deleteDocuments("Clothes", dfield, dvalue);
                                        break;
                                    case 2:
                                        System.out.println("Enter field name of condition to delete: ");
                                        String dfield1 = scan.next();
                                        System.out.println("Enter field value of condition to delete:");
                                        String dvalue1 = scan.next();
                                        deleteDocuments("Accessories", dfield1, dvalue1);
                                        break;
                                    case 3:
                                        System.out.println("Enter field name of condition to delete: ");
                                        String dfield2 = scan.next();
                                        System.out.println("Enter field value of condition to delete:");
                                        String dvalue2 = scan.next();
                                        deleteDocuments("Food", dfield2, dvalue2);
                                        break;
                                    case 4:
                                        todelete=false;
                                        break;
                                    default:
                                        System.out.println("Invalid option");
                                        break;
                                }
                            }while(todelete);
                            break;
                        case 4:
                            isAdmin=false;
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid option");
                            break;

                    }
                }while(isAdmin);
            }
        }while(isLogin);
        }
    }
