def call() {

    input(
        message: 'Approve Production Build?',
        ok: 'Deploy'
    )

    echo "Production deployment approved"
}