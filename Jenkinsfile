#!/usr/bin/env groovy

import hudson.model.Result
import hudson.model.Run
import jenkins.model.CauseOfInterruption.UserInterruption

if (env.BRANCH_NAME == "master") {
  properties([
      buildDiscarder(
          logRotator(
              daysToKeepStr: '90'
          )
      )
  ])
} else {
  properties([
      buildDiscarder(
          logRotator(
              numToKeepStr: '10'
          )
      )
  ])
}

def docker_image_dind = 'docker:18.06.0-ce-dind'
def build_image = 'pegasyseng/pantheon-build:0.0.5-jdk8'

def abortPreviousBuilds() {
  Run previousBuild = currentBuild.rawBuild.getPreviousBuildInProgress()

  while (previousBuild != null) {
    if (previousBuild.isInProgress()) {
      def executor = previousBuild.getExecutor()
      if (executor != null) {
        echo ">> Aborting older build #${previousBuild.number}"
        executor.interrupt(Result.ABORTED, new UserInterruption(
            "Aborted by newer build #${currentBuild.number}"
        ))
      }
    }

    previousBuild = previousBuild.getPreviousBuildInProgress()
  }
}

if (env.BRANCH_NAME != "master") {
  abortPreviousBuilds()
}

try {
  parallel Library: {
    def stage_name = "Library node: "
    node {
      checkout scm
      docker.image(docker_image_dind).withRun('--privileged') { d ->
        docker.image(build_image).inside("--link ${d.id}:docker") {
          stage(stage_name + 'Prepare') {
            sh './gradlew --no-daemon --parallel clean compileJava assemble'
          }
          stage(stage_name + 'build') {
            sh './gradlew --no-daemon --parallel build'
          }
          if (env.BRANCH_NAME == "master") {
            stage(stage_name + 'Bintray Upload') {
              withCredentials([
                  usernamePassword(
                      credentialsId: 'pegasys-bintray',
                      usernameVariable: 'BINTRAY_USER',
                      passwordVariable: 'BINTRAY_KEY'
                  )
              ]) {
                sh './gradlew --no-daemon --parallel bintrayUpload'
              }
            }
          }
        }
      }
    }
  }
} catch (ignore) {
  currentBuild.result = 'FAILURE'
} finally {
  if (env.BRANCH_NAME == "master") {
    def currentResult = currentBuild.result ?: 'SUCCESS'
    def channel = '#priv-pegasys-prod-dev'
    if (currentResult == 'SUCCESS') {
      def previousResult = currentBuild.previousBuild?.result
      if (previousResult != null && (previousResult == 'FAILURE' || previousResult == 'UNSTABLE')) {
        slackSend(
            color: 'good',
            message: "Pantheon Plugins API branch ${env.BRANCH_NAME} build is back to HEALTHY.\nBuild Number: #${env.BUILD_NUMBER}\n${env.BUILD_URL}",
            channel: channel
        )
      }
    } else if (currentBuild.result == 'FAILURE') {
      slackSend(
          color: 'danger',
          message: "Pantheon Plugins API branch ${env.BRANCH_NAME} build is FAILING.\nBuild Number: #${env.BUILD_NUMBER}\n${env.BUILD_URL}",
          channel: channel
      )
    } else if (currentBuild.result == 'UNSTABLE') {
      slackSend(
          color: 'warning',
          message: "Pantheon Plugins API branch ${env.BRANCH_NAME} build is UNSTABLE.\nBuild Number: #${env.BUILD_NUMBER}\n${env.BUILD_URL}",
          channel: channel
      )
    }
  }
}
