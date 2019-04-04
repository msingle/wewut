
#!groovy

def statuscolor = { currentBuild ->
  def status = "good"
  if (currentBuild.previousBuild != "SUCCESS") {
    if (currentBuild.result == "UNSTABLE") {
      status = "warning"
    }
    if (currentBuild.result == "FAILURE") {
      status = "danger"
    }
  }
  status
}

echo("Hello from Pipeline");

node {
  withEnv(['PATH+EXTRA=/usr/local/bin:/opt/ant/bin']) {

    stage("Environment") {
      sh 'mvn -version'
    }
    
    stage('Build (Maven)') {
      dir('/home/mts001/work/src/wewut/src') {
        mvn 'verify'
      }
    }
  }
}

echo("Pipeline Completed");
