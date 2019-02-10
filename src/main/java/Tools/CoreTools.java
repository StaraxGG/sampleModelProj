package Tools;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * An implementation of CoreTools
 *
 * @author ytatar
 * @version 1.0
 * @since 2018-Apr-10
 */
public class CoreTools {

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /**
     * checks a given condition and throws a RuntimeException if the conditions equals false.
     *
     * @param condition the condition which has to equal true
     * @param message   the exception message that will be given
     * @deprecated Please use the traditional way of if (!condition) throw new Exception instead
     */
    public static void check(boolean condition, String message) throws RuntimeException {

        if (!condition) {
            throw new RuntimeException(message);
        }

    }

    /**
     * checks a given condition and throws a RuntimeException if the conditions equals false.
     *
     * @param condition has to equal true
     * @param t         exception that will be thrown
     * @throws Exception
     * @deprecated Please use the traditional way of if (!condition) throw new Exception instead
     */
    public static void check(boolean condition, Exception t) throws Exception {

        if (!condition) {
            throw t;
        }

    }

    /* -------------------------------------------------------------------------------------------------------------- */

    /**
     * swaps the elements in the given array at the two indices
     * @param a T[] array
     * @param index1 int first index
     * @param index2 int second index
     * @param <T> Type of the array
     */
    public static <T> void swap(T[] a, int index1, int index2){
        T tmp = a[index1];
        a[index1] = a[index2];
        a[index2] = tmp;
    }

    /* -------------------------------------------------------------------------------------------------------------- */

    /**
     * returns the name of the current method in this stack trace. the parameter "depth" can help to retrive the name
     * of the method that was called before
     * @param depth int index of the method call in the stack trace, when that is viewed as an array with the current
     *              method starting with an index of 0
     * @return String name of the method at the index depth in the StackTrace
     */
    public static String getStackTraceMethodName(final int depth){
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        return ste[depth].getMethodName();
    }

    /* -------------------------------------------------------------------------------------------------------------- */

    /**
     * this method check if internet connection is available
     * (preferably by checking if the Tmbd-Api can be reached, so we have that checked out as well)
     * @return true, if and only if an internet connection is available
     */
    public static boolean internetConnectionAvailable() {
        //TODO: Joshua
        throw new NotImplementedException();
    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}
