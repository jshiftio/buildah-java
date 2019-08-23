package io.jshift.buildah.core;

import io.jshift.buildah.api.BuildahConfiguration;
import io.jshift.buildah.api.Command;
import io.jshift.buildah.core.terminal.CaptureOutput;
import io.jshift.buildah.core.terminal.ConsoleOutput;
import io.jshift.buildah.core.terminal.StreamDispatcher;
import org.zeroturnaround.exec.InvalidExitValueException;
import org.zeroturnaround.exec.ProcessExecutor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BuildahExecutor implements CliExecutor {

    private static final Logger logger = Logger.getLogger(BuildahExecutor.class.getName());

    private BuildahConfiguration buildahConfiguration;

    private Path buildahHome;
    private Path runcHome;

    BuildahExecutor(Path buildahHome, Path runcHome, BuildahConfiguration buildahConfiguration) {
        this.buildahHome = buildahHome;
        this.runcHome = runcHome;
        this.buildahConfiguration = buildahConfiguration;
    }

    @Override
    public List<String> execute(Command command) {
        return this.execute(buildahHome.getParent(), runcHome.getParent(), command);
    }

    @Override
    public List<String> execute(Path buildahDirectory, Path runcDirectory, Command command) {

        validateInput(this.buildahHome, buildahDirectory, this.runcHome, runcDirectory);

        final List<String> executionCommand = new ArrayList<>();
        executionCommand.add(this.buildahHome.toString());
        executionCommand.addAll(command.getCliCommand());

        logger.info(
                String.format("Executing binary: %s at %s", executionCommand.stream().collect(Collectors.joining(" ")),
                        buildahDirectory));

        final CaptureOutput captureOutput = new CaptureOutput();

        final StreamDispatcher stdStreamDispatcher =
                new StreamDispatcher(captureOutput::capture);

        if (buildahConfiguration.isPrintStandardStreamToConsole()) {
            stdStreamDispatcher.addConsumer(ConsoleOutput.forStandardConsoleOutput()::print);
        }

        final StreamDispatcher errStreamDispatcher = new StreamDispatcher();
        if (buildahConfiguration.isPrintErrorStreamToConsole()) {
            errStreamDispatcher.addConsumer(ConsoleOutput.forErrorConsoleOutput()::print);
        }

        try {

            new ProcessExecutor()
                    .directory(buildahDirectory.toFile())
                    .directory(runcDirectory.toFile())
                    .command(executionCommand)
                    .destroyOnExit()
                    .exitValueNormal()
                    .readOutput(true)
                    .redirectOutput(stdStreamDispatcher)
                    .redirectErrorStream(false)
                    .redirectError(errStreamDispatcher)
                    .execute();

        } catch (InvalidExitValueException e) {
            throw new IllegalStateException(
                    String.format("Error code %d. Result: %s", e.getExitValue(), e.getResult().outputUTF8()));
        } catch (InterruptedException | IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }

        return captureOutput.getOutput();
    }

    private void validateInput(Path buildahBinary, Path buildahDirectory, Path runcBinary, Path runcDirectory) {
        if (!Files.isRegularFile(buildahBinary)) {
            throw new IllegalArgumentException(String.format("%s binary path should point to buildah executable.", buildahBinary));
        }

        if (!Files.isRegularFile(runcBinary)) {
            throw new IllegalArgumentException(String.format("%s binary path should point to runc executable.", runcBinary));
        }

        if (!Files.isDirectory(buildahDirectory)) {
            throw new IllegalArgumentException(
                    String.format("%s should be a directory where running buildah (typically the cloned project)", buildahDirectory));
        }

        if (!Files.isDirectory(runcDirectory)) {
            throw new IllegalArgumentException(
                    String.format("%s should be a directory where running runc (typically the cloned project)", runcDirectory));
        }
    }

}
