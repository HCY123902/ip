package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import duke.command.Command;
import duke.handle.CommandNotFoundException;
import duke.handle.TaskNotFoundException;
import duke.handle.LoadingException;
import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.core.Parser;

/**
 * The Duke class represents a duke bot that can interact with
 * the user and help the user to manage their tasks, which can
 * help to store the tasks entered by the user into a local record, add
 * the tasks, remove the tasks, read the tasks in the record and
 * present them to the user, and help to
 * search for a specific task for the user.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static final String FILE_PATH = "data/duke.txt";

    /**
     * Takes in the path of the local record, and creates a duke bot to interact with
     * the user.
     *
     * @param filePath The path of the local record.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.readRecord());
        } catch (FileNotFoundException fileNotFoundException) {
            ui.handle(fileNotFoundException);
            taskList = new TaskList();
            try {
                storage.writeRecord(taskList);
            } catch (IOException ioException) {
            }
        } catch (LoadingException loadingException) {
            ui.handle(loadingException);
            taskList = new TaskList();
            try {
                storage.writeRecord(taskList);
            } catch (IOException ioException) {
            }
        }
    }

    /**
     * Starts the interaction between the bot and the user.
     */
    public void run() {
        //System.out.println("\u2718");
        this.ui.showGreeting();
        boolean isContinuing = true;
        while (isContinuing) {
            try {
                String command = this.ui.readCommand();
                Command parsedCommand = Parser.parseCommand(command);
                parsedCommand.excecute(taskList, ui, storage);
                isContinuing = parsedCommand.isContinuing();
            } catch (CommandNotFoundException commandNotFoundexException) {
                //System.out.println(commandNotFoundexException.getMessage());
                ui.handle(commandNotFoundexException);
            } catch (TaskNotFoundException taskNotFoundException) {
                ui.handle(taskNotFoundException);
            } catch (IOException ioException) {
                ui.handle(ioException);
            }
        }
    }

    /**
     * Creates a duke bot and starts the interaction between the bot and the user.
     *
     * @param args The arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke(FILE_PATH);
        duke.run();
    }
}