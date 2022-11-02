pipeline {
    agent none
    tools { gradle 'default-gradle' }
    options { skipDefaultCheckout(true) }
    stages {
        stage('Checkout repository') {
            agent any
            steps {
                checkout scm
            }
        }
        stage('Build and test') {
            agent any
//             agent {
//                 docker {
//                     image 'gradle:6.6.1-jdk11-openj9'
//                     args '-v /var/lib/jenkins/workspace/{container_name}/.m2:/root/.m2'
//                 }
//             }
            steps {
                sh './gradlew clean build'
            }
        }
        stage('Docker build') {
            agent any
            steps {
                sh 'docker build -t ${container_name}:latest .'
            }
        }
        stage('Docker run') {
            agent any
            steps {
                sh 'docker ps -f name=${container_name} -q | xargs --no-run-if-empty docker container stop'
                sh 'docker container ls -a -fname=sbor_dev -q | xargs -r docker container rm'
                sh 'docker images --no-trunc --all --quiet --filter="dangling=true" | xargs --no-run-if-empty docker rmi'
                sh 'docker run -d --name ${container_name} -v /logs:/logs -p 9090:9090 ${container_name}:latest'
            }
        }
    }
}