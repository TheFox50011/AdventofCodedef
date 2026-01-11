package software.aoc.day10.b;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MachineParser {
    private static final Pattern JOLTAGE_PATTERN = Pattern.compile("\\{([,\\d]+)}");
    private static final Pattern BUTTON_PATTERN = Pattern.compile("\\(([,\\d]+)\\)");

    public static Machine parse(String line) {

        Matcher targetMatcher = JOLTAGE_PATTERN.matcher(line);
        if (!targetMatcher.find()) throw new IllegalArgumentException("No targets found: " + line);
        List<Integer> targets = parseList(targetMatcher.group(1));


        List<List<Integer>> buttons = new ArrayList<>();
        Matcher buttonMatcher = BUTTON_PATTERN.matcher(line);
        while (buttonMatcher.find()) {
            buttons.add(parseList(buttonMatcher.group(1)));
        }
        return new Machine(targets, buttons);
    }

    private static List<Integer> parseList(String str) {
        List<Integer> list = new ArrayList<>();
        for (String part : str.split(",")) {
            list.add(Integer.parseInt(part.trim()));
        }
        return list;
    }
}
