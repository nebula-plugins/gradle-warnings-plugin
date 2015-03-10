package nebula.plugin.warnings

import nebula.test.IntegrationSpec
import org.gradle.api.logging.LogLevel

class WarningsPluginIntegrationTest extends IntegrationSpec {
    void 'always display warning'() {
        buildFile << '''
            apply plugin: 'nebula-warnings'

            warnings {
               warning('this works') { true }
            }
        '''.stripIndent()

        when:
        def result = runTasksSuccessfully('tasks')

        then:
        result.standardOutput.contains 'this works'
    }

    void 'display at different logging levels'() {
        buildFile << '''
            apply plugin: 'nebula-warnings'

            warnings {
               warning('warn', 'this works') { true }
               warning('info', 'info works') { true }
               warning('debug', 'debug works') { true }
            }
        '''.stripIndent()

        when:
        def result = runTasksSuccessfully('tasks')

        then:
        result.standardOutput.contains 'this works'
        result.standardOutput.contains 'info works'
        !result.standardOutput.contains('debug works')

        when:
        logLevel = LogLevel.DEBUG
        def debugResult = runTasksSuccessfully('tasks')

        then:
        debugResult.standardOutput.contains 'this works'
        debugResult.standardOutput.contains 'info works'
        debugResult.standardOutput.contains('debug works')

        cleanup:
        logLevel = LogLevel.INFO
    }

    def 'display warning on multi-module projects'() {
        def warningBuildText = '''
            apply plugin: 'nebula-warnings'

            warnings {
               warning("this works in ${name}") { true }
            }
        '''.stripIndent()
        buildFile << warningBuildText

        createSubProject('subA', warningBuildText)
        createSubProject('subB', warningBuildText)

        when:
        def result = runTasksSuccessfully('tasks')

        then:
        result.standardOutput.contains 'this works in display-warning-on-multi-module-projects'
        result.standardOutput.contains 'this works in subA'
        result.standardOutput.contains 'this works in subB'
    }

    def createSubProject(String name, String buildFile) {
        settingsFile << """
            include '${name}'
        """.stripIndent()

        def sub = new File(projectDir, name)
        sub.mkdirs()

        new File(sub, 'build.gradle') << buildFile

        return sub
    }
}
