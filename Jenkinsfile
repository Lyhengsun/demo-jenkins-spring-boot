/* Requires the Docker Pipeline plugin */
/* groovylint-disable-next-line CompileStatic */
pipeline {
    agent {
        docker {
            image 'gradle:9.1.0-jdk21-alpine'
            args '-u 0 -v /var/run/docker.sock:/var/run/docker.sock -v /var/lib/jenkins/.gradle:/home/gradle/.gradle'
        }
    }
    stages {
        stage('Test') {
            steps {
                script {
                    env.CURRENT_DIR = sh(script: 'pwd', returnStdout: true).trim()
                    env.FILES = sh(script: 'ls -a', returnStdout: true)
                }
                sh """
                    echo "[Current Directory]: ${env.CURRENT_DIR}"
                    echo "Existing Files:"
                    echo "${env.FILES}"
                """
            }
        }
    }
}
