/* Requires the Docker Pipeline plugin */
pipeline {
    // agent {
    //     docker {
    //         image 'gradle:9.1.0-jdk21-alpine'
    //         args '-u 0 -v /var/run/docker.sock:/var/run/docker.sock -v /var/lib/jenkins/.gradle:/home/gradle/.gradle'
    //     }
    // }
    agent {
        label 'debian-linux'
    }

    environment {
        DOCKERHUB_CREDS = credentials('hengsun-docker-creds')
        TELEGRAM_BOT_TOKEN = credentials('projects-reporter-telegram-bot-token')
        TELEGRAM_CHAT_ID = credentials('telegram-bot-chat-id')
        DOCKER_REG     = 'hengsun'
        IMAGE_REPO     = "${env.DOCKER_REG}/test-jenkins-spring-api"
        IMAGE_TAG      = "${env.BUILD_NUMBER}"
    }

    stages {
        stage('Test') {
            environment {
                MESSAGE = '[demo-jenkins-spring] Stage Test initiated'
                MESSAGE_END_SUCCESS = '[demo-jenkins-spring] Stage Test ended successfully'
                MESSAGE_END_FAILURE = '[demo-jenkins-spring] Stage Test failed'
            }

            steps {
                sh 'curl -s -X POST https://api.telegram.org/bot$TELEGRAM_BOT_TOKEN/sendMessage -d chat_id=$TELEGRAM_CHAT_ID -d text="$MESSAGE" > /dev/null'
                sh 'pwd'
                sh 'cat /etc/os-release'
                sh 'docker --version'
            }

            post {
                failure {
                    sh 'curl -s -X POST https://api.telegram.org/bot$TELEGRAM_BOT_TOKEN/sendMessage -d chat_id=$TELEGRAM_CHAT_ID -d text="$MESSAGE_END_FAILURE" > /dev/null'
                }
                success {
                    sh 'curl -s -X POST https://api.telegram.org/bot$TELEGRAM_BOT_TOKEN/sendMessage -d chat_id=$TELEGRAM_CHAT_ID -d text="$MESSAGE_END_SUCCESS" > /dev/null'
                }
            }
        }

        stage('Build & Push Image') {
            environment {
                MESSAGE = '[demo-jenkins-spring] Stage [Build and Push Image] initiated'
                MESSAGE_END_SUCCESS = '[demo-jenkins-spring] Stage [Build and Push Image] ended successfully'
                MESSAGE_END_FAILURE = '[demo-jenkins-spring] Stage [Build and Push Image] failed'
            }

            steps {
                sh 'curl -s -X POST https://api.telegram.org/bot$TELEGRAM_BOT_TOKEN/sendMessage -d chat_id=$TELEGRAM_CHAT_ID -d text="$MESSAGE" > /dev/null'
                sh '''
                    echo $DOCKERHUB_CREDS_PSW | docker login -u $DOCKERHUB_CREDS_USR --password-stdin
                    docker build -t ${IMAGE_REPO}:${IMAGE_TAG} .
                    docker push ${IMAGE_REPO}:${IMAGE_TAG}
                    docker logout
                '''
            }

            post {
                failure {
                    sh 'curl -s -X POST https://api.telegram.org/bot$TELEGRAM_BOT_TOKEN/sendMessage -d chat_id=$TELEGRAM_CHAT_ID -d text="$MESSAGE_END_FAILURE" > /dev/null'
                }
                success {
                    sh 'curl -s -X POST https://api.telegram.org/bot$TELEGRAM_BOT_TOKEN/sendMessage -d chat_id=$TELEGRAM_CHAT_ID -d text="$MESSAGE_END_SUCCESS" > /dev/null'
                }
            }
        }
    }
}
