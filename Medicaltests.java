import java.util.*;

class MedicalTestResults {
    double results;

    public MedicalTestResults(double results) {
        this.results = results;
    }

    public double getResultValue() {
        return results;
    }
}

public class medicalTest {

    public static Map<String, Integer> countPatientsByResult(List<MedicalTestResults> resultsList) {
        Map<String, Integer> countByResult = new HashMap<>();
        for (MedicalTestResults result : resultsList) {
            String resultRange = getResultRange(result.results);
            countByResult.put(resultRange, countByResult.getOrDefault(resultRange, 0) + 1);
        }
        return countByResult;
    }

    public static Map<String, Double> calculateAverageValueByResultRange(List<MedicalTestResults> resultsList) {
        Map<String, Double> avgValueByResultRange = new HashMap<>();
        Map<String, Double> totalValueByResultRange = new HashMap<>();
        Map<String, Integer> countByResultRange = new HashMap<>();

        for (MedicalTestResults result : resultsList) {
            String resultRange = getResultRange(result.results);
            double currentValue = result.getResultValue();

            double totalValue = totalValueByResultRange.getOrDefault(resultRange, 0.0);
            int count = countByResultRange.getOrDefault(resultRange, 0);

            totalValueByResultRange.put(resultRange, totalValue + currentValue);
            countByResultRange.put(resultRange, count + 1);
        }

        for (Map.Entry<String, Integer> entry : countByResultRange.entrySet()) {
            String resultRange = entry.getKey();
            int count = entry.getValue();
            double totalValue = totalValueByResultRange.get(resultRange);
            avgValueByResultRange.put(resultRange, totalValue / count);
        }

        return avgValueByResultRange;
    }

    public static String getResultRange(double resultValue) {
        if (resultValue < 10) {
            return "Normal";
        } else if (resultValue < 20) {
            return "Borderline";
        } else {
            return "High";
        }
    }

    public static void main(String[] args) {
        // Sample medical test results
        List<MedicalTestResults> resultsList = new ArrayList<>();
        resultsList.add(new MedicalTestResults(5));
        resultsList.add(new MedicalTestResults(15));
        resultsList.add(new MedicalTestResults(25));
        resultsList.add(new MedicalTestResults(8));
        resultsList.add(new MedicalTestResults(18));

        // Count patients by result range
        Map<String, Integer> patientsByResult = countPatientsByResult(resultsList);

        // Calculate average value by result range
        Map<String, Double> avgValueByResultRange = calculateAverageValueByResultRange(resultsList);

        // Display results
        System.out.println("Number of patients with results within each range:");
        for (Map.Entry<String, Integer> entry : patientsByResult.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " patients");
        }

        System.out.println("\nAverage value for each result range:");
        for (Map.Entry<String, Double> entry : avgValueByResultRange.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
