package ss5.AccessModifier.Ex.Ex02;

public class Student {
        private int id;
        private String fullName;
        private String email;
        private String phone;
        private String clazz;

        public Student(int id, String fullName, String email, String phone, String clazz) {
            this.id = id;
            this.fullName = fullName;
            this.email = email;
            this.phone = phone;
            this.clazz = clazz;
        }

        public void study() {
            System.out.println("Tui đang học nè");
        }

        public void doExercise() {
            System.out.println("Tui đang làm bài tập nè");
        }

        public void doExam() {
            System.out.println("Tui đang thi nè");
        }

        public void waitingResult() {
            System.out.println("Tui đang chờ kết quả thi nè");
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", fullName='" + fullName + '\'' +
                    ", email='" + email + '\'' +
                    ", phone='" + phone + '\'' +
                    ", clazz='" + clazz + '\'' +
                    '}';
        }
}
