# Instructions for Kafka Listener

## Docker 

1. Build docker image
```
$ docker build --tag xocbayar/notification-service .
```
2. Push docker image to docker hub
```
$ docker push --all-tags xocbayar/notification-service
```

## Kubernetes
```
$ helm repo add bitnami https://charts.bitnami.com/bitnami
$ helm install hotel-notification-mongodb --set auth.rootPassword=secretpassword,auth.username=hoteluser,auth.password=hotelpass,auth.database=notification_DB bitnami/mongodb

$ kubectl create deployment notification-service --image=xocbayar/notification-service --dry-run=client -o=yaml > notification-service-deployment.yaml 

$ echo --- >> notification-service-deployment.yaml

$ kubectl create service clusterip notification-service --tcp=8110:8110 --dry-run=client -o=yaml >> notification-service-deployment.yaml

$ kubectl apply -f notification-service-deployment.yaml

$ kubectl port-forward svc/notification-service 8110:8110
```
### Application properties
```
spring.data.mongodb.uri=mongodb://hoteluser:hotelpass@hotel-notification-mongodb.default.svc.cluster.local:27017/notification_DB
```
## Endpoint: localhost:8100/api/v1/producer

### JSON Object:

    {
    "customerName": "Samuel",
    "customerPhoneNumber": "675-3456-2456",
    "email": "samuel@outlook.com",
    "typeOfPayment": "Credit Card",
    "address": "1000 North 4St, MIU",
    "roomType": "Double",
    "price": "$34.50"
    }

### If everything goes fine you should see this output:

    2022-06-11 01:34:34.593  INFO 33060 --- [ntainer#0-0-C-1] c.m.e.c.p.n.messaging.KafkaTestListener  : The Payment has been made successfully!
    2022-06-11 01:34:34.594  INFO 33060 --- [ntainer#0-0-C-1] c.m.e.c.p.n.messaging.KafkaTestListener  : Information of the customer is:
    Name: Samuel
    Phone Number: 675-3456-2456
    Email: samuel@outlook.com
    Type of Payment: Credit Card
    Address: 1000 North 4St, MIU
    Type of room: Double
    Price $34.50
    Booking Date: 2022-06-11

## Requirements ##

### To test it out, follow the steps of this link: https://kafka.apache.org/quickstart