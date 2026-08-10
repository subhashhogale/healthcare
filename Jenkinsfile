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

        emailext(
            subject: "Healthcare Automation - Build #${env.BUILD_NUMBER} - ${currentBuild.currentResult}",

            body: """
Hello Team,

Healthcare Automation Test Execution is completed.

Build Number : ${env.BUILD_NUMBER}
Build Status : ${currentBuild.currentResult}

Jenkins Build URL:
${env.BUILD_URL}

Regards,
QA Team
""",

            to: 'yourname@gmail.com',

            attachmentsPattern: 'target/ExtentReport.html'
        )
    }
}
