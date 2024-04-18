import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TextAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter a paragraph or a lengthy text:");
        String userInput = scanner.nextLine();

        System.out.println("You entered: " + userInput);

        // Step 2: Character Count
        int characterCount = userInput.length();
        System.out.println("Character count: " + characterCount);

        // Step 3: Word Count
        String[] words = userInput.split("\\s+");
        int wordCount = words.length;
        System.out.println("Word count: " + wordCount);

        // Step 4: Most Common Character
        char mostCommonChar = findMostCommonChar(userInput);
        System.out.println("Most common character: " + mostCommonChar);

        // Step 5: Character Frequency
        char targetChar = readValidCharacter(scanner);
        int frequency = countCharacterFrequency(userInput, targetChar);
        System.out.println("Frequency of '" + targetChar + "': " + frequency);

        // Step 6: Word Frequency
        String targetWord = readValidWord(scanner);
        int wordFrequency = countWordFrequency(userInput, targetWord);
        System.out.println("Frequency of '" + targetWord + "': " + wordFrequency);

        // Step 7: Unique Words
        int uniqueWordCount = countUniqueWords(userInput);
        System.out.println("Number of unique words: " + uniqueWordCount);

        scanner.close();

        // We've completed all the tasks
    }

    private static char findMostCommonChar(String str) {
        int[] frequency = new int[256]; // Assuming ASCII characters

        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) {
                frequency[Character.toLowerCase(c)]++;
            }
        }

        char mostCommonChar = '\0';
        int maxFrequency = 0;
        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] > maxFrequency) {
                mostCommonChar = (char) i;
                maxFrequency = frequency[i];
            }
        }

        return mostCommonChar;
    }

    private static int countCharacterFrequency(String str, char targetChar) {
        int frequency = 0;
        for (char c : str.toCharArray()) {
            if (Character.toLowerCase(c) == Character.toLowerCase(targetChar)) {
                frequency++;
            }
        }
        return frequency;
    }

    private static int countWordFrequency(String str, String targetWord) {
        int frequency = 0;
        String[] words = str.split("\\s+");
        for (String word : words) {
            if (word.equalsIgnoreCase(targetWord)) {
                frequency++;
            }
        }
        return frequency;
    }

    private static int countUniqueWords(String str) {
        Set<String> uniqueWords = new HashSet<>();
        String[] words = str.split("\\s+");
        for (String word : words) {
            uniqueWords.add(word.toLowerCase()); // Convert to lowercase for case-insensitivity
        }
        return uniqueWords.size();
    }

    private static char readValidCharacter(Scanner scanner) {
        char targetChar = '\0'; // Initialize with a default value
        boolean isValidInput = false;
        do {
            System.out.println("Please enter a character to check its frequency:");
            String input = scanner.next();
            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                targetChar = Character.toLowerCase(input.charAt(0));
                isValidInput = true;
            } else {
                System.out.println("Invalid input. Please enter a single character.");
            }
        } while (!isValidInput);
        return targetChar;
    }

    private static String readValidWord(Scanner scanner) {
        String targetWord;
        boolean isValidInput = false;
        do {
            System.out.println("Please enter a word to check its frequency:");
            targetWord = scanner.next();
            if (targetWord.matches("[a-zA-Z]+")) {
                isValidInput = true;
            } else {
                System.out.println("Invalid input. Please enter a valid word containing only letters.");
            }
        } while (!isValidInput);
        return targetWord.toLowerCase();
    }
}
