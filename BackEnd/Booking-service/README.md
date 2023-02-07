## Booking service

## Docker 

1. Build docker image
```
$ docker build --tag xocbayar/booking-service .
```
2. Push docker image to docker hub
```
$ docker push --all-tags xocbayar/booking-service
```

## Kubernetes
```
$ helm repo add bitnami https://charts.bitnami.com/bitnami
$ helm install hotel-booking-mongodb --set auth.rootPassword=secretpassword bitnami/mongodb

$ kubectl create deployment booking-service --image=xocbayar/booking-service --dry-run=client -o=yaml > booking-deployment.yaml 

$ echo --- >> booking-deployment.yaml

$ kubectl create service loadbalancer booking-service --tcp=8999:8999 --dry-run=client -o=yaml >> booking-deployment.yaml

$ kubectl apply -f booking-deployment.yaml

$ kubectl port-forward svc/booking-service 8999:8999
```
### Application properties
```
spring.data.mongodb.uri=mongodb://hoteluser:hotelpass@hotel-booking-mongodb.default.svc.cluster.local.local:27017/booking_DB
```