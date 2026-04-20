package StudyPlanner;

import java.util.ArrayList;
import java.util.Comparator; // important

public class SortByDeadline implements SortStrategy {

    @Override
    public void sort(ArrayList<Task> tasks) {

        tasks.sort(Comparator
                .comparing(Task::getDeadline) // sort by deadline first
                .thenComparing(Task::getPriority)); // then by priority
    }
}