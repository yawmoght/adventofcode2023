package days.day2;

import days.Day;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Day2 extends Day {

    public Day2() {
        subdir = "day2";
        readData();
    }

    @Override
    public void execute() {

        HashMap<String, Integer> target = new HashMap<>();
        target.put("red", 12);
        target.put("green", 13);
        target.put("blue", 14);

        AtomicInteger result = new AtomicInteger(0);
        AtomicInteger power = new AtomicInteger(0);
        data.forEach(line -> {

            //para cada juego (linea), generamos si es posible o no
            AtomicBoolean isPossible = new AtomicBoolean(true);

                    String setListString = line.split(":")[1];
                    List<String> game = List.of(setListString.split(";"));

                    HashMap<String, Integer> minimum = new HashMap<>();
                    minimum.put("red", 0);
                    minimum.put("green", 0);
                    minimum.put("blue", 0);
                    game.forEach(set -> {
                        List<String> drawList = List.of(set.split(","));
                        drawList.forEach(draw -> {
                            for (Map.Entry<String, Integer> targetColor : target.entrySet()) {
                                if (draw.contains(targetColor.getKey())) {
                                    String numberOnly = draw.replaceAll("[^0-9]", "");
                                    int number = Integer.parseInt(numberOnly);
                                    String colorName = targetColor.getKey();
                                    if (number > target.get(colorName)) {
                                        isPossible.set(false);
                                    }

                                    //SEGUNDA ESTRELLA
                                    int currentMinimum = minimum.get(colorName);
                                    minimum.put(colorName, Math.max(number, currentMinimum));
                                }
                            }
                        });
                    });

                    if (isPossible.get()) {
                        String gamePart = line.split(":")[0];
                        String gameNumber = gamePart.replaceAll("[^0-9]", "");
                        result.addAndGet(Integer.parseInt(gameNumber));
                    }

                    //SEGUNDA ESTRELLA
                    int currentPower = minimum.get("red") * minimum.get("blue") * minimum.get("green");
                    power.addAndGet(currentPower);
                }
        );

        System.out.println(result.get());
        System.out.println(power.get());
    }
}
