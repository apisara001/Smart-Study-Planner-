package StudyPlanner;

import java.time.LocalDate;

public class PresentationTask extends EventTask {
    private String audience;

    public PresentationTask(String name, LocalDate deadline, String location, String audience) {
        super(name, "Presentation", deadline, location);
        this.audience = (audience == null || audience.trim().isEmpty()) ? "-" : audience.trim();
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
    public String getReminderMessage() {
        if (isDone)
            return "Presentation already completed.";
        return "Practice your delivery and prepare your slides.";
    }

    @Override
    public String getCompletionMessage() {
        return "Presentation completed.";
    }

    @Override
    public String getExtraDetails() {
        return super.getExtraDetails() + ", Audience: " + audience;
    }
}