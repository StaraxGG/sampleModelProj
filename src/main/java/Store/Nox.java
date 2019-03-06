package Store;

import java.util.HashMap;
import java.util.function.Consumer;

public final class Nox {

    static {

    }

    private static HashMap<Object, Object> Store = new HashMap<>();

    public static <val, func extends Consumer<val>> Tuple<val, func> useState(val key){
        Store.put(key, null);
        Consumer<val> function = val -> Store.replace(key, val);
        return new Tuple<>(key, (func) function);
    }
}
