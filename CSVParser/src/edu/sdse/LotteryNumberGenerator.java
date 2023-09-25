package edu.sdse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class LotteryNumberGenerator {
    // instance variable Random
    private Random random;

    // Constructor
    public LotteryNumberGenerator() {
        random = new Random();
    }

    // Method that generates a list of specified non-duplicate random integers
    public List<Integer> generate(int count, int maxRange) {
        List<Integer> lotteryNumbers = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int randomNumber = random.nextInt(maxRange) + 1;

            while (lotteryNumbers.contains(randomNumber)) {
                randomNumber = random.nextInt(maxRange) + 1;
            }

            lotteryNumbers.add(randomNumber);
        }

        Collections.sort(lotteryNumbers);

    return lotteryNumbers;
    }
    public static void main(String[] args) {
        // Creates a Generator object
        LotteryNumberGenerator numberGenerator = new LotteryNumberGenerator();

        // Calls its method
        List<Integer> lotteryNumbers = numberGenerator.generate(8, 36);

        // Prints out as specified
        System.out.print("Lottery Numbers: ");
        for (int i = 0; i < lotteryNumbers.size() - 1; i++) {
            System.out.print(lotteryNumbers.get(i) + " ");
        }

        System.out.print("_"+lotteryNumbers.get(7)+"_");
    }
}
