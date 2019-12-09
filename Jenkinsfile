pipeline {
  agent {
    kubernetes {
      yamlFile 'KubernetesPod.yaml'
    }
  }
  environment {
    REVISION = "0.0.${env.BUILD_ID}"
  }
  stages {
    stage('Build...') {
      steps {
        container('maven') {
          sh 'mvn -B clean compile -DskipTests'
        }
      }
    }
    stage('Test...') {
      steps {
        container('maven') {
          sh 'mvn -B test'

          archive (includes: 'pkg/*.gem')

          publishHTML([
            allowMissing: false,
            alwaysLinkToLastBuild: false,
            keepAll: false,
            reportDir: 'target/site/jacoco/',
            reportFiles: 'index.html',
            reportName: 'Code Coverage Report',
            reportTitles: ''
          ])
        }
      }
    }
    stage('QA...') {
      steps {
        container('maven') {
          sh 'mvn clean verify sonar:sonar'
        }
      }
    }
    stage('Deploy...') {
      when { branch "master" }
      steps {
        container('maven') {
          sh 'mvn -B deploy -DskipTests'
        }
      }
    }
  }
  post {
      success {
        emailext (
            subject: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
            body: """<p>SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
              <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
            recipientProviders: [[$class: 'DevelopersRecipientProvider']]
          )
      }

      failure {
        emailext (
            subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
            body: """<p>FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
              <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
            recipientProviders: [[$class: 'DevelopersRecipientProvider']]
          )
      }
  }
}