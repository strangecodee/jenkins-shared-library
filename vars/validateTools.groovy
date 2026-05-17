def call() {

    sh '''
        echo "Java Version:"
        java -version

        echo "Maven Version:"
        mvn -version

        echo "Git Version:"
        git --version
    '''

    echo "Build tools validation completed"
}