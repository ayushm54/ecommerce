pipeline {
    agent any
    stages {
        stage("Build"){
            steps{
                // To run Maven on a Windows agent, use
                bat "mvn -Dmaven.test.failure.ignore=true clean install"
            }
        }
    }
}
