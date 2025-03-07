import java.io.*;
import java.util.*;

class Run {
    int startTime, endTime, pace;
    public Run(int startTime, int endTime, int pace) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.pace = pace;
    }
}

class Event implements Comparable<Event> {
    int time, pace;
    boolean isStart;
    public Event(int time, int pace, boolean isStart) {
        this.time = time;
        this.pace = pace;
        this.isStart = isStart;
    }

    @Override
    public int compareTo(Event other) {
        return Integer.compare(this.time, other.time);
    }
}

public class RunningPaces {

    public static void main(String[] args) throws IOException {
        String fileName = "/iahome/s/se/sebolen/IdeaProjects/sebolen_swd/Algo HW2/Question3/src/running.txt";
        List<Run> runs = readRunsFromFile(fileName);
        List<Event> events = generateEvents(runs);

        // Sort events by time, ensuring correct ordering
        Collections.sort(events);

        // Store results
        List<String> results = new ArrayList<>();
        results.add("(0, U)");  // Initial undefined pace

        // Use divide and conquer to track pace changes
        trackPaceChanges(events, 0, events.size() - 1, results);

        // Output results
        for (String result : results) {
            System.out.print(result + " ");
        }
    }

    public static List<Run> readRunsFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        List<Run> runs = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            for (String part : parts) {
                String[] values = part.replace("(", "").replace(")", "").split(",");
                int startTime = Integer.parseInt(values[0]);
                int endTime = Integer.parseInt(values[1]);
                int pace = Integer.parseInt(values[2]);
                runs.add(new Run(startTime, endTime, pace));
            }
        }
        reader.close();
        return runs;
    }

    public static List<Event> generateEvents(List<Run> runs) {
        List<Event> events = new ArrayList<>();
        for (Run run : runs) {
            events.add(new Event(run.startTime, run.pace, true));  // Start event
            events.add(new Event(run.endTime, run.pace, false));   // End event
        }
        return events;
    }

    public static void trackPaceChanges(List<Event> events, int left, int right, List<String> results) {
        if (left == right) {
            Event event = events.get(left);
            results.add("(" + event.time + ", " + (event.isStart ? event.pace : "U") + ")");
            return;
        }

        int mid = (left + right) / 2;
        List<String> leftResults = new ArrayList<>();
        List<String> rightResults = new ArrayList<>();

        trackPaceChanges(events, left, mid, leftResults);
        trackPaceChanges(events, mid + 1, right, rightResults);

        mergeResults(leftResults, rightResults, results);
    }

    public static void mergeResults(List<String> leftResults, List<String> rightResults, List<String> results) {
        int i = 0, j = 0;
        Integer lastPace = null;

        while (i < leftResults.size() && j < rightResults.size()) {
            String leftEntry = leftResults.get(i);
            String rightEntry = rightResults.get(j);

            int leftTime = Integer.parseInt(leftEntry.split(",")[0].replace("(", "").trim());
            int rightTime = Integer.parseInt(rightEntry.split(",")[0].replace("(", "").trim());

            String entry;
            if (leftTime < rightTime) {
                entry = leftEntry;
                i++;
            } else {
                entry = rightEntry;
                j++;
            }

            Integer currentPace = entry.contains("U") ? null : Integer.parseInt(entry.split(",")[1].replace(")", "").trim());
            if (!Objects.equals(lastPace, currentPace)) {
                results.add(entry);
                lastPace = currentPace;
            }
        }

        while (i < leftResults.size()) {
            String entry = leftResults.get(i++);
            Integer currentPace = entry.contains("U") ? null : Integer.parseInt(entry.split(",")[1].replace(")", "").trim());
            if (!Objects.equals(lastPace, currentPace)) {
                results.add(entry);
                lastPace = currentPace;
            }
        }

        while (j < rightResults.size()) {
            String entry = rightResults.get(j++);
            Integer currentPace = entry.contains("U") ? null : Integer.parseInt(entry.split(",")[1].replace(")", "").trim());
            if (!Objects.equals(lastPace, currentPace)) {
                results.add(entry);
                lastPace = currentPace;
            }
        }
    }
}