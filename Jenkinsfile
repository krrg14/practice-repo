pipeline{
    agent any
    stages {
        stage('checkout') {
                steps{
                    checkout scm
                }
            }
            steps('build') {
                sh 'echo "build step(if needed) done"'
            }
        }
        stage ('deploy') {
            steps {
                withAWS(region: 'ap-south-1', credentials: '084402e5-9f28-4447-baac-33f48dd3704a') {
                    sh 'ls-la'
                    sh 'aws s3 cp ./dist/demo-jenkins-pro/ s3://demo-jenkins-pro/ --recursive --delete'
                }

        }
    }
}












/*
pipeline{
    agent any
    environment{
        AWS_REGION='ap-south-1'
        S3_BUCKET='demo-jenkins-pro'
            }

        stages{
            stage('checkout') {
                steps{
                    checkout scm
                }
            }
            stage('build'){
                steps{
                    sh 'echo "build step(if needed) done"'
                }
            }
            stage('deploy'){
                steps{
                    withcredentials([
                        usernamepassword(
                            credentialsId: 'aws-credentials',
                            usernameVariable: 'AWS_ACCESS_KEY_ID',
                            passwordVariable: 'AWS_SECRET_ACCESS_KEY'
                        )
                    ]) {
                        sh '''
                        // aws s3 sync ./build/ s3://$S3_BUCKET/ --region $AWS_REGION --delete
                        ''' 
                    }
                }
            }
    }
    post{
        success{
            echo "deployed successfully to ${env.S3_BUCKET}."
        }
        failure{
            echo "deployment failed."
        }
    }
}
*/