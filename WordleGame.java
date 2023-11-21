import java.util.Random;
import java.util.Scanner;

public class WordleGame {
    private static final String[] WORDS = { "apple", "beach", "chair", "dance", "eagle", "field", "ghost", "heart",
            "igloo", "joker", "knife",
            "lemon", "music", "night", "ocean", "piano", "queen", "river", "shark", "tiger", "uncle", "virus", "whale",
            "xerox", "yacht", "zebra",
            "alarm", "blink", "cloud", "dream", "fairy", "grape", "honey", "ivory", "jolly", "kayak", "lucky", "mango",
            "nudge", "orbit", "peach",
            "quick", "storm", "table", "unity", "venom", "wagon", "xenon", "yodel", "zesty", "amber", "blaze", "chime",
            "ember", "flame", "glide",
            "hyena", "icing", "jewel", "koala", "maple", "novel", "olive", "quirk", "roset", "snail", "tulip", "unity",
            "waltz", "xenon", "zebra",
            "alloy", "black", "charm", "delta", "eagle", "flora", "grail", "happy", "ivory", "jolly", "karma", "lemon",
            "magic", "nexus", "opera",
            "prism", "quick", "raven", "smile", "tiger", "unity", "vocal", "waltz" };
    private static final int MAX_ATTEMPTS = 6;

    public static void main(String[] args) {
        Random random = new Random();
        String hiddenWord = WORDS[random.nextInt(WORDS.length)];
        char[] wordToGuess = hiddenWord.toCharArray();
        boolean[] guessed = new boolean[5];

        System.out.println("Welcome to Wordle!");
        System.out.println("You have 6 attempts to guess a 5-letter word.");
        System.out.println("If the letters are in correct position then it is in \033[0;32m green \033[0m ");
        System.out
                .println("If the letters matches\033[0;33m yellow \033[0m  if the letter matches with the final word");
        System.out.println("Good luck!");

        Scanner scanner = new Scanner(System.in);

        int attempts = 0;

        while (attempts < MAX_ATTEMPTS) {
            System.out.println("Attempt " + (attempts + 1));
            System.out.print("Enter your guess (5 letters): ");
            String guess = scanner.nextLine();

            if (guess.length() != 5) {
                System.out.println("Please enter a 5-letter word.");
                continue;
            }

            char[] guessedWord = guess.toCharArray();
            boolean[] correctLetters = new boolean[5];

            for (int i = 0; i < 5; i++) {
                if (wordToGuess[i] == guessedWord[i]) {
                    correctLetters[i] = true;
                    guessed[i] = true;
                }
            }

            for (int i = 0; i < 5; i++) {
                if (correctLetters[i]) {
                    System.out.print("\033[0;32m");
                } else if (containsLetter(guessedWord[i], wordToGuess)) {
                    System.out.print("\033[0;33m");
                }

                System.out.print(guessedWord[i]);
                System.out.print("\033[0m");
            }
            
            System.out.println();

            if (isWordGuessed(guessed)) {
                System.out.println("Congratulations! You guessed the word: " + hiddenWord);
                break;
            } else {
                System.out.println("Try again.");
                attempts++;
            }
        }

        if (attempts == MAX_ATTEMPTS) {
            System.out.println("Out of attempts! The word was: " + hiddenWord);
        }

        scanner.close();
    }

    private static boolean containsLetter(char letter, char[] word) {
        for (char c : word) {
            if (c == letter) {
                return true;
            }
        }
        return false;
    }

    private static boolean isWordGuessed(boolean[] guessed) {
        for (boolean b : guessed) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
}