import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class MarketingStrategy {
    String name;
    String description;
    String targetAudience;
    double budget;
    double potentialROI;

    public MarketingStrategy(String name, String description, String targetAudience, double budget, double potentialROI) {
        this.name = name;
        this.description = description;
        this.targetAudience = targetAudience;
        this.budget = budget;
        this.potentialROI = potentialROI;
    }

    @Override
    public String toString() {
        return String.format("%s: %s for target audience %s. Budget: $%.2f, Potential ROI: $%.2f",
                name, description, targetAudience, budget, potentialROI);
    }
}

class MarketingStrategyRepository {
    private List<MarketingStrategy> strategies = new ArrayList<>();

    public String addStrategy(String name, String description, String targetAudience, double budget, double potentialROI) {
        if (strategies.stream().anyMatch(strategy -> strategy.name.equals(name))) {
            return "Strategy with name \"" + name + "\" already exists.";
        }
        MarketingStrategy strategy = new MarketingStrategy(name, description, targetAudience, budget, potentialROI);
        strategies.add(strategy);
        return "Strategy \"" + name + "\" added successfully.";
    }

    public String fetchStrategyByName(String name) {
        return strategies.stream()
                .filter(strategy -> strategy.name.equals(name))
                .findFirst()
                .map(MarketingStrategy::toString)
                .orElse("Strategy with name \"" + name + "\" not found.");
    }

    public String updateStrategy(String name, String description, String targetAudience, Double budget, Double potentialROI) {
        for (MarketingStrategy strategy : strategies) {
            if (strategy.name.equals(name)) {
                if (description != null) strategy.description = description;
                if (targetAudience != null) strategy.targetAudience = targetAudience;
                if (budget != null) strategy.budget = budget;
                if (potentialROI != null) strategy.potentialROI = potentialROI;
                return "Strategy \"" + name + "\" updated successfully.";
            }
        }
        return "Strategy with name \"" + name + "\" not found.";
    }

    public String deleteStrategy(String name) {
        boolean removed = strategies.removeIf(strategy -> strategy.name.equals(name));
        if (removed) {
            return "Strategy \"" + name + "\" deleted successfully.";
        } else {
            return "Strategy with name \"" + name + "\" not found.";
        }
    }

    public String listStrategiesByBudgetRange(double minBudget, double maxBudget) {
        List<MarketingStrategy> strategiesInRange = strategies.stream()
                .filter(strategy -> strategy.budget >= minBudget && strategy.budget <= maxBudget)
                .collect(Collectors.toList());
        if (strategiesInRange.isEmpty()) {
            return "No strategies found within the budget range $" + minBudget + " - $" + maxBudget + ".";
        }
        return strategiesInRange.stream()
                .map(strategy -> strategy.name + ": Budget: $" + strategy.budget)
                .collect(Collectors.joining("\n"));
    }
}
