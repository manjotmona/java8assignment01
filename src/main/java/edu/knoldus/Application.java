package edu.knoldus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by pallavi on 4/3/18.
 */
public class Application {

  public static Boolean isPrime(int num) {
    if (num == 1) {
      return false;
    } else {
      for (int i = 2; i <= num / 2; i++) {
        if (num % i == 0) {
          return false;
        }
      }
      return true;
    }
  }

  public static void main(String[] args) {

    //Question 1
    Random random = new Random();
    random.ints(100, 1, 500).filter(x -> isPrime(x)).forEach(System.out::println);

    //Question 2

    try {
      Path path = Paths.get("src/main/resources/file.txt");
      Map<String, Long> wordCount =
          Files.lines(path).flatMap(line -> Arrays.stream(line.trim().split(" ")))
              .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase().trim())
              .filter(word -> word.length() > 0).map(word -> new AbstractMap.SimpleEntry<>(word, 1))
              .collect(groupingBy(AbstractMap.SimpleEntry::getKey, counting()));

      wordCount.forEach((k, v) -> System.out.println(String.format("%s ==>> %d", k, v)));
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Question 3

    String sentence = "My name is Deepak Deepak is my name.";
    Map<String, Long> wordCount = Pattern.compile(" ").splitAsStream(sentence)
        .map(s -> s.replaceAll("[^a-zA-Z]", "").toUpperCase())
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    System.out.println(wordCount);

    //Question 4

    int[] list1 = { 1, 2, 3, 4, 5 };
    int[] list2 = { 1, 2, 3, 4, 5 };

    IntStream.range(0, 4).map(x -> list1[x] + list2[x]).forEach(System.out::println);

    //Question 5

    List<Movie> movies = new ArrayList<Movie>(5);
    movies.add(new Movie("Dangal", 2016, 9, Genre.SCIFI));
    movies.add(new Movie("Shivay", 2017, 9, Genre.COMEDY));
    movies.add(new Movie("Black Panther", 2018, 4, Genre.SUSPENSE));
    movies.add(new Movie("Padmavat", 2018, 2, Genre.ROMANCE));
    movies.add(new Movie("Agneepath", 2014, 9, Genre.COMEDY));

    movies.stream().filter(x -> x.rating > 8 && x.genre == Genre.COMEDY)
        .forEach(System.out::println);
    movies.stream().filter(x -> x.rating < 8 && x.releaseYear > 2000).forEach(System.out::println);
    movies.stream().filter(x -> x.rating % 2 == 0).forEach(System.out::println);
    movies.stream().filter(x -> x.rating == 7 && x.genre == Genre.SCIFI)
        .forEach(System.out::println);

  }
}