package ss5_AccessModifier.bai_tap.AccessModifier2;

public class Test {
    private String name = "john";
    private String clazz = "C02";

//    public Test() {
//    }

//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setClazz(String clazz) {
//        this.clazz = clazz;
//    }

    public Test() {
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", clazz='" + clazz + '\'' +
                '}';
    }
}
