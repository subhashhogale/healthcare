pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
                  // Extent Report publish
            publishHTML([
                reportDir: 'target',
                reportFiles: 'ExtentReport.html',
                reportName: 'Extent Report',
                keepAll: true,
                alwaysLinkToLastBuild: true,
                allowMissing: false
            ])


 // Screenshot archive
            archiveArtifacts(
                artifacts: 'target/screenshots/*.png',
                allowEmptyArchive: true
            )



            emailext(
                subject: "Healthcare Automation - Build #${env.BUILD_NUMBER} - ${currentBuild.currentResult}",

                body: """
Hello Team,

Healthcare Automation Test Execution is completed.

Project      : Healthcare-Selenium
Build Number : ${env.BUILD_NUMBER}
Build Status : ${currentBuild.currentResult}

Jenkins Build URL:
${env.BUILD_URL}

Extent Report is available in Jenkins.

Regards,
QA Team
""",

                to: 'jobjenkin89@gmail.com,shailaja2595@gmail.com',

                attachmentsPattern: 'target/ExtentReport.html'
            )
        }
    }
}