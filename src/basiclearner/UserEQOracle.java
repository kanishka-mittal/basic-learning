package basiclearner;

import de.learnlib.api.EquivalenceOracle;
import de.learnlib.api.SUL;
import de.learnlib.oracles.DefaultQuery;
import net.automatalib.automata.transout.MealyMachine;
import net.automatalib.words.Word;
import net.automatalib.words.impl.Symbol;

import java.util.*;

/**
 * Created by ramon on 12-12-16.
 */
public class UserEQOracle implements EquivalenceOracle<MealyMachine<?, String, ?, String>, String, Word<String>> {
    private final SUL<String,String> sul;

    public UserEQOracle(SUL<String,String> sul) {
        this.sul = sul;
    }

    @Override
    public DefaultQuery<String, Word<String>> findCounterExample(MealyMachine<?, String, ?, String> hypothesis, Collection<? extends String> allowedInputs) {
        System.out.println("Enter space-separated input sequence to try as a counter-example, or 'stop' to stop learning");
        System.out.println("Allowed inputs: " + allowedInputs);
        Scanner userInputScanner = new Scanner(System.in);
        askCE:
        do {
            String userInput = userInputScanner.nextLine();
            if (userInput.equals("stop")) {
                return null;
            } else {
                String[] sutInputs = userInput.split("\\s");
                if (sutInputs.length != 0) {
                    Word<String> input = Word.fromArray(sutInputs, 0, sutInputs.length);
                    for (String symbol : input) {
                        if (!allowedInputs.contains(symbol)) {
                            System.out.println("Unknown input '" + symbol + "', try again");
                            continue askCE;
                        }
                    }
                    Word<String> hypOutput = hypothesis.computeOutput(input);
                    Word<String> sulOutput = sulOutput(input);
                    System.out.println("SUL output: " + sulOutput);
                    if (!hypOutput.equals(sulOutput)) {
                        System.out.println("Query '" + userInput + "' is a counterexample (hypothesis predicts " + hypOutput + ")");
                        return new DefaultQuery<String, Word<String>>(Word.fromList(Collections.emptyList()), input, sulOutput);
                    } else {
                        System.out.println("Query '" + userInput + "' is not a counterexample, try again");
                    }
                }
            }
        } while (true);
    }

    private Word<String> sulOutput(Word<String> inputs) {
        sul.pre();
        List<String> output = new ArrayList<>();
        for (String input: inputs) {
            output.add(sul.step(input));
        }
        sul.post();
        return Word.fromList(output);
    }
}
