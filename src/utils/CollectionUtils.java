package utils;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionUtils {

    public static <T> ArrayList<T> streamToArrayList(Stream<T> stream) {
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    public static <T> ArrayList<T> intersection(ArrayList<T> col1, ArrayList<T> col2) {
        ArrayList<T> interArrayList = new ArrayList<>();

        for (T val : col1) {
            if (col2.contains(val)) {
                interArrayList.add(val);
            }
        }

        return interArrayList;
    }
}
