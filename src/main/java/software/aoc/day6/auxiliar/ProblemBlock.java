package software.aoc.day6.auxiliar;

import java.util.ArrayList;
import java.util.List;

public class ProblemBlock {
    private final List<Long> numbers = new ArrayList<>();
    private String operator;

    public ProblemBlock(List<String> tokens) {
        parseTokens(tokens);
    }

    private void parseTokens(List<String> tokens) {
        for (String token : tokens) {
            if (token.equals("+") || token.equals("*")) {
                this.operator = token;
            } else {
                this.numbers.add(Long.parseLong(token));
            }
        }
    }

    public long solve() {
        if (numbers.isEmpty()) return 0;
        if ("+".equals(operator)) {
            return numbers.stream().mapToLong(Long::longValue).sum();
        } else if ("*".equals(operator)) {
            return numbers.stream().mapToLong(Long::longValue).reduce(1, (a, b) -> a * b);
        }
        throw new IllegalStateException("Operador desconocido o faltante: " + operator);
    }
}
