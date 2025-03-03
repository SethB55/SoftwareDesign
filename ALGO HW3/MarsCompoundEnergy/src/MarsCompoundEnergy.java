import java.io.*;
import java.util.*;

public class MarsCompoundEnergy {
    static class Compound {
        int id;
        double C, N, O;

        public Compound(int id, double C, double N, double O) {
            this.id = id;
            this.C = C;
            this.N = N;
            this.O = O;
        }
    }

    // Function to read the input file and parse the compounds
    public static List<Compound> readInput(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        List<Compound> compounds = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            double C = Double.parseDouble(parts[1]);
            double N = Double.parseDouble(parts[2]);
            double O = Double.parseDouble(parts[3]);
            compounds.add(new Compound(id, C, N, O));
        }

        return compounds;
    }

    // Function to calculate the energy between two compounds
    public static double computeEnergy(Compound a, Compound b) {
        return Math.sqrt(Math.pow(a.C - b.C, 2) + Math.pow(a.N - b.N, 2) + Math.pow(a.O - b.O, 2));
    }

    // Function to find the closest pair of compounds
    public static int[] findClosestPair(List<Compound> compounds) {
        double minEnergy = Double.MAX_VALUE;
        int[] closestPair = new int[2];

        for (int i = 0; i < compounds.size(); i++) {
            for (int j = i + 1; j < compounds.size(); j++) {
                double energy = computeEnergy(compounds.get(i), compounds.get(j));
                if (energy < minEnergy) {
                    minEnergy = energy;
                    closestPair[0] = compounds.get(i).id;
                    closestPair[1] = compounds.get(j).id;
                }
            }
        }

        return closestPair;
    }

    // Main function
    public static void main(String[] args) throws IOException {
        List<Compound> compounds = readInput("/iahome/s/se/sebolen/IdeaProjects/sebolen_swd/ALGO HW3/MarsCompoundEnergy/input3.txt");
        int[] closestPair = findClosestPair(compounds);
        System.out.println("Closest pair of compounds: " + closestPair[0] + " and " + closestPair[1]);
    }
}
