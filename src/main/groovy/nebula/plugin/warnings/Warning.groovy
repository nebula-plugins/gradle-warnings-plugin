package nebula.plugin.warnings

import groovy.transform.Canonical
import org.gradle.api.logging.LogLevel

/**
 * Associates a warning message with the condition when it should be displayed.
 */
@Canonical
class Warning {
    Closure<Boolean> condition
    LogLevel logLevel
    String message
}
