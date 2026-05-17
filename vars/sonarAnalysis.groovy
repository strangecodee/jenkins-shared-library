def call() {

    echo "Starting SonarQube analysis"

    withSonarQubeEnv('sonarqube') {

        sh '''
            mvn sonar:sonar
        '''
    }

    echo "SonarQube analysis completed"
}