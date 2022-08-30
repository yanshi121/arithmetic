package com.ding.arithmeticInInterview;

public class StockProfit {
    public static int maxProfitFor1Time(int[] prices){
        if (prices == null || prices.length == 0){
            return 0;
        }
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++){
            if (prices[i] < minPrice){
                minPrice = prices[i];
            }else if(prices[i] - minPrice > maxProfit){
                maxProfit = prices[i] -minPrice;
            }
        }
        return maxProfit;
    }
    public static int maxProfitForAnyTime(int[] prices){
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++){
            if (prices[i] > prices[i - 1]){
                maxProfit += prices[i] - prices[i -1];
            }
        }
        return maxProfit;
    }
    public static int MAX_DEAL_TIMES = 2;
    public static int MaxProfitFor2Time(int[] prices){
        if (prices == null || prices.length == 0){
            return 0;
        }
        int n = prices.length;
        int m = MAX_DEAL_TIMES * 2 + 1;
        int[][] resultTable = new int[n][m];
        resultTable[0][1] = -prices[0];
        resultTable[0][3] = -prices[0];
        for (int i = 1; i < n; ++i){
            for (int j = 1; j < m; j++){
                if ((j & 1) == 1){
                    resultTable[i][j] = Math.max(resultTable[i - 1][j], resultTable[i - 1][j - 1] - prices[i]);
                }else {
                    resultTable[i][j] = Math.max(resultTable[i - 1][j], resultTable[i -1][j - 1] + prices[i]);
                }
            }
        }
        return resultTable[n - 1][m -1];
    }
    private static int MAX_DEAL_TIMES2 = 2;
    public static  int maxProfitFor2TimeV2(int[] prices){
        if (prices == null || prices.length == 0){
            return 0;
        }
        int n = prices.length;
        int m = MAX_DEAL_TIMES2 * 2 + 1;
        int[] resultTable = new int[m];
        resultTable[1] = -prices[0];
        resultTable[3] = -prices[0];
        for (int i = 1; i < n; ++i){
            for (int j = 1; j < m; j++){
                if ((j & 1) == 1){
                    resultTable[j] = Math.max(resultTable[j], resultTable[j - 1]-prices[i]);
                }else {
                    resultTable[j] = Math.max(resultTable[j], resultTable[j - 1]+prices[i]);
                }
            }
        }
        return resultTable[m - 1];
    }
    public static int maxProfitForKTime(int[] prices, int k){
        if (prices == null || prices.length == 0){
            return 0;
        }
        int n = prices.length;
        int m = k * 2 + 1;
        int[] resultTable = new int[m];
        resultTable[1] = -prices[0];
        resultTable[3] = -prices[0];
        for (int i = 1; i < n; ++i){
            for (int j = 1; j < m; j++){
                if ((j & 1) == 1){
                    resultTable[j] = Math.max(resultTable[j], resultTable[j - 1]-prices[i]);
                }else {
                    resultTable[j] = Math.max(resultTable[j], resultTable[j - 1]+prices[i]);
                }
            }
        }
        return resultTable[m - 1];
    }
    public static void main(String[] args) {
        int[] prices = {9, 2, 7, 4, 3, 1, 8, 4};
        System.out.println(maxProfitFor1Time(prices));
        System.out.println(maxProfitForAnyTime(prices));
        System.out.println(MaxProfitFor2Time(prices));
        System.out.println(maxProfitFor2TimeV2(prices));
        System.out.println(maxProfitForKTime(prices, 2));
    }
}
