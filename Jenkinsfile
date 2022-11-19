pipeline {
    agent any
	/*environment {
    		DOCKERHUB_CREDENTIALS=credentials('dockerhub')
    		}*/
    		tools {
    		maven "M2_HOME"
    		}
    stages {
        stage('Checkout GIT') {
            steps {
                echo 'Pulling... ';
                    git branch: 'main',
                        url : 'https://github.com/marouenkaroui/Spring2.git';
            }
        }

        stage('Cleaning the project') {
             
             
            steps {
                echo 'cleaning project ...'
                //sh 'mvn -Dmaven.test.failure.ignore=true clean package'
            }
        }
        
        stage('Compiling the artifact') {
             
            steps {
                echo "compiling"
                sh 'mvn compile'
               
            }
        } 
        stage('Nexus') {
            steps {
                sh 'mvn deploy'
            }
        }  
        stage('Code Quality Check via SonarQube') {
            steps {
                script {
                        sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=admin.'
						}
               
            }
        }
           
     stage ('Mockito/Junit') {
             steps {
            sh 'mvn test -Dtest="FactureServiceImplMockito" '
            sh 'mvn test -Dtest="ReglementServiceImpTest" '
            }
        }
        stage('Docker build')
        {
            steps {
                 sh 'docker build -t $DOCKERHUB_CREDENTIALS_USR/tpachatproject-1.0:latest .'
            }
        }
         stage ('Docker login'){
        	steps {
        	sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR -p $DOCKERHUB_CREDENTIALS_PSW'
        	}
        }
         stage('Docker compose ') {
              steps {
                  sh "docker compose -f docker-compose.yml up -d  "
              }
        }
       
        
        stage ('Docker push'){
        	steps {
        	sh 'docker push $DOCKERHUB_CREDENTIALS_USR/tpachatproject-1.0:latest'
        	}
        }
        
        stage('mailling'){
	           steps {
		            mail bcc: '', body: '''Hello from Jenkins,
		            Devops Pipeline returned success.
		            Best Regards''', cc: '', from: 'louatisahar23@gamil.com', replyTo: '', subject: 'Devops Pipeline', to: 'sahar.louati@esprit.tn'
	            }
	       }
       
    }
    post {
      	always {
      		sh 'docker logout'
      		emailext attachLog: true, body: "${env.BUILD_URL} has result ${currentBuild.result}", compressLog: true, subject: "Status of pipeline: ${currentBuild.fullDisplayName}", to: 'sahar.louati@esprit.tn'
          	emailext body: 'A Test EMail', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: 'Test'
  		
      	}
      }
}