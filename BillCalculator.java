public class BillCalculator {
    public static double calculate(String size, int toppings) {
        double base = switch (size) {
            case "XL" -> 15.00;
            case "L" -> 12.00;
            case "M" -> 10.00;
            case "S" -> 8.00;
            default -> 0.00;
        };
        double toppingCost = toppings * 1.50;
        double subtotal = base + toppingCost;
        return Math.round(subtotal * 1.15 * 100.0) / 100.0; // add 15% HST
    }
}