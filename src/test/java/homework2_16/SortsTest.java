package homework2_16;

import homework2_16.model.IntegerList;
import homework2_16.model.IntegerListImpl;
import homework2_16.model.Sorts;
import org.junit.jupiter.api.Test;

import static homework2_16.IntegerListImplConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SortsTest {
    private final IntegerList integerList = new IntegerListImpl();

    @Test
    public void checkSorts() {
        integerList.add(VALUE_4);
        integerList.add(VALUE_1);
        integerList.add(VALUE_2);
        integerList.add(VALUE_5);
        integerList.add(VALUE_3);
        integerList.add(VALUE_5);
        integerList.add(VALUE_1);
        Integer[] expected = Sorts.runSort(integerList.toArray());
        Integer[] actual = new Integer[]{
                VALUE_1, VALUE_1, VALUE_2, VALUE_3, VALUE_5, VALUE_5
        };

        assertThat(expected.equals(actual));
    }
}
