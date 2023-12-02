package days.day1;

import days.Day;

import java.util.*;

public class Day1 extends Day {

    public Day1() {
        subdir = "day1";
        readData();
    }

    @Override
    public void execute() {
        //Tenemos las líneas en calibrationData


        List<Character> numbers= Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        HashMap<String, String> words = new HashMap<>();
        words.put("zero", "0");
        words.put("one", "1");
        words.put("two", "2");
        words.put("three", "3");
        words.put("four", "4");
        words.put("five", "5");
        words.put("six", "6");
        words.put("seven", "7");
        words.put("eight", "8");
        words.put("nine", "9");

        HashMap<String, String> problematicWords = new HashMap<>();
        problematicWords.put("zerone", "zeroone");
        problematicWords.put("oneight", "oneeight");
        problematicWords.put("twone", "twoone");
        problematicWords.put("threeight", "threeeight");
        problematicWords.put("fiveight", "fiveeight");
        problematicWords.put("sevenine", "sevennine");
        problematicWords.put("eightwo", "eighttwo");
        problematicWords.put("eighthree", "eightthree");
        problematicWords.put("nineight", "nineeight");

        data = data.stream().map(line->{

            List<String> problematicKeys = problematicWords.keySet().stream().toList();
            for(String key :problematicKeys) {
                line = line.replace(key, problematicWords.get(key));
            }

            List<String> keys = words.keySet().stream().toList();
            for(String key :keys) {
                line = line.replace(key, words.get(key));
            }

            return line;
                }
        ).toList();

        List<Integer> calibrationNumbers = data.stream().map(line -> {

            Character initial = null;
            Character ending = null;

            for (int i = 0; i<line.length(); i++){
                char ch = line.charAt(i);
                if (numbers.contains(ch)){
                    ending = ch;
                    if (initial == null){
                        initial = ch;
                    }
                }
            }
            System.out.println(initial);
            System.out.println(ending);
            System.out.println(10*(initial-'0')+ ending-'0');

            return (10*(initial-'0')+ ending-'0');
        }).toList();

        int result = calibrationNumbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println(result);
    }
}
