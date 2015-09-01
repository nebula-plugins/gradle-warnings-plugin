gradle-warnings-plugin
================================
[![Build Status](https://travis-ci.org/nebula-plugins/projects/gradle-warnings-plugin.svg?branch=master)](https://travis-ci.org/nebula-plugins/projects/gradle-warnings-plugin)
[![Coverage Status](https://coveralls.io/repos/nebula-plugins/projects/gradle-warnings-plugin/badge.svg?branch=masterservice=github)](https://coveralls.io/github/nebula-plugins/projects/gradle-warnings-plugin?branch=master)
[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/nebula-plugins/projects/gradle-warnings-plugin?utm_source=badgeutm_medium=badgeutm_campaign=pr-badge)
[![Apache 2.0](https://img.shields.io/github/license/nebula-plugins/projects/gradle-warnings-plugin.svg)](http://www.apache.org/licenses/LICENSE-2.0)



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