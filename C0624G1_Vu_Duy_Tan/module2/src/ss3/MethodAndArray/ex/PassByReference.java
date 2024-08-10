package ss3.MethodAndArray.ex;

public class PassByReference {

    public static void main(String[] args) {
        Student sA = new Student(1, "Tý");
        Student sB = sA;
        System.out.println("sA: "  + sA);
        System.out.println("sB: "  + sB);

        sA.setName("Tèo");
        System.out.println("sA: "  + sA);
        System.out.println("sB: "  + sB);

        sB.setId(2);
        System.out.println("sA: "  + sA);
        System.out.println("sB: "  + sB);
    }
}
