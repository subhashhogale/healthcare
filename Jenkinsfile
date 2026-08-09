pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'shailaja',
                    url: 'https://github.com/yourusername/healthcare.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            echo 'Test execution completed'
        }

        success {
            echo 'Tests Passed'
        }

        failure {
            echo 'Tests Failed'
        }
    }
}