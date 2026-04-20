package StudyPlanner;

import java.time.LocalDate;

public class EventTask extends Task {
    protected String location;

    public EventTask(String name, String type, LocalDate deadline, String location) {
        super(name, type, deadline);
        this.location = (location == null || location.trim().isEmpty()) ? "-" : location.trim();
    }

    @Override
    public String getExtraDetails() {
        return "Location: " + location;
    }

    @Override
    public String getReminderMessage() {
        if (isDone)
            return "Event already completed.";
        return "Be prepared and arrive on time.";
    }
}