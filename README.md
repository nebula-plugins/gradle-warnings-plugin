gradle-warnings-plugin
================================


## Usage

### Applying the Plugin

To include, add the following to your build.gradle

    buildscript {
        repositories { jcenter() }

        dependencies {
            classpath 'com.netflix.nebula:gradle-warnings-plugin:2.2.+'
        }
    }

    apply plugin: 'nebula-warnings'