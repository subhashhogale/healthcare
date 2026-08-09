pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean test'
                bat 'dir target'
            }
        }
    }

    post {
        always {
            publishHTML([
                reportDir: 'target',
                reportFiles: 'ExtentReport.html',
                reportName: 'Extent Report',
                keepAll: true,
                alwaysLinkToLastBuild: true,
                allowMissing: false
            ])
        }
    }
}