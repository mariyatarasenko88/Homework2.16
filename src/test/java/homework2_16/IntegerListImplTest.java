package homework2_16;

import homework2_16.exception.MyIndexOutOfBoundsException;
import homework2_16.model.IntegerList;
import homework2_16.model.IntegerListImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static homework2_16.IntegerListImplConstants.*;

public class IntegerListImplTest {
    private final IntegerList integerList = new IntegerListImpl();

    @BeforeEach
    public void beforeEach() {
        integerList.add(VALUE_1);
        integerList.add(VALUE_2);
        integerList.add(VALUE_3);
        integerList.add(VALUE_4);
    }

    @AfterEach
    public void afterEach() {
        integerList.clear();
    }

    @Test
    public void addValue() {
        assertThat(integerList.toArray())
                .isEqualTo(new Integer[]{VALUE_1, VALUE_2, VALUE_3, VALUE_4});
    }

    @Test
    public void addValueByIndexPositive() {
        integerList.add(2, VALUE_5);
        integerList.add(2, VALUE_1);
        integerList.add(2, VALUE_3);
        integerList.add(2, VALUE_5);
        assertThat(integerList.toArray())
                .isEqualTo(new Integer[]{VALUE_1, VALUE_2, VALUE_5, VALUE_3, VALUE_1, VALUE_5, VALUE_3, VALUE_4});
    }

    @Test
    public void addValueByIndexNegative() {
        assertThatExceptionOfType(MyIndexOutOfBoundsException.class)
                .isThrownBy(() -> integerList.add(8, VALUE_5))
                .withMessage(ERROR_MESSAGE_NOT_CORRECT_INDEX);
    }

    @Test
    public void addValueNegative() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> integerList.add(null))
                .withMessage("Значение не должно быть равно null");
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> integerList.add(1, null))
                .withMessage("Значение не должно быть равно null");
    }

    @Test
    public void setValuePositive() {
        integerList.set(3, VALUE_1);
        assertThat(integerList.toArray())
                .isEqualTo(new Integer[]{VALUE_1, VALUE_2, VALUE_3, VALUE_1});
    }

    @Test
    public void setValueNegative() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> integerList.set(1, null))
                .withMessage("Значение не должно быть равно null");
        assertThatExceptionOfType(MyIndexOutOfBoundsException.class)
                .isThrownBy(() -> integerList.set(5, VALUE_1))
                .withMessage("Такого элемента не существует");
        assertThatExceptionOfType(MyIndexOutOfBoundsException.class)
                .isThrownBy(() -> integerList.set(300, VALUE_1))
                .withMessage("Превышен размер внутреннего массива");
    }

    @Test
    public void removeValueByIndex() {
        integerList.remove(1);
        assertThat(integerList.toArray())
                .isEqualTo(new Integer[]{VALUE_1, VALUE_3, VALUE_4});
    }

    @Test
    public void removeValueByItemPositive() {
        integerList.remove(VALUE_3);
        assertThat(integerList.toArray())
                .isEqualTo(new Integer[]{VALUE_1, VALUE_2, VALUE_4});
    }

    @Test
    public void removeValueByItemNegative() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> integerList.remove(null))
                .withMessage("Значение не должно быть равно null");
        assertThatExceptionOfType(MyIndexOutOfBoundsException.class)
                .isThrownBy(() -> integerList.remove(VALUE_NON_ELEMENT));
    }
    @Test
    public void containsValueInArray() {
        integerList.add(VALUE_12);
        integerList.add(VALUE_42);
        integerList.add(VALUE_15);
        integerList.add(VALUE_31);

        assertThat(integerList.contains(VALUE_31))
                .isTrue();
        assertThat(integerList.contains(VALUE_3))
                .isTrue();

        assertThat(integerList.contains(VALUE_NEGATIVE))
                .isFalse();
        assertThat(integerList.contains(VALUE_NON_ELEMENT))
                .isFalse();

        integerList.clear();


    }

    @Test
    public void lastIndexOf() {
        assertThat(integerList.lastIndexOf(VALUE_3))
                .isEqualTo(2);
        assertThat(integerList.lastIndexOf(VALUE_NON_ELEMENT))
                .isEqualTo(-1);
    }
    @Test
    public void getValue() {
        assertThat(integerList.get(1))
                .isEqualTo(VALUE_2);
    }
    @Test
    public void equalsArrays() {
        IntegerList integerListSecond = new IntegerListImpl();
        integerListSecond.add(VALUE_1);
        integerListSecond.add(VALUE_2);
        integerListSecond.add(VALUE_3);
        assertThat(integerList.equals(integerListSecond))
                .isFalse();
        integerListSecond.add(VALUE_4);
        assertThat(integerList.equals(integerListSecond))
                .isTrue();
        integerListSecond.set(2, VALUE_1);
        assertThat(integerList.equals(integerListSecond))
                .isFalse();
    }
    @Test
    public void sizeArray() {
        assertThat(integerList.size())
                .isEqualTo(4);
    }

    @Test
    public void bigSizeArray() {
        integerList.add(VALUE_3);
        integerList.add(VALUE_5);
        integerList.add(VALUE_3);
        integerList.add(VALUE_1);
        integerList.add(VALUE_3);
        integerList.add(VALUE_1);


        assertThat(integerList.size())
                .isEqualTo(10);
    }

    @Test
    public void bigSizeArrayInsert() {
        integerList.add(VALUE_3, 0);
        integerList.add(VALUE_5, 2);
        integerList.add(VALUE_3, 1);
        integerList.add(VALUE_1, 3);
        integerList.add(VALUE_3, 3);
        integerList.add(VALUE_1, 0);


        assertThat(integerList.size())
                .isEqualTo(10);
    }

    @Test
    public void isEmptyAndClearArray() {
        assertThat(integerList.isEmpty())
                .isFalse();
        integerList.clear();
        assertThat(integerList.isEmpty())
                .isTrue();
    }
}

