pipeline {
    agent none
    tools { gradle 'default-gradle' }
    options { skipDefaultCheckout(true) }
    environment {
        container_name = 'pipeline-postmelon'
        use_profile = '${USE_PROFILE}'
    }
    stages {
        stage('Checkout repository') {
            agent any
            steps {
                checkout scm
            }
        }
        stage('Build and test') {
            agent any
            steps {
                sh './gradlew clean build'
            }
        }
        stage('Docker build') {
            agent any
            steps {
                sh 'docker build -t ${container_name}:latest --build-arg USE_PROFILE=${use_profile} .'
            }
        }
        stage('Docker run') {
            agent any
            steps {
                sh 'docker ps -f name=${container_name} -q | xargs --no-run-if-empty docker container stop'
                sh 'docker container ls -a -f name=${container_name} -q | xargs -r docker container rm'
                sh 'docker images --no-trunc --all --quiet --filter="dangling=true" | xargs --no-run-if-empty docker rmi'
                sh 'docker run -d --name ${container_name} -v /logs:/logs -p 9090:9090 ${container_name}:latest'
            }
        }
    }
}