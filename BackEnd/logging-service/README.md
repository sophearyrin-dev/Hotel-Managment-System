# Logging service

## Docker 

1. Build docker image
```
$ docker build --tag xocbayar/logging-service .
```
2. Push docker image to docker hub
```
$ docker push --all-tags xocbayar/logging-service
```

## Kubernetes
```
$ kubectl create deployment logging-service --image=xocbayar/logging-service --dry-run=client -o=yaml > logging-deployment.yaml 

$ echo --- >> logging-deployment.yaml

$ kubectl create service clusterip logging-service --tcp=9500:9500 --dry-run=client -o=yaml >> logging-deployment.yaml

$ kubectl apply -f logging-deployment.yaml

$ kubectl port-forward svc/logging-service 9500:9500
```
