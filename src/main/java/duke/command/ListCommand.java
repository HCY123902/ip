package duke.command;

import duke.core.*;

/**
 * The ListCommand class represents a command that lists the tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Takes in the task list, the interface, and the storage components, and list
     * the tasks in the task list.
     *
     * @param taskList The task list component.
     * @param ui The user interface component.
     * @param storage The storage component.
     */
    @Override
    public Result excecute(TaskList taskList, Ui ui, Storage storage) {
        return new Result(ui.getTaskListMessage(taskList), isContinuing(), MessageType.COMMAND_FOUND_MESSAGE);
    }
}
