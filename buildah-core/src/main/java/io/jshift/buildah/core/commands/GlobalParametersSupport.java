package io.jshift.buildah.core.commands;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GlobalParametersSupport {

    private static final String ALSOLOGTOSTDERR = "--alsologtostderr";
    private static final String LOGBACKTRACEAT  = "--log_backtrace_at";
    private static final String LOGDIR = "--log_dir";
    private static final String LOGTOSTDERR = "--logtostderr";
    private static final String SKIPCONNECTIONCHECK = "--skip-connection-check";
    private static final String STDERRTHRESHOLD = "--stderrthreshold";
    private static final String V = "--v";
    private static final String VMODULE = "--vmodule";

    private Boolean alsologtostderr;
    private Integer log_backtrace_at;
    private String log_dir;
    private Boolean logtostderr;
    private Boolean skipconnectioncheck;
    private Integer stderrthreshold;
    private Integer v;
    private List<String> vmodule;

    private List<String> extraCommands;

    public List<String> getCliCommand() {

        final List<String> arguments = new ArrayList<>();

        if (alsologtostderr != null && alsologtostderr.booleanValue()) {
            arguments.add(ALSOLOGTOSTDERR);
        }

        if (log_backtrace_at != null) {
            arguments.add(LOGBACKTRACEAT);
            arguments.add(Integer.toString(log_backtrace_at));
        }

        if (log_dir != null) {
            arguments.add(LOGDIR);
            arguments.add(log_dir);
        }

        if (logtostderr != null && logtostderr.booleanValue()) {
            arguments.add(LOGTOSTDERR);
        }

        if (skipconnectioncheck != null && skipconnectioncheck.booleanValue()) {
            arguments.add(SKIPCONNECTIONCHECK);
        }

        if (stderrthreshold != null) {
            arguments.add(STDERRTHRESHOLD);
            arguments.add(Integer.toString(stderrthreshold));
        }

        if (v != null) {
            arguments.add(V);
            arguments.add(Integer.toString(v));
        }

        if (vmodule != null && vmodule.size() > 0) {
            arguments.add(VMODULE);
            arguments.add(toCsv(vmodule));
        }

        if (extraCommands != null) {
            arguments.addAll(extraCommands);
        }

        return arguments;
    }

    private String toCsv(List<String> args) {
        return args.stream()
                .collect(Collectors.joining(", "));
    }

    public static abstract class Builder<T> {

        private GlobalParametersSupport globalParametersSupport;

        private Class<T> typeOfT;

        public Builder() {
            this.globalParametersSupport = new GlobalParametersSupport();
            this.typeOfT = (Class<T>)
                    ((ParameterizedType)getClass()
                            .getGenericSuperclass())
                            .getActualTypeArguments()[0];
        }

        public T withAlsoLogToStderr() {
            this.globalParametersSupport.alsologtostderr = true;
            final Type type = ((ParameterizedType)
                    this.getClass().getGenericInterfaces()[0])
                    .getActualTypeArguments()[0];
            return typeOfT.cast(this);
        }

        public T withLogBacktraceAt(Integer logBacktraceAt) {
            this.globalParametersSupport.log_backtrace_at = logBacktraceAt;
            return typeOfT.cast(this);
        }

        public T withLogDir(String log_dir) {
            this.globalParametersSupport.log_dir = log_dir;
            return typeOfT.cast(this);
        }

        public T withLogToStderr() {
            this.globalParametersSupport.logtostderr = true;
            return typeOfT.cast(this);
        }

        public T withSkipConnectionCheck() {
            this.globalParametersSupport.skipconnectioncheck = true;
            return typeOfT.cast(this);
        }

        public T withStderrThreshold(Integer threshold) {
            this.globalParametersSupport.stderrthreshold = threshold;
            return typeOfT.cast(this);
        }

        public T withV(Integer v) {
            this.globalParametersSupport.v = v;
            return typeOfT.cast(this);
        }

        public T withVModule(List<String> vmodule) {
            this.globalParametersSupport.vmodule = vmodule;
            return typeOfT.cast(this);
        }

        public T withExtraArguments(List<String> extraArguments) {
            this.globalParametersSupport.extraCommands = extraArguments;
            return typeOfT.cast(this);
        }

        protected GlobalParametersSupport buildGlobalParameters() {
            return this.globalParametersSupport;
        }

    }
}
