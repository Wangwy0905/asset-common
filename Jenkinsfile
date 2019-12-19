// #PIPELINE# feat-* >> dev >> stg >> rel >> master
// feat-* 特性分支，开发新特性，发布<mainVersion>.*-SNAPSHOT包
// dev 开发主分支，归并特性分支代码，Code Review，发布<mainVersion>.dev-SNAPSHOT包
// stg 测试分支，集成测试，发布<mainVersion>.stg-SNAPSHOT包
// rel 发布分支，发布<mainVersion>包，若rel上有tag，则根据tag名称进行打包

def revision = "" // 版本号
pipeline {
  agent {
    kubernetes {
      yamlFile 'KubernetesPod.yaml'
    }
  }
  stages {
    stage('Check') {
      steps {
        container('maven') {
          script {
            revision = readMavenPom().getProperties().getProperty('revision')
            if (!(revision && revision.endsWith("-SNAPSHOT"))) {
                throw new Exception("pom.xml文件中的revision必须以-SNAPSHOT结尾")
            }

            def branchName = env.getEnvironment().get('BRANCH_NAME')
            Set branchSet = ["dev", "stg", "rel", "master"]
            if (!(branchName ==~ /feat-\d+/ || branchSet.contains(branchName) || branchName ==~ /MR-\d+-merge/)) {
              throw new Exception("分支命名不规范，仅支持feat-*/dev/stg/rel/master")
            }

            if (branchName ==~ /feat-\d+/) {
              revision = revision.replaceAll('-SNAPSHOT', '.' + env.getEnvironment().BRANCH_NAME.substring(5) + '-SNAPSHOT')
            }

            if (branchName ==~ /MR-\d+-merge/) {
              revision = revision.replaceAll('-SNAPSHOT', '.' + env.getEnvironment().BRANCH_NAME.toLowerCase().replaceAll('-merge', '').replaceAll(/-/, '.') + '-SNAPSHOT')
            }

            if (branchName == 'dev' || branchName == 'stg') {
              revision = revision.replaceAll('-SNAPSHOT', '.' + env.getEnvironment().BRANCH_NAME + '-SNAPSHOT')
            }

            if (branchName == 'rel') {
              revision = revision.replaceAll('-SNAPSHOT', '')
            }
          }
        }
      }
    }
    stage('Build') {
      steps {
        container('maven') {
          sh "mvn -B -Drevision=${revision} clean compile -DskipTests"
        }
      }
    }
    stage('Test and Sonar Scan') {
      steps {
        container('maven') {
          sh "mvn -B -Drevision=${revision} verify sonar:sonar"
        }
      }
    }
    stage('Upload Jar') {
      parallel {
        stage('FEATURE branch') {
          when {
            branch pattern: "feat-\\d+", comparator: "REGEXP"
          }
          steps {
            container('maven') {
              sh "mvn -B -Drevision=${revision} deploy -DskipTests"
            }
          }
        }
        stage('SNAPSHOT branch') {
          when {
            anyOf {
              branch 'dev'; branch 'stg'
            }
          }
          steps {
            container('maven') {
              sh "mvn -B -Drevision=${revision} deploy -DskipTests"
            }
          }
        }
        stage('RELEASE branch') {
          when {
            branch 'rel'
          }
          steps {
            container('maven') {
              sh "mvn -B -Drevision=${revision} deploy -DskipTests"
            }
          }
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
