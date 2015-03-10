package nebula.plugin.warnings

import org.gradle.BuildResult
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger

class WarningsPlugin implements Plugin<Project> {
    public static final String EXTENSION_NAME = 'warnings'
    public static final String TAG = 'taggedByWarningsPlugin'

    Iterable<Warning> gatherWarnings(Project rootProject) {
        Set<Project> relevant = rootProject.allprojects.findAll { it.extensions.findByType(WarningsExtension) }
        relevant.collectMany { it.extensions.getByType(WarningsExtension).getKnown() }
    }

    void displayWarnings(Logger logger, Iterable<Warning> warnings) {
        Iterable<Warning> conditions = warnings.findAll { it.condition() }
        conditions.each {
            logger.log(it.logLevel, it.message)
        }
    }

    @Override
    void apply(Project project) {
        project.extensions.create(EXTENSION_NAME, WarningsExtension)

        def alreadyTagged = project.rootProject.hasProperty(TAG)
        if (!alreadyTagged) {
            project.gradle.buildFinished { BuildResult result ->
                if (!result.failure) {
                    def warnings = gatherWarnings(project.rootProject)
                    displayWarnings(project.logger, warnings)
                }
            }
            project.rootProject.ext[TAG] = true
        }
    }
}
