/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gchat;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;

/**
 *
 * @author Mishko
 */
public class GChat {

    /**
     * @param args the command line arguments
     */
    public static Email User = new Email("", "", "Ditto22222@gmail.com", "DoppleGanger2");
    static boolean cont = false;
    static ArrayList<String> MainMessage = new ArrayList<String>();

    public static void main(String[] args) {
        Scanner inpt = new Scanner(System.in);
        ArrayList<String> message = new ArrayList<String>();
        message.add("");
        String file, filess = "";
        boolean files = false;
        while (!cont) {
            MainMessage.clear();
            System.out.println("Input User File: ");
            file = inpt.nextLine();
            Email.combineArrays(MainMessage, readFile(file));
            System.out.println("Loading Data...");
            printArray(MainMessage);
        }
        System.out.println("Data Loaded.\nWelcome " + MainMessage.get(0));

        startDittoT();
        while (true) {
            System.out.println("Enter Message: ");
            while (!message.get(message.size() - 1).toUpperCase().equals("END")) {
                message.add(inpt.nextLine());
                if (message.get(message.size()-1).contains("ssss")){
                    message.add(takeScreenShot());
                    files = true;
                    filess = message.get(message.size()-1);
                }
            }
            message.remove(0);
            message.add(0, MainMessage.get(2)+":");
            if (files){
                User.sendEmails(User.address, MainMessage.get(3), arrayToString(removeLast(message)), filess);
                files = false;
            }else{
            User.sendEmails(User.address, MainMessage.get(3), arrayToString(removeLast(message)));
            }
            message.add("");
        }

    }
    public static String takeScreenShot() {
        //Takes a screen shot of the desktop and saves it to the "Exports" folder
        String tempstr = "";
        try {
            Robot robot = new Robot();
            BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            tempstr = System.getProperty("user.dir")+ System.getProperty("file.separator") + User.userName + ".png";
            ImageIO.write(screenShot, "PNG", new File(tempstr));

        } catch (Exception e) {
        }
        return tempstr;
    }
    public static ArrayList removeLast(ArrayList arr1) {
        //removes the last item in an array
        arr1.remove(arr1.size() - 1);
        return arr1;
    }

    public static void printArray(ArrayList<String> temp) {
        //Prints out an Array with numbered items
        for (int i = 0; i < temp.size(); i++) {
            System.out.println(i + ") " + temp.get(i));
        }
    }

    public static ArrayList<String> readFile(String fileName) {

        ArrayList<String> temp = new ArrayList<String>();

        try {

            java.io.File file = new java.io.File(fileName);
            if (file.isFile()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    temp.add(scanner.nextLine());
                }
                scanner.close();
                cont = true;
            } else {
                temp.add("File not found");
                cont = false;
            }
        } catch (FileNotFoundException e) {
            temp.add(e.getLocalizedMessage());
            cont = false;
        }
        return temp;

    }

    public static String arrayToString(ArrayList<String> arr) {
        //Converts an array to a string with a "new line" symbol after each array item
        String temp = "";
        for (int i = 0; i < arr.size(); i++) {
            temp = temp + System.getProperty("line.separator") + arr.get(i);
        }
        return temp;
    }

    static Timer DittoTime;

    public static void startDittoT() {
        //Begins a clock that will initialize email scans every 10 seconds
        DittoTime = new Timer();
        DittoTime.scheduleAtFixedRate(new DittoT(), 0, 10000);

    }

    static class DittoT extends TimerTask {
        //Obtains commands from users email account

        ArrayList<String> Data;
        int i = 0;

        @Override
        public void run() {
            i++;

            Data = (User.readEmail(MainMessage.get(1)));
            for (i = 0; i < Data.size(); i++) {
                if (Data.get(i).contains("\n")) {
                    Data.add(i + 1, Data.get(i).substring(Data.get(i).indexOf("\n") + 1));
                    Data.set(i, Data.get(i).substring(0, Data.get(i).indexOf("\n")));

                }
            }
            if (!Data.isEmpty()) {
                Data.remove(0);
                System.out.println("-----"+arrayToString(Data)+"-----");
                System.out.println("Enter Message:");
            }

            Data.clear();
        }
    }
}
