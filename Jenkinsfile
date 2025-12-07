pipeline{
    agent any
    envirnment{
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
                    sh  'npm install'
                    sh 'npm run build'
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
                        aws s3 sync ./build/ s3://$S3_BUCKET/ --region $AWS_REGION --delete
                        ''' 
                    }
                }
            }
    }
    post{
        sucess{
            echo "deployed successfully to ${env.S3_BUCKET}."
        }
        failure{
            echo "deployment failed."
        }
    }
}