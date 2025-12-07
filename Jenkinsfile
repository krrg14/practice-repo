pipeline{
    agent any
    stages {
        stage('checkout') {
                steps{
                    checkout scm
                }
            }
            stage('build') {
                steps {
                    sh 'echo "build step(if needed) done"'
            }
        }
        stage('deploy') {
            steps {
                withAWS(region: 'ap-south-1', credentials: '084402e5-9f28-4447-baac-33f48dd3704a') {
                    sh '''
                        ls -la
                        aws s3 sync ./dist/demo-jenkins-pro/ s3://demo-jenkins-pro/ --recursive
                    '''
                }

        }
    }
}
}  