package ss5.AccessModifier.Ex.Ex01;

public class Employee {
        private long id;
        private String name;
        private boolean isMarried;

        public Employee() {
        }

        public Employee(String name) {
            this.name = name;
        }

        public Employee(String name, boolean isMarried) {
            this.name = name;
            this.isMarried = isMarried;
        }

        public Employee(long id, String name, boolean isMarried) {
            this.id = id;
            this.name = name;
            this.isMarried = isMarried;
        }

        public long getId() {
            return this.id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isMarried() {
            return isMarried;
        }

        public void setMarried(boolean married) {
            isMarried = married;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", isMarried=" + isMarried +
                    '}';
        }
}
