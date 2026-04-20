package StudyPlanner;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Task {
    protected String name;
    protected String type;
    protected LocalDate deadline;
    protected boolean isDone;
    protected LocalDate completedDate;

    public Task(String name, String type, LocalDate deadline) {
        this.name = name;
        this.type = type;
        this.deadline = deadline;
        this.isDone = false;
        this.completedDate = null;
    }

    public void markDone() {
        if (!isDone) {
            this.isDone = true;
            this.completedDate = LocalDate.now();
        }
    }

    protected long getDaysUntilDeadline() {
        return ChronoUnit.DAYS.between(LocalDate.now(), deadline);
    }

    public int getPriority() {
        long daysLeft = getDaysUntilDeadline();

        if (daysLeft < 0)
            return 1;
        if (daysLeft <= 1)
            return 2;
        if (daysLeft <= 3)
            return 3;
        return 5;
    }

    public String getUrgencyLevel() {
        long daysLeft = getDaysUntilDeadline();

        if (daysLeft < 0)
            return "OVERDUE";
        if (daysLeft <= 1)
            return "CRITICAL";
        if (daysLeft <= 3)
            return "HIGH";
        if (daysLeft <= 7)
            return "MEDIUM";
        return "LOW";
    }

    public String getReminderMessage() {
        if (isDone)
            return "Already completed.";
        if (deadline.isBefore(LocalDate.now()))
            return "This task is overdue.";
        return "Stay on track.";
    }

    public String getCompletionMessage() {
        return "Task completed.";
    }

    public String getStatus() {
        LocalDate today = LocalDate.now();

        if (!isDone) {
            if (deadline.isBefore(today)) {
                return "Not accomplished on time";
            }
            return "In Progress";
        } else {
            if (completedDate != null && completedDate.isAfter(deadline)) {
                return "Done late";
            }
            return "Done";
        }
    }

    public boolean hasNote() {
        return !getStatus().equals("In Progress");
    }

    public boolean canBeRemoved() {
        return !hasNote();
    }

    public String getExtraDetails() {
        return "-";
    }

    public boolean isFinished() {
        return isDone;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDeadline() {
        return deadline;
    }
}