package software.aoc.day10.a;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MachineParser {

    private static final Pattern TARGET_PATTERN = Pattern.compile("\\[([.#]+)\\]");
    private static final Pattern BUTTON_PATTERN = Pattern.compile("\\(([,\\d]+)\\)");

    public static Machine parse(String line) {

        Matcher targetMatcher = TARGET_PATTERN.matcher(line);
        if (!targetMatcher.find()) {
            throw new IllegalArgumentException("Invalid machine format (no indicator diagram): " + line);
        }
        String lightDiagram = targetMatcher.group(1);
        long targetMask = parseLights(lightDiagram);

        List<Long> buttons = new ArrayList<>();
        Matcher buttonMatcher = BUTTON_PATTERN.matcher(line);
        while (buttonMatcher.find()) {
            String indicesStr = buttonMatcher.group(1);
            buttons.add(parseButtonIndices(indicesStr));
        }

        return new Machine(targetMask, buttons);
    }

    private static long parseLights(String diagram) {
        long mask = 0;
        for (int i = 0; i < diagram.length(); i++) {
            if (diagram.charAt(i) == '#') {
                mask |= (1L << i);
            }
        }
        return mask;
    }

    private static long parseButtonIndices(String indicesStr) {
        long mask = 0;
        String[] parts = indicesStr.split(",");
        for (String part : parts) {
            int index = Integer.parseInt(part.trim());
            mask |= (1L << index);
        }
        return mask;
    }
}