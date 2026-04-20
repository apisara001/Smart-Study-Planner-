package StudyPlanner;

import java.util.ArrayList;

public class Planner {
    private ArrayList<Task> tasks;
    private SortStrategy sortStrategy;

    public Planner(SortStrategy sortStrategy) {
        this.tasks = new ArrayList<>();
        this.sortStrategy = sortStrategy;
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Task added successfully.");
    }

    public ArrayList<String> getSavedCourseCodes() {
        ArrayList<String> courseCodes = new ArrayList<>();

        for (Task task : tasks) {
            if (task instanceof AcademicTask) {
                AcademicTask academicTask = (AcademicTask) task;
                if (academicTask.hasCourseCode() && !courseCodes.contains(academicTask.getCourseCode())) {
                    courseCodes.add(academicTask.getCourseCode());
                }
            }
        }

        return courseCodes;
    }

    public void showPlanner() {
        System.out.println("\n===== SMART PLANNER =====");
        System.out.println("Today's Date: " + java.time.LocalDate.now());

        if (tasks.isEmpty()) {
            System.out.println("You currently have no plans. Add one?");
            return;
        }

        sortStrategy.sort(tasks);

        System.out.println("\nYour Plans:\n");

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);

            System.out.println(
                    "[" + i + "] " +
                            t.getName() + " | " +
                            t.getType() + " | " +
                            t.getDeadline() + " | " +
                            t.getStatus() + " | " +
                            "Urgency: " + t.getUrgencyLevel() + " | " +
                            t.getExtraDetails());

            System.out.println("    Reminder: " + t.getReminderMessage());
        }
    }

    public void markTaskDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid index.");
            return;
        }

        Task t = tasks.get(index);

        if (t.isFinished()) {
            System.out.println("Task is already done.");
            return;
        }

        t.markDone();
        System.out.println(t.getCompletionMessage());
        System.out.println("Status: " + t.getStatus());
    }

    public void removeTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid index.");
            return;
        }

        Task t = tasks.get(index);

        if (!t.canBeRemoved()) {
            System.out.println("Cannot remove task because it already has a note/status.");
        } else {
            tasks.remove(index);
            System.out.println("Task removed.");
        }
    }

    public void clearFinishedTasks() {
        int before = tasks.size();
        tasks.removeIf(Task::isFinished);
        int removed = before - tasks.size();

        if (removed == 0) {
            System.out.println("No finished tasks to clear.");
        } else {
            System.out.println(removed + " finished tasks cleared.");
        }
    }
}