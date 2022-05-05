/*
 * Copyright(C) 2005, Nguyen Van Hieu.
 * J1.S.P0071
 * Task management
 * 
 * Record of change:
 * DATE             Version             AUTHOR              DESCRIPTION
 * 2021-09-08       1.0                 Hieunv              First Implement
 */
package taskmanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * The class contains method to get input number in range , a method to get
 * input date , a method to get input plan and input string , a method to get
 * input a integer number , a method to get input answer of Y/N question and a
 * method to check id is exist or not.
 * <p>
 * Bugs: Still don't have it or not yet know it
 *
 * @author Nguyen Van Hieu
 */
public class GetInput {

    private final static Logger LOGGER = Logger.getLogger("Task management");  //use logger to display to console
    private static Scanner scanner = new Scanner(System.in);

    /**
     * This method takes an integer between min and max.
     *
     * @param min min range
     * @param max max range
     * @param message The information displayed requires user input
     * @return
     */
    public int getInputNumberInRange(String message, int min, int max) {
        while (true) {
            try {
                System.out.print(message);
                int inputNumber = Integer.parseInt(scanner.nextLine());
                if (inputNumber >= min && inputNumber <= max) {
                    return inputNumber;
                }
                //Invalid value message
                LOGGER.warning("Invalid value. Please try again!");
            } catch (NumberFormatException e) {
                //Invalid value message
                LOGGER.warning("Invalid value. Please try again!");
            }
        }
    }

    /**
     * This method get user input a Date until it is a valid date in format
     * "month-date-year".
     *
     * @param message out to console.
     * @param update take to check option is update or not. If it's update and
     * user input nope return nope.
     * @return a positive integer.
     */
    LocalDate getInputDate(String message, int length) {
        while (true) {
            LocalDate inputDate = checkValidDate(getInputString(message, length));
            if (inputDate != null) {
                return inputDate;
            }
        }
    }

    /**
     * This method is be used to get input plan time by user and check time
     * between 8 and 17.5 to perform functions add task and update task
     *
     * @param message The information displayed requires user input
     * @param checkUpdate check value to distinguish the case of update or not
     * @return a string
     */
    public String getInputPlan(String message, boolean checkUpdate) {
        while (true) {
            try {
                System.out.print(message);
                String inputString = scanner.nextLine().trim();
                if (inputString.equalsIgnoreCase("nope") && checkUpdate) {//case of update
                    return "0";
                }
                if (Float.valueOf(inputString) >= 8 && Float.valueOf(inputString) <= 17.5) {//check input value range
                    return inputString;
                }
                LOGGER.warning("The input value must be between 8.0 and 17.5. Please try again!");
            } catch (Exception e) {
                LOGGER.warning("The input value must be between 8.0 and 17.5. Please try again!");
            }
        }
    }

    /**
     * This method is used to get input string by user and check this string
     * less or equal than limited length.
     *
     * @param message The information displayed requires user input
     * @param length limited length
     * @return a string
     */
    public String getInputString(String message, int length) {
        while (true) {
            System.out.print(message);
            String inputString = scanner.nextLine().trim();
            //Check if the string satisfies the condition
            if (!inputString.isEmpty() && inputString.length() <= length) {
                return inputString;
            } else {
                LOGGER.warning("Invalid input string. Please try again!");
            }
        }
    }

    /**
     * This method is used to get a positive integer.
     *
     * @param message content should be communicated to the user.
     * @return a integer number
     */
    public int getInputInteger(String message) {
        while (true) {
            try {
                System.out.print(message);
                int inputNumber = Integer.parseInt(scanner.nextLine());
                //Check input number must be greater than 0
                if (inputNumber > 0) {
                    return inputNumber;
                } else {
                    LOGGER.warning("Invalid value. Please try again!");
                }
            } catch (NumberFormatException e) {
                LOGGER.warning("Invalid value. Please try again!");
            }
        }
    }

    /**
     * This method is used to get entered answer by user , the answer must be
     * y(yes) or n(no)
     *
     * @param message The information displayed requires user input
     * @return true or false
     */
    public boolean getInputAnswer(String message) {
        while (true) {
            System.out.println(message);
            String answer = getInputString("Your answer is: ", 1);
            if (answer.equalsIgnoreCase("y")) {
                return true;
            }
            if (answer.equalsIgnoreCase("n")) {
                return false;
            }
            LOGGER.warning("Your input must be (Y) or (N)");
        }
    }

    /**
     * This method use to check that user input is valid date format
     * "month-date-year".
     *
     * @param inputDate - string that user input.
     * @return true if user input valid date type. Otherwise return false.
     */
    LocalDate checkValidDate(String inputDate) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM-dd-yyyy");
            Date date = simpleDateFormat.parse(inputDate); // String to date
            String dateFormatted = simpleDateFormat.format(date); // Format to MMM-dd-yyyy
            LocalDate timeReturn = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (inputDate.equalsIgnoreCase(dateFormatted) && timeReturn.compareTo(LocalDate.now()) >= 0) {
                return timeReturn;
            }
            LOGGER.warning("Your input is invalid. Please try again!");
        } catch (ParseException ex) {
            LOGGER.warning("Your input is invalid (It's must be in format MMM/dd/yyyy). Please try again!");
        }
        return null;
    }

}
