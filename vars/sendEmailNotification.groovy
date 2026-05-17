def call(String buildStatus) {

    emailext(

        subject: "Jenkins Build ${buildStatus}: ${env.JOB_NAME}",

        body: """
        Build Status : ${buildStatus}

        Job Name     : ${env.JOB_NAME}
        Build Number : ${env.BUILD_NUMBER}

        Build URL:
        ${env.BUILD_URL}
        """,

        to: 'anuragmaurya883@gmail.com'
    )

    echo "Email notification sent"
}