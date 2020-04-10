package lambdasinaction.chap4;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StreamBasic {

    public static void main(String... args) {
        // Java 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        getLowCaloricDishesNamesInJava8(Dish.menu,
                new DishCaloricPredicate(),
                Comparator.comparing(Dish::getCalories)).forEach(System.out::println);

        System.out.println("---");

        // Java 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);

    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish d : dishes) {
            if (d.getCalories() < 400) {
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for (Dish d : lowCaloricDishes) {
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }

    /**
     * add by aaron 2020-04-07 10:21:02
     *
     * @param dishes
     * @param predicate
     * @param comparator
     * @return
     */
    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes,
                                                               DishCaloricPredicate predicate,
                                                               Comparator<Dish> comparator) {
        return dishes.stream()
                .filter(d -> predicate.filter(d))
                .sorted(comparator)
                .map(Dish::getName)
                .collect(toList());
    }

    interface DishPredicate {
        boolean filter(Dish d);
    }

    static class DishCaloricPredicate implements DishPredicate {
        @Override
        public boolean filter(Dish d) {
            return d.getCalories() < 400;
        }
    }

    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
        return dishes.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
    }
}
