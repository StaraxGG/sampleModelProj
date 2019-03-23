package Store;

public class test {
    public static void main(String[] args){
        Tuple x = Nox.useState(0);
        System.out.println(x.x);
        x.setState(5);
        System.out.println(x.x);
    }
}
