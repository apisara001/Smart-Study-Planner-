package StudyPlanner;

import java.time.LocalDate;

public class MeetingTask extends EventTask {
    private boolean mandatory;

    public MeetingTask(String name, LocalDate deadline, String location, boolean mandatory) {
        super(name, "Meeting", deadline, location);
        this.mandatory = mandatory;
    }

    @Override
    public int getPriority() {
        long daysLeft = getDaysUntilDeadline();

        if (mandatory) {
            if (daysLeft < 0)
                return 1;
            if (daysLeft <= 1)
                return 1;
            if (daysLeft <= 3)
                return 2;
            return 3;
        }

        if (daysLeft <= 1)
            return 2;
        return 4;
    }

    @Override
    public String getStatus() {
        if (!isDone && deadline.isBefore(LocalDate.now()) && mandatory) {
            return "Missed mandatory meeting";
        }
        return super.getStatus();
    }

    @Override
    public String getReminderMessage() {
        if (isDone)
            return "Meeting already completed.";
        return mandatory ? "Mandatory meeting coming up. Be prepared." : "Optional meeting scheduled.";
    }

    @Override
    public String getCompletionMessage() {
        return "Meeting attended.";
    }

    @Override
    public String getExtraDetails() {
        return super.getExtraDetails() + ", Mandatory: " + (mandatory ? "Yes" : "No");
    }
}