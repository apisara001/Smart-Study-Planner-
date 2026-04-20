package StudyPlanner;

import java.time.LocalDate;

public class ExamTask extends AcademicTask {

    public ExamTask(String name, LocalDate deadline, String courseCode) {
        super(name, "Exam", deadline, courseCode);
    }

    @Override
    public int getPriority() {
        long daysLeft = getDaysUntilDeadline();

        if (daysLeft < 0)
            return 1;
        if (daysLeft <= 2)
            return 1;
        if (daysLeft <= 5)
            return 2;
        return 3;
    }

    @Override
    public String getUrgencyLevel() {
        long daysLeft = getDaysUntilDeadline();

        if (daysLeft < 0)
            return "MISSED";
        if (daysLeft <= 2)
            return "CRITICAL";
        if (daysLeft <= 5)
            return "HIGH";
        return "MEDIUM";
    }

    @Override
    public String getStatus() {
        if (!isDone && deadline.isBefore(LocalDate.now())) {
            return "MISSED EXAM!";
        }
        return super.getStatus();
    }

    @Override
    public String getReminderMessage() {
        if (isDone)
            return "Exam already completed.";
        return "Revise key topics before the exam.";
    }

    @Override
    public String getCompletionMessage() {
        return "Exam marked as completed.";
    }
}