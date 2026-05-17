def call() {

    echo "Running pre-build checks"

    sh '''
        mvn clean compile

        mvn checkstyle:checkstyle

        mvn test
    '''

    echo "Pre-build checks completed successfully"
}