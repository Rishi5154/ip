package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeInvalidTimeException;

/**
 * Event class which carries tasks of type Event and extends of the base Task class.
 */
public class Event extends Task {
    protected LocalDateTime time;

    /**
     * Constructor for tasks of event type.
     *
     * @param description               Description of the activity
     * @param index                     Numbers for the indexing process of the tasks
     * @param isDone                    Task completion status
     * @throws DukeInvalidTimeException Wrong definition for the task timing
     */

    public Event(String description, int index, boolean isDone) throws DukeInvalidTimeException {
        super(description, index, isDone);
        super.type = TaskType.EVENT;
        int idx = this.description.indexOf('/');
        try {
            this.time = LocalDateTime.parse(this.description.substring(idx + 4, idx + 20),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new DukeInvalidTimeException();
        }
    }

    /**
     * Returns the text version of task with index & date formatted to MMM d yyyy h:mm a.
     *
     * @return String representation for event objects with indexing and date conversion.
     */

    @Override
    public String getStatusWithIndex() {
        int idx = this.description.indexOf('/');
        String task = this.description.substring(0, idx);
        String end = String.format("at: %s", this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")));
        return String.format("%s. %s%s%s(%s)", index, super.type, isDone ? super.done : super.start, task, end);
    }

    /**
     * Default toString() definition for Event tasks.
     *
     * @return String representation of event objects.
     */

    @Override
    public String toString() {
        int idx = this.description.indexOf('/');
        String task = this.description.substring(0, idx);
        String end = new StringBuilder(this.description.substring(idx + 1)).insert(2, ':').toString();
        return String.format("%s%s%s(%s)", super.type, isDone ? super.done : super.start, task, end);
    }
}
