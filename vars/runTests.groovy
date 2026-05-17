def call() {

    sh 'mvn clean test'

    echo "Unit testing completed successfully"
}