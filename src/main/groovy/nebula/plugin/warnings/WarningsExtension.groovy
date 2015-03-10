package nebula.plugin.warnings

import org.gradle.api.logging.LogLevel

class WarningsExtension {
    Set<Warning> known = new LinkedHashSet<Warning>()

    def warning(String warningMessage, Closure condition = {true} ) {
        getKnown().add(new Warning(condition, LogLevel.WARN, warningMessage))
    }
    def warning(String logLevelStr, String warningMessage, Closure condition = {true}) {
        def logLevel = LogLevel.valueOf(logLevelStr.toUpperCase())
        getKnown().add(new Warning(condition, logLevel, warningMessage))
    }
}
