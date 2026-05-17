def call() {

    archiveArtifacts(
        artifacts: 'gitleaks-report.json',
        fingerprint: true
    )

    junit '**/target/surefire-reports/*.xml'

    echo "Reports archived successfully"
}