package ru.dataart.academy.java;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.iterators.ReverseListIterator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Calculator {
    private int predicateCounter = 0;

    /**
     * @param firstNumber  - list for first number in reversed order (1 - 2 - 3 -> 321)
     * @param secondNumber - list for second number in reversed order (1 - 2 - 3 -> 321)
     * @return - sum of firstNumber + secondNumber
     */
    public Integer getSum(List<Integer> firstNumber, List<Integer> secondNumber) {
        //Task implementation

        int result;

        // Using Collections.reverse() or Guava's Lists.reverse() and Iterator<T>
//        result = getSumWithIterator(firstNumber, secondNumber);

        // Using Java Collections Framework ListIterator<T>
//        result = getSumWithListIterator(firstNumber, secondNumber);

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

        if (list == null) {
            return null; // :D
        }

        if (list.size() == 0) {
            return Collections.emptyList();
        }

        if (list.size() == 1) {
            return list;
        }

        List<T> oddElements;

        // Java Collection framework
//        oddElements = getOddsWithIterator(list);
        oddElements = getOddsWithListIterator(list);

        return oddElements;
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
        if (list.size() == 0) {
            return Collections.emptyList();
        }

        List<T> boundsList = new ArrayList<>();

        ListIterator<T> iterator = list.listIterator();

        // Adding a first element
        boundsList.add(iterator.next());
        if (list.size() == 1) {
            // If an original list contains only one element than result list also does
            // considering the tests
            return boundsList;
        }

        // Adding the last element
        iterator = list.listIterator(list.size());
        boundsList.add(iterator.previous());

        return boundsList;
    }





    // It would be better to create a class for every task but it was obligated to put everything in Calculator

//=getSum=====================================================================================================================================================
    // Uses additional collections to save original ones in reversed order
    private int getSumWithIterator(List<Integer> firstNumber, List<Integer> secondNumber) {
        int num1 = 0;
        int num2 = 0;

        // A basic Iterator only moves forward, so the collections are needed to be reversed
        // The problem is that it's necessary to create new collections

        // Using Guava's Lists.reverse() (also Collections.reverse(firstReversed);)
//        Iterator<Integer> iter = Lists.reverse(firstNumber).iterator();
//        while(iter.hasNext()) {
//            num1 = num1*10 + iter.next();
//        }
//        iter = Lists.reverse(secondNumber).iterator();
//        while (iter.hasNext()) {
//            num2 = num2*10 + iter.next();
//        }

        for (Integer integer : Lists.reverse(firstNumber)) {
            num1 = num1*10 + integer;
        }
        for (Integer integer : Lists.reverse(secondNumber)) {
            num2 = num2*10 + integer;
        }

        return num1 + num2;
    }

    // Uses ListIterator to walk over collections backwards
    private int getSumWithListIterator(List<Integer> firstNumber, List<Integer> secondNumber) {
        int num1 = 0;
        int num2 = 0;

        // This code can work with more abstract List<E> type
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

//=getOddElements=============================================================================================================================================
    // Uses a basic iterator and outer index
    private <T> List<T> getOddsWithIterator(List<T> list) {
        List<T> oddElements = new ArrayList<>(list.size()/2 + 1);

        // Copying odd elements (where "1" element has index 0) to an empty ArrayList
        Iterator<T> iterator = list.iterator();
        int i = 1;
        while (iterator.hasNext()) {
            if (i%2 != 0) {
                oddElements.add(iterator.next());
            } else {
                iterator.next();
            }
            i++;
        }

        return oddElements;
    }

    // Uses a list iterator and its index
    private <T> List<T> getOddsWithListIterator(List<T> list) {
        List<T> oddElements = new ArrayList<>();

        // Adding all odd elements (where "1" element has index 0)
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex()%2 == 0) {
                oddElements.add(iterator.next());
            } else {
                iterator.next();
            }
        }

        return oddElements;
    }
}