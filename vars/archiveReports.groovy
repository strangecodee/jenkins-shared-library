def call() {

    archiveArtifacts(
        artifacts: '''
            gitleaks-report.json,
            dependency-check-report.xml
        ''',
        allowEmptyArchive: true,
        fingerprint: true
    )

    junit(
        allowEmptyResults: true,
        testResults: '**/target/surefire-reports/*.xml'
    )

    publishHTML(target: [
        allowMissing: true,
        alwaysLinkToLastBuild: true,
        keepAll: true,
        reportDir: '.',
        reportFiles: 'dependency-check-report.html',
        reportName: 'OWASP Dependency Check Report'
    ])

    echo "Reports archived successfully"
}