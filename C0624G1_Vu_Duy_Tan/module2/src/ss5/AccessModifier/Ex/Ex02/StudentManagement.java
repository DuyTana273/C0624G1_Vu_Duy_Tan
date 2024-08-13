package ss5.AccessModifier.Ex.Ex02;

public class StudentManagement {
    public static void main(String[] args) {
        Student khang = new Student(1, "Lương Triệu Khang",
                "trieukhang2k6@gmail.com",
                "093336123", "C0624G1");

        Student tan = new Student(2, "Vũ Duy Tân", "duytan@gmail.com",
                "012347586", "C0624G1");

        Student hoang = new Student(3, "Đỗ Hoàng", "dohoang@gmail.com",
                "012347567", "C0624G1");

//        System.out.println(khang);
//        System.out.println(tan);
//        System.out.println(hoang);

        //Cách 1:
        Student[] studentsArray1 = {khang, tan, hoang};

        //Cách 2:
        Student[] studentsArray2 = new Student[3];

        for (int i = 0; i < 3; i++) {
            System.out.println(studentsArray1[i]);
        }

        studentsArray1[0].doExercise();
    }
}
