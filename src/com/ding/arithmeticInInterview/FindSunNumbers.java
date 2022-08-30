package com.ding.arithmeticInInterview;

import java.util.*;

public class FindSunNumbers {
    public static List<List<Integer>> twoSum(int[] numbs, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 1; i < numbs.length; i++) {
            map.put(numbs[i], i);
        }
        for (int i = 0; i < numbs.length; i++) {
            int other = target - numbs[i];
            if (map.containsKey(other) && map.get(other) != i) {
                resultList.add(Arrays.asList(i, map.get(other)));
                map.remove(numbs[i]);
            }
        }
        return resultList;
    }

    public static List<List<Integer>> twoSumV2(int[] numbs, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < numbs.length; i++) {
            int other = target - numbs[i];
            if (map.containsKey(other)) {
                resultList.add(Arrays.asList(map.get(other), i));
            }
            map.put(numbs[i], i);
        }
        return resultList;
    }

    public static List<List<Integer>> threeSum(int[] numbs, int target) {
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < numbs.length; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            int d1 = target - numbs[i];
            for (int j = i + 1; j < numbs.length; j++) {
                int d2 = d1 - numbs[j];
                if (map.containsKey(d2)) {
                    resultList.add(Arrays.asList(numbs[i], d2, numbs[j]));
                }
                map.put(numbs[j], j);
            }
        }
        return resultList;
    }

    public static List<List<Integer>> threeSumV2(int[] numbs, int target) {
        Arrays.sort(numbs);
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        for (int i = 0; i < numbs.length; i++) {
            int d = target - numbs[i];
            for (int j = i + 1, k = numbs.length - 1; j < numbs.length; j++) {
                while (j < k && (numbs[j] + numbs[k]) > d) {
                    k--;
                }
                if (j == k) {
                    break;
                }
                if (numbs[j] + numbs[k] == d) {
                    List<Integer> list = Arrays.asList(numbs[i], numbs[j], numbs[k]);
                    resultList.add(list);
                }
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        int[] numbs = {5, 12, 6, 3, 9, 2, 1, 7};
        List<List<Integer>> resultList1 = twoSum(numbs, 13);
        for (List<Integer> list : resultList1) {
            System.out.println(Arrays.toString(list.toArray()));
        }
        List<List<Integer>> resultList2 = twoSum(numbs, 13);
        for (List<Integer> list : resultList2) {
            System.out.println(Arrays.toString(list.toArray()));
        }
        List<List<Integer>> resultList3 = threeSum(numbs, 13);
        for (List<Integer> list : resultList3) {
            System.out.println(Arrays.toString(list.toArray()));
        }
        List<List<Integer>> resultList4 = threeSumV2(numbs, 13);
        for (List<Integer> list : resultList4) {
            System.out.println(Arrays.toString(list.toArray()));
        }
    }
}
