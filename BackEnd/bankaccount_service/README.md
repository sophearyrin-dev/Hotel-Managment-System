## Bank account service

## Docker 

1. Build docker image
```
$ docker build --tag xocbayar/bankaccount-service .
```
2. Push docker image to docker hub
```
$ docker push --all-tags xocbayar/bankaccount-service
```

## Kubernetes
```
$ helm repo add cnieg https://cnieg.github.io/helm-charts
$ helm install bankaccount-db cnieg/h2-database --version 1.0.3

$ kubectl create deployment bankaccount-service --image=xocbayar/bankaccount-service --dry-run=client -o=yaml > bankaccount-deployment.yaml 

$ echo --- >> bankaccount-deployment.yaml

$ kubectl create service clusterip bankaccount-service --tcp=9003:9003 --dry-run=client -o=yaml >> bankaccount-deployment.yaml

$ kubectl apply -f bankaccount-deployment.yaml

$ kubectl port-forward svc/bankaccount-service 9003:9003
```
### Application properties
```
spring.data.mongodb.uri=mongodb://hoteluser:hotelpass@hotel-bankaccount-mongodb.default.svc.cluster.local:27017/bankaccount_DB
```