## API Gateway

## Docker 

1. Build docker image
```
$ docker build --tag xocbayar/apigateway .
```
2. Push docker image to docker hub
```
$ docker push --all-tags xocbayar/apigateway
```

## Kubernetes
```
$ kubectl create deployment apigateway --image=xocbayar/apigateway --dry-run=client -o=yaml > apigateway-deployment.yaml 

$ echo --- >> apigateway-deployment.yaml

$ kubectl create service loadbalancer apigateway --tcp=8762:8762 --dry-run=client -o=yaml >> apigateway-deployment.yaml

$ kubectl apply -f apigateway-deployment.yaml

$ kubectl port-forward svc/apigateway 8762:8762
```