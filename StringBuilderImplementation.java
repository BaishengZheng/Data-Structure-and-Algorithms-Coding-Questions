import java.io.*;
import java.util.*;

class Solution {
    public static void main(String[] args) {
        MyStringBuilder sb = new MyStringBuilder();
        sb.append("abcdefghij");
        System.out.println(sb.toString());
        sb.append("123456789101112");
        System.out.println(sb.toString());
    }  
}

class MyStringBuilder {
    public static final int initialCapacity = 10;
    public static final double factor = 1.5;
    private int capacity;
    private int index; // Position for next appended char
    private char[] array;

    public MyStringBuilder() {
        this.capacity = MyStringBuilder.initialCapacity;
        this.array = new char[this.capacity];
        this.index = 0;
    }

    public void append(String str) {
        if (str == null) {
            return;
        }

        if (this.isOverFlowAfterAppend(str)) {
            this.resize(index + str.length() - 1);
        }

        for (int i = 0; i < str.length(); i++, index++) {
            array[index] = str.charAt(i);
        }
    }

    private boolean isOverFlowAfterAppend(String str) {
        if (str == null) return false;
        return index + str.length() > this.array.length;// The last char cannot be place into array
    }

    private void resize(int smallestSize) {
        // smallestSize for array to cover the string after appended.
        int newSize = array.length;
        do {
            newSize = (int)(newSize * MyStringBuilder.factor);
        } while (newSize < smallestSize);
              
        char[] newArray = new char[newSize];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        // Update array reference
        array = newArray;
    }

    public String toString() {
        return new String(array, 0, index);
    }
}
