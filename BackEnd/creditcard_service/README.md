## Credit card service

## Docker 

1. Build docker image
```
$ docker build --tag xocbayar/creditcard-service .
```
2. Push docker image to docker hub
```
$ docker push --all-tags xocbayar/creditcard-service
```

## Kubernetes
```
$ helm repo add cnieg https://cnieg.github.io/helm-charts
$ helm install creditcard-db cnieg/h2-database --version 1.0.3

$ kubectl create deployment creditcard-service --image=xocbayar/creditcard-service --dry-run=client -o=yaml > creditcard-deployment.yaml 

$ echo --- >> creditcard-deployment.yaml

$ kubectl create service loadbalancer creditcard-service --tcp=9001:9001 --dry-run=client -o=yaml >> creditcard-deployment.yaml

$ kubectl apply -f creditcard-deployment.yaml

$ kubectl port-forward svc/creditcard-service 9001:9001
```
### Application properties
```
spring.data.mongodb.uri=mongodb://hoteluser:hotelpass@hotel-creditcard-mongodb.default.svc.cluster.local:27017/creditcard_DB
```