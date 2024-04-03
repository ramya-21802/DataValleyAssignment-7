import java.util.*;

public class WorkHours {

    public static void main(String[] args) {
        List<EmployeeWorkHours> workHoursList = new ArrayList<>();
        workHoursList.add(new EmployeeWorkHours("Rani", new int[]{8,9,3,4,5})); 
        workHoursList.add(new EmployeeWorkHours("Rama", new int[]{5,8,9,2,3})); 
        workHoursList.add(new EmployeeWorkHours("Uma", new int[]{6,8,4,5,9})); 
        workHoursList.add(new EmployeeWorkHours("Harshi", new int[]{8,6,1,7,3})); 
        workHoursList.add(new EmployeeWorkHours("Swetha", new int[]{8,7,5,9,6})); 

        Map<String, Integer> hoursCount = new LinkedHashMap<>();
        hoursCount.put("More than 40 hours", 0);
        hoursCount.put("Exactly 40 hours", 0);
        hoursCount.put("Less than 40 hours", 0);

        Map<String, Double> averageHoursPerDay = new LinkedHashMap<>();

        for (EmployeeWorkHours employee : workHoursList) {
            int totalHours = Arrays.stream(employee.getDailyHours()).sum();
            if (totalHours > 40) {
                hoursCount.put("More than 40 hours", hoursCount.get("More than 40 hours") + 1);
            } else if (totalHours == 40) {
                hoursCount.put("Exactly 40 hours", hoursCount.get("Exactly 40 hours") + 1);
            } else {
                hoursCount.put("Less than 40 hours", hoursCount.get("Less than 40 hours") + 1);
            }

            double averageHours = totalHours / (double) employee.getDailyHours().length;
            averageHoursPerDay.put(employee.getEmployeeName(), averageHours);
        }

        for (Map.Entry<String, Integer> entry : hoursCount.entrySet()) {
            System.out.println("Number of employees who worked " + entry.getKey() + ": " + entry.getValue());
        }

        System.out.println();

        for (Map.Entry<String, Double> entry : averageHoursPerDay.entrySet()) {
            System.out.println("Average hours worked per day for " + entry.getKey() + " Group : " + entry.getValue());
        }
    }
}

class EmployeeWorkHours {
    private String employeeName;
    private int[] dailyHours;

    public EmployeeWorkHours(String employeeName, int[] dailyHours) {
        this.employeeName = employeeName;
        this.dailyHours = dailyHours;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public int[] getDailyHours() {
        return dailyHours;
    }
}
