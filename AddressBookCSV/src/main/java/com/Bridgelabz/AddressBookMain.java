package com.Bridgelabz;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class AddressBookMain {

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book System");
        Scanner sc = new Scanner(System.in);
        //AddressBook addressBook = new AddressBook();
        String filePath = "F:\\program\\src\\AddressBookSystem\\";

        HashMap<String, AddressBook> addressBookHashMap = new HashMap<>();

        while (true){
            System.out.println("1.Add Contact \n2.Display Contact \n3.Edit Contact \n4.Delete Contact \n5.Add new Address Book" +
                    "\n6.Display available address books \n7.Display all address books" +
                    "\n8.Write addressbook to file" +
                    "\n9.Read addressbook from file" +
                    "\n10.Search by city or state" +
                    "\n11.Write Address book contacts in CSV file" +
                    "\n12.Read Address book contacts from CSV file" + "\n13.Exit");
            int ch = sc.nextInt();
            switch (ch){
                case 1:
                    System.out.println("Enter the address book name to add contact in that address book : ");
                    String addressBookName = sc.next();
                    if (!addressBookHashMap.containsKey(addressBookName)){
                        System.out.println("Address book not exists!");
                    }else {
                        AddressBook addressBook = addressBookHashMap.get(addressBookName);
                        addressBook.addContact();
                    }
                    break;
                case 2:
                    System.out.println("Enter the address book name to display contacts in that address book : ");
                    String addressBookName1 = sc.next();
                    if (!addressBookHashMap.containsKey(addressBookName1)){
                        System.out.println("Address book not exists!");
                    }else {
                        AddressBook addressBook = addressBookHashMap.get(addressBookName1);
                        addressBook.displayContact();
                    }
                    break;
                case 3:
                    System.out.println("Enter the address book name to edit contacts in that address book : ");
                    String addressBookName2 = sc.next();
                    if (!addressBookHashMap.containsKey(addressBookName2)){
                        System.out.println("Address book not exists!");
                    }else {
                        AddressBook addressBook = addressBookHashMap.get(addressBookName2);
                        addressBook.editContact();
                    }
                    break;
                case 4:
                    System.out.println("Enter the address book name to edit contacts in that address book : ");
                    String addressBookName3 = sc.next();
                    if (!addressBookHashMap.containsKey(addressBookName3)){
                        System.out.println("Address book not exists!");
                    }else {
                        AddressBook addressBook = addressBookHashMap.get(addressBookName3);
                        addressBook.deleteContact();
                    }
                    break;
                case 5:
                    System.out.println("Enter the address book name : ");
                    String name = sc.next();
                    if (addressBookHashMap.containsKey(name)){
                        System.out.println("Address book already exists!");
                    }else {
                        AddressBook addressBook = new AddressBook();
                        addressBookHashMap.put(name, addressBook);
                    }
                    break;
                case 6:
                    Set<String> keys = addressBookHashMap.keySet();
                    if (keys.isEmpty()){
                        System.out.println("No address books available!");
                    }
                    for (String str : keys){
                        System.out.print(str +" ");
                    }
                    System.out.println();
                    break;
                case 7:
                    Set<Map.Entry<String, AddressBook>> addressBook = addressBookHashMap.entrySet();
                    if (addressBook.isEmpty()){
                        System.out.println("No address books available!");
                    }
                    for (Map.Entry entry :  addressBook){
                        System.out.println(entry.getKey());
                        AddressBook addBook = (AddressBook) entry.getValue();
                        addBook.displayContact();
                    }
                    break;
                case 8:
                    Set<Map.Entry<String, AddressBook>> addressBook1 = addressBookHashMap.entrySet();
                    for (Map.Entry entry :  addressBook1){
                        try {
                            FileOutputStream fos = new FileOutputStream(filePath+entry.getKey()+".txt");
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            AddressBook adBook = (AddressBook) entry.getValue();
                            List<Contact> contacts = adBook.getContactList();
                            oos.writeObject(contacts);
                            oos.close();
                        }catch (Exception exception){
                            System.out.println(exception);
                        }
                    }
                    break;
                case 9:
                    System.out.println("Enter address book name :");
                    String file = sc.next();
                    try{
                        FileInputStream fis = new FileInputStream(filePath+file+".txt");
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        List<Contact> contacts = (List<Contact>) ois.readObject();
                        ois.close();
                        for (Contact contact : contacts){
                            System.out.println(contact);
                        }
                    }catch (Exception exception){
                        System.out.println(exception);
                    }
                    break;
                case 10:
                    Set<Map.Entry<String, AddressBook>> addressBook2 = addressBookHashMap.entrySet();
                    System.out.println("Enter city or state : ");
                    String location = sc.next();
                    if (addressBook2.isEmpty()){
                        System.out.println("No address books available!");
                    }
                    for (Map.Entry entry :  addressBook2){
                        System.out.println(entry.getKey());
                        AddressBook addBook = (AddressBook) entry.getValue();
                        addBook.searchByCityOrState(location);
                    }
                    break;
                case 11:
                    Set<Map.Entry<String, AddressBook>> addressBook3 = addressBookHashMap.entrySet();
                    if (addressBook3.isEmpty()){
                        System.out.println("Address book not available!");
                    }
                    for (Map.Entry entry :  addressBook3){
                        String fileName = entry.getKey().toString();
                        try {
                            // create FileWriter object with file as parameter
                            FileWriter outputfile = new FileWriter(filePath+fileName+"CSV.csv");
                            // create CSVWriter object filewriter object as parameter
                            CSVWriter writer = new CSVWriter(outputfile);

                            String[] header = { "First Name", "Last Name", "Street", "City", "State", "zipcode", "ContactNo", "Email" };
                            writer.writeNext(header);
                            AddressBook addressBook4 = (AddressBook) entry.getValue();
                            List<Contact> contacts = addressBook4.getContactList();

                            for (Contact cnt : contacts){
                                String[] data = {cnt.getFirstName(), cnt.getLastName(), cnt.getStreet(), cnt.getCity(),cnt.getState(),String.valueOf(cnt.getZip()),String.valueOf(cnt.getPhoneNo()),cnt.getEmail()};
                                writer.writeNext(data);
                            }

                            writer.close();

                        }catch (Exception exception){
                            System.out.println(exception);
                        }
                    }
                    break;
                case 12:
                    System.out.println("Enter address book name :");
                    String addBookName = sc.next();
                    File file1 = new File(filePath+addBookName+"CSV.csv");
                    String fileName = filePath+addBookName+"CSV.csv";
                    if (!file1.exists()){
                        System.out.println(addBookName +" not found!");
                    }else{
                        try {
                            Reader reader = Files.newBufferedReader(Paths.get(fileName));
                            CSVReader csvReader = new CSVReader(reader);

                            String[] nextRecord;
                            csvReader.readNext();
                            while ((nextRecord = csvReader.readNext()) != null){
                                System.out.print("First name:"+ nextRecord[0] + "\t");
                                System.out.print("Last name:"+ nextRecord[1] + "\t");
                                System.out.print("Street:"+ nextRecord[2] + "\t");
                                System.out.print("City:"+ nextRecord[3] + "\t");
                                System.out.print("State:"+ nextRecord[4] + "\t");
                                System.out.print("Zip code:"+ nextRecord[5] + "\t");
                                System.out.print("Phone:"+ nextRecord[6] + "\t");
                                System.out.print("Email:"+ nextRecord[7] + "\t");
                                System.out.println();
                            }

                        }catch (Exception ex){
                            System.out.println(ex);
                        }
                    }
                    break;
                case 13:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }

    }
}
