/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Administrator
 */
public class BigInteger {

    private String bigNum;

    /**
     * Constructor
     */
    BigInteger() {
        bigNum = new String();
    }

    /**
     * Constructor have params
     *
     * @param a
     */
    BigInteger(String a) {
        bigNum = new String(a);
    }

    /**
     * Copy constructor
     *
     * @param a
     */
    BigInteger(BigInteger a) {
        bigNum = new String(a.bigNum);
    }

    /**
     *
     * @param a
     * @param index
     * @return number of string at index
     */
    public static int numberOfString(String a, int index) {
        return Integer.parseInt(a.charAt(index) + "");
    }

    /**
     * Cut the unmean zero in number
     * @param a
     * @return
     */
    public static String cutUnmeaningZero(String a) {
        String rs = a;
        int i = 0;
        for (i = 0; i < rs.length(); i++) {
            if (BigInteger.numberOfString(rs, i) != 0) {
                break;
            }
        }
        if (i != rs.length()) {
            rs = rs.substring(i);
        } else {
            rs = "0";
        }
        return rs;
    }

    /**
     * Add zero to have the same size numbers
     * @param another
     */
    public void addZero(BigInteger another) {
        String a = this.bigNum;
        String b = another.bigNum;
        while (a.length() < b.length()) {
            a = "0" + a;
        }
        while (a.length() > b.length()) {
            b = "0" + b;
        }
        this.bigNum = a;
        another.bigNum = b;
    }

    /**
     * Compare two numbers
     * @param another
     * @return
     */
    public int compare(BigInteger another) {
        int i = 0;
        int check = 0;
        this.addZero(another);
        String a = this.bigNum;
        String b = another.bigNum;
        while (i < b.length()) {
            int firstA = BigInteger.numberOfString(a, i);
            int firstB = BigInteger.numberOfString(b, i);
            if (firstA != firstB) {
                if (firstA > firstB) {
                    check = 1;
                } else {
                    check = -1;
                }
                break;
            }
            i++;
        }
        return check;
    }

    /**
     * Sum two numbers
     * @param another
     * @return
     */
    public BigInteger plus(BigInteger another) {
        this.addZero(another);
        String result = new String();
        String a = this.bigNum;
        String b = another.bigNum;
        int carry = 0; // miss something in operation
        for (int i = a.length() - 1; i >= 0; i--) {
            int tmp = BigInteger.numberOfString(a, i) + BigInteger.numberOfString(b, i) + carry;
            result = Integer.toString(tmp % 10) + result; // 0 or anything
            carry = (int) tmp / 10; // 0 or 1

        }
        if (carry != 0) {
            result = Integer.toString(carry) + result;
        }
        return new BigInteger(result);
    }

    public String getBigNum() {
        return bigNum;
    }

    /**
     * Minus two numbers
     * @param another
     * @return
     */
    public BigInteger minus(BigInteger another) {
        String result = new String();
        int cmp = this.compare(another);
        String a, b; // a bigger stand front
        if (cmp >= 0) {
            a = this.bigNum;
            b = another.bigNum;
        } else {
            a = another.bigNum;
            b = this.bigNum;
        }
        int carry = 0;
        for (int i = a.length() - 1; i >= 0; i--) {
            int up = BigInteger.numberOfString(a, i);
            int down = BigInteger.numberOfString(b, i) + carry;
            carry = 0;
            if (up < down) {
                up = up + 10;
                carry = 1;
            }
            int tmp = up - down;
            result = Integer.toString(tmp) + result;
        }
        return new BigInteger(BigInteger.cutUnmeaningZero(result));
    }

    /**
     * Multiply two numbers
     * @param another
     * @return
     */
    public BigInteger multiply(BigInteger another) {
        String result = "";
        BigInteger thisTmp = new BigInteger(this);
        BigInteger tmp = new BigInteger(this);
        while (!BigInteger.cutUnmeaningZero(another.bigNum).equals("0")) {
            result = thisTmp.plus(tmp).bigNum;
            thisTmp.bigNum = result;
            another.bigNum = another.minus(new BigInteger("1")).bigNum;
        }
        result = thisTmp.minus(tmp).bigNum;
        return new BigInteger(BigInteger.cutUnmeaningZero(result));
    }

    /**
     * Get DIV
     * @param another
     * @return
     */
    public BigInteger divideDiv(BigInteger another) {
        int cmp = this.compare(another);
        BigInteger thisTmp = new BigInteger(this);
        BigInteger tmp = new BigInteger("0");
        if (cmp == 0) {
            return new BigInteger("1");
        }
        while (cmp >= 0) {
            tmp = new BigInteger(tmp.plus(new BigInteger("1")));
            thisTmp.bigNum = thisTmp.minus(another).bigNum;
            cmp = thisTmp.compare(another);
        }
        return new BigInteger(tmp.bigNum);
    }

    /**
     * Get MOD
     * @param another
     * @return
     */
    public BigInteger divideMod(BigInteger another) {
        int cmp = this.compare(another);
        BigInteger thisTmp = new BigInteger(this);
        BigInteger tmp = new BigInteger("1");
        if (cmp == 0) {
            return new BigInteger("1");
        }
        while (cmp >= 0) {
            tmp = new BigInteger(tmp.plus(new BigInteger("1")));
            thisTmp.bigNum = thisTmp.minus(another).bigNum;
            cmp = thisTmp.compare(another);
        }
        return new BigInteger(BigInteger.cutUnmeaningZero(thisTmp.bigNum));
    }

    /**
     * Override toString function in Object Class
     */
    public String toString(){
        return this.bigNum;
    }

    public static void main(String[] args) {
        BigInteger a = new BigInteger("4000");
        BigInteger b = new BigInteger("1");
        System.out.println("Plus: " + a.plus(b));
        System.out.println("Minus: " + a.minus(b));
        System.out.println("Multiply :" + a.multiply(new BigInteger("4000")));
        System.out.println("DIV Divide :" + a.divideDiv(new BigInteger("7")));
        System.out.println("MOD Divide :" + a.divideMod(new BigInteger("7")));
    }
}
