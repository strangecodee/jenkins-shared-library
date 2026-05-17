def call(String repoUrl, String branch = 'main') {

    checkout scmGit(
        branches: [[name: "*/${branch}"]],
        extensions: [],
        userRemoteConfigs: [[
            url: repoUrl
        ]]
    )

    echo """
 Repository Checkout Successful
 Branch : ${branch}
"""
}