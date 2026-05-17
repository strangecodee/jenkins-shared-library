def call() {

    catchError(buildResult: 'SUCCESS', stageResult: 'UNSTABLE') {

        sh '''
            /usr/local/bin/gitleaks detect \
            --source . \
            --report-format json \
            --report-path gitleaks-report.json
        '''
    }

    echo "GitLeaks scan completed"
}