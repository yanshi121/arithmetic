package com.ding.sort;

import java.util.Arrays;

public class sort {
    public static void main(String[] args) {
        orderingMethod orderingMethod = new orderingMethod();
        int[] array = new int[] {3, 4, 2, 1, 5, 6, 7, 8, 30, 50, 1, 33, 24, 5, 14, 7, 0};
        String[] array1 = new String[] {"qd", "abc", "qwe", "hhh", "a", "cws", "ope"};
//        orderingMethod.selectionSortStart(array);
//        System.out.println(Arrays.toString(array));
//        orderingMethod.insertSort(array);
//        System.out.println(Arrays.toString(array));
//        orderingMethod.shellSort(array);
//        System.out.println(Arrays.toString(array));
//        orderingMethod.mergeSort(array, 0, array.length - 1);
//        System.out.println(Arrays.toString(array));
//        System.out.println(Arrays.toString(orderingMethod.radixSort(array1, 3)));
//        orderingMethod.quickSort(array, 0, array.length - 1);
//        System.out.println(Arrays.toString(array));
    }
}
class orderingMethod{
    public void quickSort(int[] array, int left, int right){
        int l = left;
        int r = right;
        int pivot = array[(left + right) / 2];
        int temp = 0;
        while (l < r){
            while (array[l] < pivot){
                l += 1;
            }
            while (array[r] > pivot){
                r -= 1;
            }
            if (l >= r){
                break;
            }
            temp = array[l];
            array[l] = array[r];
            array[r] = temp;
            if (array[l] == pivot){
                r -= 1;
            }
            if (array[r] == pivot){
                l += 1;
            }
            if (l == r){
                l += 1;
                r -= 1;
            }
            if (left < r){
                quickSort(array, left, r);
            }
            if (right > l){
                quickSort(array, l, right);
            }
        }
    }
    public void selectionSortStart(int[] array){
        for (int i = 0; i < array.length - 1; i++){
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++){
                if (array[j] < array[minIndex]){
                    minIndex = j;
                }
            }
            if (i != minIndex){
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }
    public void insertSort(int[] array){
        for (int i = 1; i < array.length; i++){
            int insertValue = array[i];
            int j = i - 1;
            for (; (j >= 0) && (insertValue < array[j]); j--){
                array[j + 1] = array[j];
            }
            array[j + 1] = insertValue;
        }
    }
    public void shellSort(int[] array){
        int d = array.length;
        while (d > 1){
            d = d / 2;
            for (int x = 0; x < d; x++){
                for (int i = x + d; i < array.length; i = i + d){
                    int temp = array[i];
                    int j;
                    for (j = i - d; (j >= 0) && (array[j] > temp); j = j - d){
                        array[j + d] = array[j];
                    }
                    array[j + d] = temp;
                }
            }
        }
    }
    public void mergeSort(int[] array, int start, int end){
        if (start < end){
            int mid = (start + end) / 2;
            mergeSort(array, start, mid);
            mergeSort(array, mid + 1, end);
            merge(array, start, mid, end);
        }
    }
    public static void merge(int[] array, int start, int mid, int end){
        int[] tempArray = new int[end - start + 1];
        int p1 = start;
        int p2 = mid + 1;
        int p = 0;
        while ((p1 <= mid) && (p2 <= end)){
            if (array[p1] <= array[p2]){
                tempArray[p++] = array[p1++];
            }else {
                tempArray[p++] = array[p2++];
            }
        }
        while (p1 <= mid){
            tempArray[p++] = array[p1++];
        }
        while (p2 <= end){
            tempArray[p++] = array[p2++];
        }
        for (int i = 0; i < tempArray.length; i++){
            array[i + start] = tempArray[i];
        }
    }
    public static final int ASCII_RANGE = 128;
    public String[] radixSort(String[] array, int maxLength){
        String[] sortedArray = new String[array.length];
        for (int k = maxLength - 1; k >= 0; k--){
            int[] count = new int[ASCII_RANGE];
            for (int i = 0; i < array.length; i++){
                int index = getCharIndex(array[i], k);
                count[index]++;
            }
            for (int i = 1; i < count.length; i++){
                count[i] = count[i] + count[i - 1];
            }
            for (int i = array.length - 1; i >= 0; i--){
                int index = getCharIndex(array[i], k);
                int sortedIndex = count[index] - 1;
                sortedArray[sortedIndex] = array[i];
                count[index]--;
            }
            array = sortedArray.clone();
        }
        return array;
    }
    public static int getCharIndex(String str, int k){
        if (str.length() < (k + 1)){
            return 0;
        }
        return str.charAt(k);
    }
}