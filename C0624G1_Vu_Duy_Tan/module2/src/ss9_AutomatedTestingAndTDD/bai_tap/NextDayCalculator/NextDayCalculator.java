package ss9_AutomatedTestingAndTDD.bai_tap.NextDayCalculator;

public class NextDayCalculator {
    public static String findNextDay(int day, int month, int year) {
        if (day == 31 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10)) {
            day = 1;
            month += 1;
        } else if (day == 30 && (month == 4 || month == 6 || month == 9 || month == 11)) {
            day = 1;
            month += 1;
        } else if (month == 2) {
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                if (day == 29) {
                    day = 1;
                    month += 1;
                } else if (day == 28) {
                    day += 1;
                }
            } else {
                if (day == 28) {
                    day = 1;
                    month += 1;
                }
            }
        } else if (month == 12 && day == 31) {
            day = 1;
            month = 1;
            year += 1;
        } else {
            day += 1;
        }

        return day + "/" + month + "/" + year;
    }
}
