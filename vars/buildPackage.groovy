def call() {

    sh 'mvn clean package -DskipTests'

    echo "Application package build completed"
}