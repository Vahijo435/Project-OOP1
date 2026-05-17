package bg.tu_varna.sit.f24621646.project_oop1.commands.calculations;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.AggregationStrategy;

import java.util.List;
/**
 * @author Vahan
 * Имплементация на интерфейса AggregationStrategy, която намира и връща максималната стойност от подаден списък с числа.
 *
 */
public class MaximumStrategy implements AggregationStrategy {

    @Override
    public double calculate(List<Double> values) {
        double result = values.getFirst();
        for (double v : values) {
            if (v > result) result = v;
        }
        return result;
    }
}
