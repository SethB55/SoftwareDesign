import java.io.*;
import java.util.*;

public class RaceFinishAnalysis {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/iahome/s/se/sebolen/IdeaProjects/sebolen_swd/ALGO HW3/RaceFinishAnalysis/input4.txt"));
        String[] data = br.readLine().split(",");
        br.close();

        int k = Integer.parseInt(data[0]);
        String targetTime = data[1];
        List<String> times = Arrays.asList(Arrays.copyOfRange(data, 2, data.length));

        int count = countRunnersUnderX(times, targetTime);

        System.out.println(count == k ? "OK" : "problem");
    }

    private static int countRunnersUnderX(List<String> times, String targetTime) {
        int left = 0, right = times.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (times.get(mid).compareTo(targetTime) < 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left; // Number of runners who finished under targetTime
    }
}
