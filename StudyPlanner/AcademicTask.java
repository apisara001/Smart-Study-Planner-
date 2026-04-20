package StudyPlanner;

import java.time.LocalDate;

public class AcademicTask extends Task {
    protected String courseCode;

    public AcademicTask(String name, String type, LocalDate deadline, String courseCode) {
        super(name, type, deadline);
        this.courseCode = courseCode == null ? "" : courseCode.trim();
    }

    public boolean hasCourseCode() {
        return !courseCode.isEmpty();
    }

    public String getCourseCode() {
        return courseCode;
    }

    @Override
    public String getExtraDetails() {
        if (hasCourseCode()) {
            return "Course: " + courseCode;
        }
        return "Course: -";
    }

    @Override
    public String getReminderMessage() {
        if (isDone)
            return "Academic task already completed.";
        return "Review the material and stay on schedule.";
    }
}