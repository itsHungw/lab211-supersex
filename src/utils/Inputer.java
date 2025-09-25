package utils;

import java.util.Scanner;

public class Inputer {
    private static final Scanner sc = new Scanner(System.in);

    public Inputer() {
    }

    //input String without validation
    public static String getString(String msg) {
        System.out.print(msg);  //message for user to input
        return sc.nextLine().trim();
    }

    //input loop until valid
    public static String getInputWithLoop(String msg, String regex, String errMessage, boolean isAllowEmpty) {
        String data;
        do {
            System.out.print(msg);
            data = sc.nextLine().trim();
            if(data.isEmpty()){
                if (isAllowEmpty) {
                    return data;
                }
                System.out.println("Input cannot be empty!");
            }
            if(data.equalsIgnoreCase("exit")){
                return data;
            }
            if (Acceptable.isValid(data, regex)) {
                return data;
            }
            System.out.println(errMessage);
        } while (true);
    }

    //input Integer
    public static int getInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid! Input must be number, please try again!");
            }
        }
    }

    //input double
    public static double getDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid! Input must be number, please try again!");
            }
        }
    }


}

