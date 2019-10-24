//pipeline

node {

      stage('Clone repository'){

            checkout scm 
       }
  
    stage('Deploy') {
      
  
          sh "kubectl cluster-info"
          sh "kubectl create -f deployment.yaml --kubeconfig = kubeconfig"
         }
  }  
