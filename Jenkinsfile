// #PIPELINE# feat-* >> dev >> stg >> rel >> master
// feat-* 特性分支，开发新特性，发布<mainVersion>.*-SNAPSHOT包
// dev 开发主分支，归并特性分支代码，Code Review，发布<mainVersion>.dev-SNAPSHOT包
// stg 测试分支，集成测试，发布<mainVersion>.stg-SNAPSHOT包
// rel 发布分支，发布<mainVersion>.SNAPSHOT包
// IMPORTANT!!! 我们不适用分支发布RELEASE版本的包，因为分支随时有可能重新执行，但RELEASE的包只能上传一次，如果要上传RELEASE版本的包
//              需要使用tag并在jenkins上手动触发，tag格式：v<version>
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
            if (!(branchName ==~ /feat-\d+/ || branchSet.contains(branchName) || branchName ==~ /MR-\d+-merge/ || branchName ==~ /v\d+\.\d+\.\d+.*/)) {
              throw new Exception("分支命名不规范，仅支持feat-*/dev/stg/rel/master")
            }

            // Feature Branch
            if (branchName ==~ /feat-\d+/) {
              revision = revision.replaceAll('-SNAPSHOT', '.' + branchName.substring(5) + '-SNAPSHOT')
            }

            // Merge Request
            if (branchName ==~ /MR-\d+-merge/) {
              revision = revision.replaceAll('-SNAPSHOT', '.' + branchName.toLowerCase().replaceAll('-merge', '').replaceAll(/-/, '.') + '-SNAPSHOT')
            }

            // SNAPSHOT分支
            if (branchName == 'dev' || branchName == 'stg') {
              revision = revision.replaceAll('-SNAPSHOT', '.' + branchName + '-SNAPSHOT')
            }

            // TAG
            if (branchName ==~ /v\d+\.\d+\.\d+.*/) {
              revision = branchName.substring(1)
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
            branch pattern: /feat-\d+/, comparator: "REGEXP"
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
        stage('TAG branch') {
          when {
            branch pattern: /v\d+\.\d+\.\d+.*/, comparator: "REGEXP"
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
            subject: "【Jenkins】编译通过: ${env.JOB_NAME} [${env.BUILD_NUMBER}]",
            body: """<p>编译通过: ${env.JOB_NAME} [${env.BUILD_NUMBER}]:</p>
              <p>点击链接查看编译日志： <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>""",
            recipientProviders: [[$class: 'DevelopersRecipientProvider']]
          )
      }

      failure {
        emailext (
            subject: "【Jenkins】编译失败: ${env.JOB_NAME} [${env.BUILD_NUMBER}]",
            body: """<p>编译失败: ${env.JOB_NAME} [${env.BUILD_NUMBER}]:</p>
              <p>点击链接查看编译日志： <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>""",
            recipientProviders: [[$class: 'DevelopersRecipientProvider']]
          )
      }
  }
}
