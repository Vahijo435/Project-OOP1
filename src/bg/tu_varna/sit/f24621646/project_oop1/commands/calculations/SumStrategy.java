package bg.tu_varna.sit.f24621646.project_oop1.commands.calculations;

import bg.tu_varna.sit.f24621646.project_oop1.contracts.AggregationStrategy;

import java.util.List;

/**
 * @author Vahan
 * Имплементация на интерфейса AggregationStrategy, която изчислява математическата сума от всички числови стойности в подадения списък.
 *
 */
public class SumStrategy implements AggregationStrategy {

    @Override
    public double calculate(List<Double> values) {
        double result=0;
        for (double v : values) result += v;
        return result;
    }
}
