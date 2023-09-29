package MyDatastructures.java;

import java.util.Scanner;

class demo{
    public static void main(String[] args) {
        MyDate d = new MyDate("1-1-1");
        for (int i = 0; i < 100; i++) {
            System.out.println(MyDate.randomDate());
        }
        MyDate date = new MyDate("2016-12-31");
        for (int i = 0; i < 102; i++) {
            System.out.println(date);
            date = date.nextYear();
        }
    }
}
public class MyDate {
    static Scanner scanner = new Scanner(System.in);
    int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    int year, month, day;
    String YEAR_MONTH_DAY;

    public MyDate(MyDate date) {
        this(date.YEAR_MONTH_DAY);
    }

    public MyDate(String YYYY_MM_DD) {
//      assigns respective year month and date
        YEAR_MONTH_DAY = YYYY_MM_DD;
        if (YEAR_MONTH_DAY.equalsIgnoreCase("NEVER_EXPIRES")) {
            year = 0;
            month = 0;
            day = 0;
            return;
        } else {
           YEAR_MONTH_DAY = validateDateString(YYYY_MM_DD);
        }
        year = Integer.parseInt(YEAR_MONTH_DAY.split("-")[0]);
        month = Integer.parseInt(YEAR_MONTH_DAY.split("-")[1]);
        day = Integer.parseInt(YEAR_MONTH_DAY.split("-")[2]);
    }

    public static String validateDateString(String dateString) {
        String[] arr = dateString.split("-");
        for (int i = 0; i < 3; i++) {
            for (int j = 'a'; j <= 'z'; j++) {
                if (arr[i].toLowerCase().contains((char) j + "")) {
                    System.out.println("INVALID DATE RE-ENTER in 'yyyy-dd-mm' format!");
                    return validateDateString(scanner.nextLine());
                }
            }
        }
        return dateString;
    }
    public MyDate(int year, int month, int day) {
        this(year + "-" + month + "-" + day);
    }

    public static MyDate randomDate() {
        return new MyDate("2023-9-30").nextDay((int) (Math.random() * 100));
    }

    private MyDate nextMonth() {
        return new MyDate(day, ++month, year).validateMonth();
    }

    public MyDate nextDay() {
        return validateDate(year, month, ++day);
    }

    private MyDate validateDate(int year, int month, int day) {
            return validateDay(year, month, day);
    }

    private boolean isValid() {
        return day <= daysInMonth[(month - 1) % 12] && month <= 12;
    }

    private MyDate validateMonth() {
        while (month > 12) {
            this.month -= 12;
            this.year++;
        }
        return new MyDate(year, month, day);
    }

    private MyDate validateDay(int year, int month, int day) {

        while (day > daysInMonth[(month - 1) % 12]) {
            if (month == 2 && isLeapYear(year) && day > 29)
                day -= 29;
            else
                day -= daysInMonth[(month - 1) % 12];
            month++;
        }
        return new MyDate(year, month, day).validateMonth();
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
    }

    @Override
    public String toString() {
        return YEAR_MONTH_DAY;
    }

    public MyDate nextYear() {
        return new MyDate(++year, month, day);
    }

    public boolean greaterThan(MyDate secondDate) {
        if (year == 0)
            return false;
        if (this.year > secondDate.year)
            return true;
        if (this.month > secondDate.month)
            return true;
        if (this.day > secondDate.day)
            return true;
        return false;
    }

    public MyDate nextDay(int i) {
        MyDate d = this;
        while (i > 0) {
            d = d.nextDay();
            i--;
        }
        return d;
    }
}