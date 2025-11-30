/* Requires the Docker Pipeline plugin */
/* groovylint-disable-next-line CompileStatic */
pipeline {
    // agent {
    //     docker {
    //         image 'gradle:9.1.0-jdk21-alpine'
    //         args '-u 0 -v /var/run/docker.sock:/var/run/docker.sock -v /var/lib/jenkins/.gradle:/home/gradle/.gradle'
    //     }
    // }
    agent any

    stages {
        stage('Test') {
            steps {
                sh 'pwd'
                sh 'cat /etc/os-release'
                sh 'docker --version'
            }
        }
    }
}
