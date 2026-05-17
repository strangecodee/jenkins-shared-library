def call(Map config = [:]) {

    pipeline {

        agent {
            label 'built-in'
        }

        options {

            disableConcurrentBuilds()

            timestamps()

            buildDiscarder(
                logRotator(
                    numToKeepStr: '10'
                )
            )

            ansiColor('xterm')
        }

        environment {

            JAVA_HOME = '/usr/lib/jvm/java-11-openjdk-amd64'

            PATH = "${JAVA_HOME}/bin:${env.PATH}"

            APP_NAME = "${config.appName}"
        }

        parameters {

            choice(
                name: 'ENVIRONMENT',
                choices: ['dev', 'qa', 'prod'],
                description: 'Select Deployment Environment'
            )
        }

        stages {

            stage('Pipeline Initialization') {

                steps {

                    script {

                        pipelineBanner(params.ENVIRONMENT)

                    }
                }
            }

            stage('Production Pipeline') {

                when {

                    expression {

                        params.ENVIRONMENT == 'prod'

                    }
                }

                stages {

                    stage('Checkout Source Code') {

                        steps {

                            script {

                                checkoutCode(
                                    config.repoUrl,
                                    config.branch
                                )

                            }
                        }
                    }

                    stage('Security Scan - GitLeaks') {

                        steps {

                            script {

                                runGitLeaks()

                            }
                        }
                    }

                    stage('Validate Build Tools') {

                        steps {

                            script {

                                validateTools()

                            }
                        }
                    }

                    stage('Parallel Build Execution') {

                        parallel {

                            stage('Compile Application') {

                                steps {

                                    dir('compile-workspace') {

                                        script {

                                            checkoutCode(
                                                config.repoUrl,
                                                config.branch
                                            )

                                            compileApp()

                                        }
                                    }
                                }
                            }

                            stage('Execute Unit Tests') {

                                steps {

                                    dir('test-workspace') {

                                        script {

                                            checkoutCode(
                                                config.repoUrl,
                                                config.branch
                                            )

                                            runTests()

                                        }
                                    }
                                }
                            }
                        }
                    }

                    stage('Approval Gate') {

                        steps {

                            timeout(time: 2, unit: 'MINUTES') {

                                script {

                                    approvalStage()

                                }
                            }
                        }
                    }

                    stage('Build Application Package') {

                        steps {

                            script {

                                buildPackage()

                            }
                        }
                    }

                    stage('Display Security Report') {

                        steps {

                            sh 'cat gitleaks-report.json || true'
                        }
                    }
                }
            }
        }

        post {

            always {

                script {

                    archiveReports()

                }

                cleanWs()

                echo "Pipeline execution completed"
            }

            success {

                echo "Pipeline completed successfully"
            }

            unstable {

                echo "Pipeline completed with unstable status"
            }

            failure {

                echo "Pipeline execution failed"
            }
        }
    }
}