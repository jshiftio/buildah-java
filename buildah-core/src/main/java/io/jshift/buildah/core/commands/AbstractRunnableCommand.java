package io.jshift.buildah.core.commands;

import io.jshift.buildah.api.Command;
import io.jshift.buildah.api.RunnableCommand;
import io.jshift.buildah.core.CliExecutor;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public abstract class AbstractRunnableCommand<RETURNTYPE> implements RunnableCommand<RETURNTYPE>, Command {

    protected CliExecutor buildahExecutor;
    protected Function<List<String>, RETURNTYPE> parse;

    protected AbstractRunnableCommand(final CliExecutor buildahExecutor) {
        this.buildahExecutor = buildahExecutor;
    }

    protected AbstractRunnableCommand(final CliExecutor buildahExecutor, Function<List<String>, RETURNTYPE> parse) {
        this.buildahExecutor = buildahExecutor;
        this.parse = parse;
    }

    @Override
    public RETURNTYPE execute(Path buildahDirectory, Path runcdirectory) {

        final List<String> output = this.buildahExecutor.execute(buildahDirectory, runcdirectory, this);

        if (parse != null) {
            return parse.apply(output);
        }

        return null;
    }

    @Override
    public RETURNTYPE execute() {
        final List<String> output = this.buildahExecutor.execute(this);

        if (parse != null) {
            return parse.apply(output);
        }

        return null;
    }
}
