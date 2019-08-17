package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;

import java.util.ArrayList;
import java.util.List;

public class BuildahFromCommand extends AbstractRunnableCommand<String>{

    private static final String COMMAND_NAME = "from";

    private String baseImageName;
    private GlobalParametersSupport globalParametersSupport;

    private BuildahFromCommand(CliExecutor buildahExecutor, String baseImageName) {
        super(buildahExecutor, BuildahFromCommand::parse);
        this.baseImageName = baseImageName;
    }
    @Override
    public List<String> getCliCommand() {
        List<String> arguments = new ArrayList();
        arguments.add(COMMAND_NAME);

        arguments.add(this.baseImageName);

        return arguments;
    }

    protected static String parse(List<String> consoleOutput) {
        final String output = String.join(" ", consoleOutput.toArray(new String[consoleOutput.size()]));
        return output;
    }
    public static class Builder extends GlobalParametersSupport.Builder<BuildahFromCommand.Builder> {
        private BuildahFromCommand buildahFromCommand;

        public Builder(String baseImageName, CliExecutor buildahExecutor) {
            this.buildahFromCommand = new BuildahFromCommand(buildahExecutor, baseImageName);
        }

        public BuildahFromCommand build() {
            buildahFromCommand.globalParametersSupport = this.buildGlobalParameters();
            return buildahFromCommand;
        }
    }
}
