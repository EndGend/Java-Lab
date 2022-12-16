package lab0;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


class Variant5Test {
    Variant5 obj = new Variant5();
    @ParameterizedTest
    @MethodSource("integerNumbersTaskProvider")
    void integerNumbersTask(int a, int b,int expected) {
        assertEquals(expected,obj.integerTask(a,b));
    }

    @ParameterizedTest
    @MethodSource("booleanTaskProvider")
    void booleanTask(int a,int b, boolean expected) {
        assertEquals(expected,obj.booleanTask(a,b));
    }

    @ParameterizedTest
    @MethodSource("ifTaskProvider")
    void ifTask(int a,int b, int c, int[] expected) {
        assertArrayEquals(expected,obj.ifTask(a,b,c));
    }

    @ParameterizedTest
    @MethodSource("switchTaskProvider")
    void switchTask(int a, int b, Variant5.MATH_ACTIONS action, double expectedResult) {
        assertEquals(expectedResult, obj.switchTask(action,a,b));
    }

    @Test
    void forTask() {
        var map = new HashMap<Double,Double>();
        map.put(1.0,10.0);
        map.put(0.1,1.0);
        map.put(0.2,2.0);
        map.put(0.3,3.0);
        map.put(0.4,4.0);
        map.put(0.5,5.0);
        map.put(0.6,6.0);
        map.put(0.7,7.0);
        map.put(0.8,8.0);
        map.put(0.9,9.0);
        assertEquals(map,obj.forTask(10));
    }

    @ParameterizedTest
    @MethodSource("whileTaskProvider")
    void whileTask(int n,int expectedResult) {
        assertEquals(expectedResult,obj.whileTask(n));
    }

    @ParameterizedTest
    @MethodSource("arrayTaskProvider")
    void arrayTask(int n, int[] expectedValue) {
        assertArrayEquals(expectedValue,obj.arrayTask(n));
    }

    @ParameterizedTest
    @MethodSource("matrixTaskProvider")
    void matrixTask(int m, int n, int d, int[] a, int[][] expectedResult) {
        assertArrayEquals(expectedResult,obj.matrixTask(m,n,d,a));
    }

    public static Object[][] whileTaskProvider(){
        return new Object[][]{
                {4,2},
                {8,3},
                {16,4}
        };
    }
    public static Object[][] arrayTaskProvider(){
        return new Object[][]{
                {2,new int[]{1,1}},
                {1,new int[]{1}},
                {3,new int[]{1,1,2}},
                {4,new int[]{1,1,2,3}},
                {5,new int[]{1,1,2,3,5}}
        };
    }
    public static Object[][] matrixTaskProvider(){
        return new Object[][]{
                {2,3,5, new int[]{1,2},new int[][]{
                        {1,6,11},
                        {2,7,12}
                }}
        };
    }
    public static Object[][] switchTaskProvider(){
        return new Object[][]{
                {1,2, Variant5.MATH_ACTIONS.ADDITION,3},
                {1,2, Variant5.MATH_ACTIONS.SUBTRACTION,-1},
                {1,2, Variant5.MATH_ACTIONS.DIVISION,0.5},
                {1,2, Variant5.MATH_ACTIONS.MULTIPLICATION,2},
        };
    }
    public static Object[][] ifTaskProvider(){
        return new Object[][]{
                {-1,-2,-3,new int[]{0,3}},
                {1,-3,1, new int[]{2,1}},
                {12,13,2, new int[]{3,0}}
        };
    }
    public static Object[][] booleanTaskProvider(){
        return new Object[][]{
                {1,-3,true},
                {1,0,false},
                {4,4,false}
        };
    }
    public static Object[][] integerNumbersTaskProvider(){
        return new Object[][]{
                {4,2,0},
                {5,2,1},
                {31,10,1}
        };
    }
}