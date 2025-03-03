//
//import java.io.*;
//import java.util.*;
//
//public class StockProfit {
//    public static void main(String[] args) {
//        String filename = "stocks.txt";
//        try (BufferedReader reader = new BufferedReader(new FileReader("/iahome/s/se/sebolen/IdeaProjects/sebolen_swd/StockProfit/src/stocks.txt"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                int[] prices = Arrays.stream(line.split(","))
//                        .mapToInt(Integer::parseInt)
//                        .toArray();
//                int maxProfit = maxProfit(prices, 0, prices.length - 1);
//                System.out.println(maxProfit);
//            }
//        } catch (IOException e) {
//            System.err.println("Error reading file: " + e.getMessage());
//        }
//    }
//
//    private static int maxProfit(int[] prices, int left, int right) {
//        if (left >= right) {
//            return 0; // No profit possible with one or zero prices
//        }
//
//        int mid = (left + right) / 2;
//
//        // Max profit in left half
//        int leftProfit = maxProfit(prices, left, mid);
//
//        // Max profit in right half
//        int rightProfit = maxProfit(prices, mid + 1, right);
//
//        // Max crossing profit
//        int minLeft = Integer.MAX_VALUE;
//        for (int i = left; i <= mid; i++) {
//            minLeft = Math.min(minLeft, prices[i]);
//        }
//
//        int maxRight = Integer.MIN_VALUE;
//        for (int i = mid + 1; i <= right; i++) {
//            maxRight = Math.max(maxRight, prices[i]);
//        }
//
//        int crossProfit = maxRight - minLeft;
//
//        return Math.max(leftProfit, Math.max(rightProfit, crossProfit));
//    }
//}