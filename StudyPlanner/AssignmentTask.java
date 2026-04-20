package StudyPlanner;

import java.time.LocalDate;

public class AssignmentTask extends AcademicTask {
    private boolean lateSubmissionAllowed;

    public AssignmentTask(String name, LocalDate deadline, String courseCode, boolean lateSubmissionAllowed) {
        super(name, "Assignment", deadline, courseCode);
        this.lateSubmissionAllowed = lateSubmissionAllowed;
    }

    @Override
    public int getPriority() {
        long daysLeft = getDaysUntilDeadline();

        if (daysLeft < 0)
            return lateSubmissionAllowed ? 3 : 1;
        if (daysLeft <= 1)
            return 1;
        if (daysLeft <= 3)
            return 2;
        return 3;
    }

    @Override
    public String getStatus() {
        if (!isDone && deadline.isBefore(LocalDate.now())) {
            if (lateSubmissionAllowed) {
                return "Late submission allowed";
            }
            return "Missed assignment";
        }
        return super.getStatus();
    }

    @Override
    public String getReminderMessage() {
        if (isDone)
            return "Assignment already completed.";
        if (lateSubmissionAllowed) {
            return "Complete and submit before scores deduction.";
        }
        return "Finish the assignment before the deadline.";
    }

    @Override
    public String getCompletionMessage() {
        return "Assignment submitted.";
    }

    @Override
    public String getExtraDetails() {
        String courseInfo = hasCourseCode() ? courseCode : "-";
        return "Course: " + courseInfo + ", Late allowed: " + (lateSubmissionAllowed ? "Yes" : "No");
    }
}