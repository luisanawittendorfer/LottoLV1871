import java.util.*;

public class LottoLV1871 {

    public static void main(String[] args) {
        int[] fixedNumbers = {23, 12, 36, 4, 15, 46}; // Fixed numbers
        
        int simulations = 1;       // Quick test
        int weeksPerYear = 52;

        // Run simulations for each scenario
        System.out.println("Test with " + simulations + " win:");
        runSimulation(fixedNumbers, simulations, weeksPerYear);
    }

    private static void runSimulation(int[] fixedNumbers, int simulations, int weeksPerYear) {
        System.out.println("fixedNumbers " + Arrays.toString(fixedNumbers) + " simulation.");
        long yearsWithFixedNumbers = simulateLotto(fixedNumbers, simulations, weeksPerYear);
        System.out.println("Average years to win 1 time with the fixed numbers: " + yearsWithFixedNumbers / (double) simulations);

        long yearsWithRandomNumbers = simulateLotto(null, simulations, weeksPerYear);
        System.out.println("Average years to win 1 time with random numbers: " + yearsWithRandomNumbers / (double) simulations);
    }

    private static long simulateLotto(int[] fixedNumbers, int simulations, int weeksPerYear) {
        Random rand = new Random();
        long totalYears = 0;

        for (int i = 0; i < simulations; i++) {
            int[] tipNumbers;
            if (fixedNumbers != null) {
                tipNumbers = fixedNumbers;
            } else {
                tipNumbers = generateRandomNumbers(rand);
            }

            long weeks = 0;
            while (true) {
                weeks++;
                int[] drawnNumbers = generateRandomNumbers(rand);
                if (checkWin(tipNumbers, drawnNumbers)) {
                    System.out.println("Simulation " + (i + 1) + ": Won after " + weeks + " weeks.");
                    break;
                }
                if (fixedNumbers == null) {

                    tipNumbers = generateRandomNumbers(rand);
                }
            }
            totalYears += (weeks / weeksPerYear);
        }
        return totalYears;
    }

    private static int[] generateRandomNumbers(Random rand) {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < 6) {
            numbers.add(rand.nextInt(49) + 1);
        }
        int[] result = new int[6];
        int index = 0;
        for (int number : numbers) {
            result[index++] = number;
        }
        return result;
    }

    private static boolean checkWin(int[] tipNumbers, int[] drawnNumbers) {
        Set<Integer> tipSet = new HashSet<>();
        for (int number : tipNumbers) {
            tipSet.add(number);
        }
        int matchCount = 0;
        for (int number : drawnNumbers) {
            if (tipSet.contains(number)) {
                matchCount++;
            }
        }
        return matchCount == 6;
    }
}