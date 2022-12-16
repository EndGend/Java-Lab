package lab0;

import java.util.HashMap;
import java.util.Map;

public class Variant5 {
    enum MATH_ACTIONS{
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        DIVISION
    }
    /**
     * @param a length of segment
     * @param b length of smaller segment
     * @return length of the unoccupied segment A by overlapping segments B
     */
    public int integerTask(int a, int b){
        assert a > b: "a > b";
        return a % b;
    }

    /**
     * @param a number
     * @param b number
     * @return return true is a=0 and b<-2
     */
    public boolean booleanTask(int a, int b){
        return a == 0 || b < -2;
    }

    /**
     *
     * @param a number
     * @param b number
     * @param c number
     * @return array where [0] is count of positive numbers, [1] is count of negative numbers
     */
    public int[] ifTask(int a, int b, int c){
        assert a != 0 && b!=0 && c!=0;
        int positive_count = 0;
        int negative_count = 0;
        if(a > 0){
            positive_count++;
        } else {
            negative_count++;
        }
        if(b > 0){
            positive_count++;
        } else {
            negative_count++;
        }
        if(c > 0){
            positive_count++;
        } else {
            negative_count++;
        }
        return new int[]{positive_count,negative_count};
    }

    /**
     *
     * @param n action
     * @param a number
     * @param b number
     * @return result of action
     */
    public double switchTask(MATH_ACTIONS n, int a, int b){
        switch(n){
            case ADDITION : return a + b;
            case DIVISION : return (double) a/b;
            case SUBTRACTION : return a-b;
            case MULTIPLICATION : return a*b;
        }
        return 0.0;
    }

    /**
     * @param n price of 1 kg of candies
     * @return price of 0.1, 0.2, ..., 1 kg of candies
     */
    public Map<Double,Double> forTask(double n){
        assert n >0;
        Map<Double,Double> map = new HashMap<>();
        map.put(1.0,n);

        for(int i = 1; i < 10; i++){
            double p = (double) i/10;
            map.put(p,n*p);
        }
        return map;
    }

    /**
     * @param n n=2^k
     * @return k
     */
    public int whileTask(int n){
        assert n>0;
        int k =0;
        while (n != 1){
            n/=2;
            k++;
        }
        return k;
    }

    /**
     * @param n array size
     * @return array of fibonacci numbers
     */
    public int[] arrayTask(int n){
        assert n>0;
        int[] a = new int[n];
        a[0]=1;
       if(n<=1) return a;
       a[1]=1;
        for (int i = 2; i<n;i++){
            a[i] = a[i-2]+a[i-1];
        }
        return a;
    }

    /**
     *
     * @param m matrix param
     * @param n matrix param
     * @param d number of arithmetical progression
     * @param a data set
     * @return first column is data set, other is a[i]+d
     */
    public int[][] matrixTask(int m, int n, int d, int[] a){
        assert m>0 && m>0 && a.length == m;
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(j == 0){ result[i][0] = a[i];}
                else {
                    result[i][j] = result[i][j-1] + d;
                }
            }
        }
        return result;
    }
}
