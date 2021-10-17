package ru.dataart.academy.java;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.iterators.ReverseListIterator;

import java.util.*;

public class Calculator {
    /**
     * @param firstNumber  - list for first number in reversed order (1 - 2 - 3 -> 321)
     * @param secondNumber - list for second number in reversed order (1 - 2 - 3 -> 321)
     * @return - sum of firstNumber + secondNumber
     */
    public Integer getSum(List<Integer> firstNumber, List<Integer> secondNumber) {
        //Task implementation

        int result = 0;

        // Using Collections.reverse() or Guava's Lists.reverse() and Iterator<T>
        result = getSumWithIterator(firstNumber, secondNumber);

        // Using Java Collections Framework ListIterator<T>
        result = getSumWithListIterator(firstNumber, secondNumber);

        // Using Apache's commons-collections4 ReverseListIterator
        result = getSumWithReversedIterator(firstNumber, secondNumber);

        return result;
    }

    /**
     *
     * @param list - list of elements
     * @param <T> - type of element
     * @return - odd list elements
     * Example: [1, 22, 34] -> [1, 34]
     */
    public <T> List<T> getOddElements(List<T> list) {
        // Task implementation
        return Collections.EMPTY_LIST;
    }

    /**
     *
     * @param list - list of elements
     * @param <T> - type of element
     * @return - first and last elements of the list
     * Example: [1, 2, 3] -> [1, 3]
     * [1, 2, 3 , 4] -> [1, 4]
     */
    public <T> List<T> getBounds(List<T> list) {
        // Task implementation
        return Collections.EMPTY_LIST;
    }

    //==================================================================================================================
    // Uses additional collections to save original ones in reversed order
    private int getSumWithIterator(List<Integer> firstNumber, List<Integer> secondNumber) {
        int num1 = 0;
        int num2 = 0;

        // A basic Iterator only moves forward, so the collections are needed to be reversed
        // The problem is that it's necessary to create new collections

        // Using Collections.reverse()
//        List<Integer> firstReversed = new ArrayList<>(firstNumber);
//        Collections.reverse(firstReversed);
//
//        List<Integer> secondReversed = new ArrayList<>(secondNumber);
//        Collections.reverse(secondReversed);

        // Using Guava's Lists.reverse()
        Iterator<Integer> iter = Lists.reverse(firstNumber).iterator();
        while(iter.hasNext()) {
            num1 = num1*10 + iter.next();
        }
        iter = Lists.reverse(secondNumber).iterator();
        while (iter.hasNext()) {
            num2 = num2*10 + iter.next();
        }

        // I like to use Idea's "iter" autocompletion, but it's not representative in terms of taking lessons
//        for (Integer integer : firstList) {
//            num1 = num1*10 + integer;
//        }
//        for (Integer integer : secondList) {
//            num2 = num2*10 + integer;
//        }
        return num1 + num2;
    }

    // Uses ListIterator to walk over collections backwards
    private int getSumWithListIterator(List<Integer> firstNumber, List<Integer> secondNumber) {
        int num1 = 0;
        int num2 = 0;

        // Fortunately a ListIterator can move backward that is very useful in this case
        // and this code can work with more abstract List<E> type
        // and there is no need to create additional collections
        ListIterator<Integer> liter = firstNumber.listIterator(firstNumber.size());
        while(liter.hasPrevious()) {
            num1 = num1*10 + liter.previous();
        }

        liter = secondNumber.listIterator(secondNumber.size());
        while(liter.hasPrevious()) {
            num2 = num2*10 + liter.previous();
        }

        return num1 + num2;
    }

    // Uses Apache's commons-collections4 ReverseListIterator to walk over collections backwards
    private int getSumWithReversedIterator(List<Integer> firstNumber, List<Integer> secondNumber) {
        int num1 = 0;
        int num2 = 0;

        ReverseListIterator<Integer> reverseIter = new ReverseListIterator<>(firstNumber);
        while(reverseIter.hasNext()) {
            num1 = num1*10 + reverseIter.next();
        }

        reverseIter = new ReverseListIterator<>(secondNumber);
        while(reverseIter.hasNext()) {
            num2 = num2*10 + reverseIter.next();
        }

        return num1 + num2;
    }

}
