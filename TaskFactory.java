package StudyPlanner;

import java.time.LocalDate;

public class TaskFactory {
    public static Task createTask(
            int type,
            String name,
            LocalDate deadline,
            String courseCode,
            boolean lateSubmissionAllowed,
            String location,
            boolean mandatory,
            int progressPercent,
            String audience) {

        switch (type) {
            case 1:
                return new ExamTask(name, deadline, courseCode);
            case 2:
                return new QuizTask(name, deadline, courseCode);
            case 3:
                return new AssignmentTask(name, deadline, courseCode, lateSubmissionAllowed);
            case 4:
                return new MeetingTask(name, deadline, location, mandatory);
            case 5:
                return new ProjectTask(name, deadline, courseCode, progressPercent);
            case 6:
                return new PresentationTask(name, deadline, location, audience);
            default:
                return new Task(name, "Other", deadline);
        }
    }
}