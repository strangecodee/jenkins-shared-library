def call(String envName) {

    def currentDate = new Date().format("EEEE, dd MMMM yyyy")

    echo """
╔══════════════════════════════════════════════════════╗
║               CI/CD PIPELINE STARTED                 ║
╚══════════════════════════════════════════════════════╝

 Environment    : ${envName}
 Execution Date : ${currentDate}

 Pipeline Status: STARTED

════════════════════════════════════════════════════════
"""
}