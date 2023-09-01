package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int seconds = scanner.nextInt();

        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int seconds1 = seconds  % 60;
        String hours1 = (hours < 24) ? "" + hours: (hours==24) ? "0" :Integer.toString(hours);
        System.out.printf ( hours1 + ":" + "%02d:%02d", minutes, seconds1);
    }
}