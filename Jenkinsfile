pipeline {
  agent any
  stages {
    stage ('Notice start build & deploy') {
      steps {
        slackSend(message: "jeus-blog Deploy ${env.BUILD_NUMBER} Started", color: 'good', tokenCredentialId: 'slack-key')
      }
    }
  }
}