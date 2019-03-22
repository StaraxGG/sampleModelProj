package Store;

import java.util.function.Consumer;

public class Tuple<X, Func extends Consumer<X>> {
    public X x;
    public final Func function;
    public Tuple(X x, Func function) {
        this.x = x;
        this.function = function;
    }

    public void setState(X x){
        this.x = x;
    }
}
