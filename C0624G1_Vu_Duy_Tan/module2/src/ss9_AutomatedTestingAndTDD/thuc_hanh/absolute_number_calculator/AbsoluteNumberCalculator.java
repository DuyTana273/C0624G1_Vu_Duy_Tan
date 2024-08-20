package ss9_AutomatedTestingAndTDD.thuc_hanh.absolute_number_calculator;

public class AbsoluteNumberCalculator {
    public static int findAbsolute(int number){
        if(number < 0)
            return  -number;
        return number;
    }
}
