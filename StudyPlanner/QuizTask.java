package StudyPlanner;

import java.time.LocalDate;

public class QuizTask extends AcademicTask {

    public QuizTask(String name, LocalDate deadline, String courseCode) {
        super(name, "Quiz", deadline, courseCode);
    }

    @Override
    public int getPriority() {
        long daysLeft = getDaysUntilDeadline();

        if (daysLeft < 0)
            return 1;
        if (daysLeft <= 1)
            return 1;
        if (daysLeft <= 3)
            return 2;
        return 3;
    }

    @Override
    public String getReminderMessage() {
        if (isDone)
            return "Quiz already completed.";
        return "Do a quick review before the quiz.";
    }

    @Override
    public String getCompletionMessage() {
        return "Quiz marked as completed.";
    }
}