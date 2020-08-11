package com.zakris.records;

import java.util.Scanner; 

public class Records {
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Artist name:");
        String artist = scan.nextLine();
        
        System.out.println("Album name:");
        String album = scan.nextLine();

        System.out.println("Year:");
        int year = scan.nextInt();

        System.out.println("Notes:");
        String notes = scan.nextLine();

        System.out.println("The artist is: " + artist);
        System.out.println("The album is: " + album);
        System.out.println("The year is: " + year);
        System.out.println(notes);

        scan.close();
    }
}
