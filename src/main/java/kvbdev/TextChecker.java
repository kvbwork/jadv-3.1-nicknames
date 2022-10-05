package kvbdev;

public class TextChecker {

    public boolean isPalindrome(String text) {
        final char[] charArray = text.toCharArray();
        final int middle = charArray.length / 2;

        for (int i = 0; i < middle; i++) {
            if (charArray[i] != charArray[charArray.length - i - 1]) return false;
        }
        return true;
    }

    public boolean hasOneLetter(String text) {
        final char[] charArray = text.toCharArray();
        final char TEST_CHAR = charArray[0];
        final int START_INDEX = 1;

        for (int i = START_INDEX; i < charArray.length; i++) {
            if (TEST_CHAR != charArray[i]) return false;
        }
        return true;
    }

    public boolean hasLetterIncreasion(String text) {
        final char[] charArray = text.toCharArray();
        final int START_INDEX = 1;
        char lastChar = charArray[0];

        for (int i = START_INDEX; i < charArray.length; i++) {
            if (lastChar > charArray[i]) return false;
            lastChar = charArray[i];
        }
        return true;
    }

}
