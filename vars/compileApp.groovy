def call() {

    sh 'mvn clean compile'

    echo "Application compilation successful"
}