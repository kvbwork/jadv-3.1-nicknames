package kvbdev;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {
    public static final Random random = new Random();
    public static TextChecker textChecker = new TextChecker();
    public static final AtomicInteger niceWord3 = new AtomicInteger();
    public static final AtomicInteger niceWord4 = new AtomicInteger();
    public static final AtomicInteger niceWord5 = new AtomicInteger();

    public static void main(String[] args) {

        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        awaitNiceWordsCount(texts, List.of(
                textChecker::isPalindrome,
                textChecker::hasOneLetter,
                textChecker::hasLetterIncreasion
        ));

        System.out.println("Красивых слов с длиной 3: " + niceWord3.get() + " шт");
        System.out.println("Красивых слов с длиной 4: " + niceWord4.get() + " шт");
        System.out.println("Красивых слов с длиной 5: " + niceWord5.get() + " шт");
    }

    public static void awaitNiceWordsCount(String[] texts, List<Predicate<String>> textChecks) {
        ThreadGroup textWorkersGroup = new ThreadGroup("TextWorkers");

        textChecks.stream()
                .forEach(textCheck ->
                        new Thread(textWorkersGroup, () ->
                                Stream.of(texts)
                                        .filter(textCheck)
                                        .forEach(Main::countNiceWord)
                        ).start()
                );

        awaitShutdown(textWorkersGroup);
    }

    public static void countNiceWord(String text) {
        switch (text.length()) {
            case 3:
                niceWord3.getAndIncrement();
                break;
            case 4:
                niceWord4.getAndIncrement();
                break;
            case 5:
                niceWord5.getAndIncrement();
                break;
            default:
                throw new UnsupportedOperationException("can't found counter for word length " + text.length());
        }
    }

    public static String generateText(String letters, int length) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    protected static void awaitShutdown(ThreadGroup threadGroup) {
        try {
            Thread[] threads = new Thread[1];
            while (threadGroup.activeCount() > 0) {
                threadGroup.enumerate(threads, false);
                threads[0].join();
            }
        } catch (InterruptedException ex) {
        }
    }

}
