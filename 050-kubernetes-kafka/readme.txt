1. we can start kubernetes proxy: 
$kubectl proxy
- it will run proxy on default port 8001 and services will be available by URLS:
  http://127.0.0.1:8001/api/v1/namespaces/my-kafka-prod-cons/services/my-kafka-cons-service/proxy/event-bus
  http://127.0.0.1:8001/api/v1/namespaces/my-kafka-prod-cons/services/my-kafka-prod-service/proxy/publish?huan=sebastyan

2. to run kubectl via minikube we can add to ~/.bashrc next lines:
kubectl ()
{
  minikube kubectl -- $1 $2 $3 $4 $5 $6 $7
}
