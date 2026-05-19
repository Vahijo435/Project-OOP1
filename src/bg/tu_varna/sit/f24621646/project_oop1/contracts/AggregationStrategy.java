package bg.tu_varna.sit.f24621646.project_oop1.contracts;

import java.util.List;
/**
 * @author Vahan
 * Интерфейс, дефиниращ стратегия за извършване на математически операции върху списък от числови стойности.
 * Реализира шаблона за дизайн Strategy за командата Aggregate.
 *
 */
public interface AggregationStrategy {
    /**
     * Изчислява математическа стойност въз основа на списък от числа.
     *
     */
    double calculate(List<Double> values);
}
