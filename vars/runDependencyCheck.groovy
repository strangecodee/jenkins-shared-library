def call() {

    echo "Running OWASP Dependency Check"

    dependencyCheck(
        additionalArguments: '''
            --scan .
            --format HTML
        ''',
        odcInstallation: 'OWASP-Dependency-Check'
    )

    dependencyCheckPublisher(
        pattern: 'dependency-check-report.xml'
    )

    echo "OWASP Dependency Check completed successfully"
}