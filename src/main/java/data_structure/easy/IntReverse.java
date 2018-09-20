package data_structure.easy;

/**
 * https://leetcode-cn.com/problems/reverse-integer/solution/
 */
public class IntReverse {

    public static void main(String[] args) {
        System.out.println(new IntReverse().reverse(123));
        System.out.println(new IntReverse().reverse(-123));
        System.out.println(new IntReverse().reverse(120));
        System.out.println(new IntReverse().reverse(-10));
        System.out.println(new IntReverse().reverse(0));
        System.out.println(new IntReverse().reverse(Integer.MAX_VALUE));
        System.out.println(new IntReverse().reverse(Integer.MIN_VALUE));
        System.out.println(new IntReverse().reverse(-1563847412));
    }

    public int reverse(int x) {
        boolean isNegative = x < 0;
        int abs = isNegative ? -x : x;
        int reversedInt = 0;

        while(abs > 0) {
            int lastPosInt = abs % 10;

            //判断溢出
            if (isNegative) {
                if (reversedInt > -((Integer.MIN_VALUE + lastPosInt) / 10)){
                    return 0;
                }
            }
            else {
                if (reversedInt > (Integer.MAX_VALUE - lastPosInt) / 10){
                    return 0;
                }
            }

            abs /= 10;
            reversedInt = reversedInt * 10 + lastPosInt;
        }

        return isNegative ? -reversedInt : reversedInt;
    }
}
