package StudyPlanner;

import java.time.LocalDate;

public class ProjectTask extends AcademicTask {
    private int progressPercent;

    public ProjectTask(String name, LocalDate deadline, String courseCode, int progressPercent) {
        super(name, "Project", deadline, courseCode);
        this.progressPercent = Math.max(0, Math.min(100, progressPercent));
    }

    @Override
    public int getPriority() {
        long daysLeft = getDaysUntilDeadline();

        if (daysLeft < 0)
            return 1;

        if (daysLeft <= 3) {
            if (progressPercent < 50)
                return 1;
            if (progressPercent < 80)
                return 2;
            return 3;
        }

        if (daysLeft <= 7) {
            if (progressPercent < 30)
                return 1;
            if (progressPercent < 70)
                return 2;
            return 3;
        }

        if (progressPercent < 20)
            return 2;
        return 4;
    }

    @Override
    public String getUrgencyLevel() {
        long daysLeft = getDaysUntilDeadline();

        if (daysLeft < 0)
            return "OVERDUE";
        if (daysLeft <= 3 && progressPercent < 50)
            return "CRITICAL";
        if (daysLeft <= 7 && progressPercent < 70)
            return "HIGH";
        if (progressPercent < 100)
            return "MEDIUM";
        return "LOW";
    }

    @Override
    public String getReminderMessage() {
        if (isDone)
            return "Project already completed.";
        if (progressPercent < 50)
            return "Project progress is low. Work on key parts now.";
        if (progressPercent < 80)
            return "Keep pushing the project toward completion.";
        return "Project is close to done. Finalize the remaining work.";
    }

    @Override
    public String getCompletionMessage() {
        return "Project milestone completed.";
    }

    @Override
    public String getExtraDetails() {
        String courseInfo = hasCourseCode() ? courseCode : "-";
        return "Course: " + courseInfo + ", Progress: " + progressPercent + "%";
    }
}