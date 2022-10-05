package kvbdev;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class TextCheckerTest {

    private TextChecker sut = new TextChecker();

    @ParameterizedTest
    @ValueSource(strings = {"a", "aa", "aba", "aabbaa", "abcba"})
    void isPalindrome_success(String text) {
        assertThat(sut.isPalindrome(text), is(true));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ab", "abc", "aacbaa"})
    void isPalindrome_failure(String text) {
        assertThat(sut.isPalindrome(text), is(false));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "aa", "bbb"})
    void hasOneLetter_success(String text) {
        assertThat(sut.hasOneLetter(text), is(true));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ab", "aba", "baa"})
    void hasOneLetter_failure(String text) {
        assertThat(sut.hasOneLetter(text), is(false));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "ab", "aabbcc"})
    void hasLetterIncreasion_success(String text) {
        assertThat(sut.hasLetterIncreasion(text), is(true));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ba", "ba", "aaaabca"})
    void hasLetterIncreasion_failure(String text) {
        assertThat(sut.hasLetterIncreasion(text), is(false));
    }
}