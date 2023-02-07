git# Room service
Admin can create, update and delete room data

## Docker

1. Build docker image
```
$ docker build --tag xocbayar/room-service .
```
2. Push docker image to docker hub
```
$ docker push --all-tags xocbayar/room-service
```

## Kubernetes
```
$ helm repo add bitnami https://charts.bitnami.com/bitnami
$ helm install hotel-room-mongodb \
    --set auth.rootPassword=secretpassword,auth.username=hoteluser,auth.password=hotelpass,auth.database=room_DB \
    bitnami/mongodb

$ kubectl create deployment room-service --image=xocbayar/room-service --dry-run=client -o=yaml > room-deployment.yaml 

$ echo --- >> room-deployment.yaml

$ kubectl create service clusterip room-service --tcp=8088:8088 --dry-run=client -o=yaml >> room-deployment.yaml

$ kubectl apply -f room-deployment.yaml

$ kubectl port-forward svc/room-service 8088:8088
```
### Application properties
```
spring.data.mongodb.uri=mongodb://hoteluser:hotelpass@hotel-room-mongodb.default.svc.cluster.local:27017/room_DB
```

## Endpoints
### Create Room

~~~
POST http://localhost:8088/room/
Content-Type: application/json

{
    "roomNumber": 601,
    "type": "Single",
    "price": 120.0,
    "bedType": "Single",
    "numberOfBeds": null,
    "maxNumberOfGuests": null,
    "smoking": false,
    "description": null,
    "available": true,
    "roomRating":null,
    "totalRatings": 1
}
~~~

### Delete Room
```
replce room id auto generate from sysem with 1 at url
```
~~~
DELETE http://localhost:8088/room/1

~~~

## Update Room
```
replce room id auto generate from sysem with 1 at url
```
~~~
PUT http://localhost:8088/room/1
Content-Type: application/json

{
    "roomNumber": 201,
    "type": Single,
    "price": 200.00,
    "bedType": Single,
    "numberOfBeds": 3,
    "maxNumberOfGuests": 3,
    "smoking": false,
    "description": null,
    "available": true
}


~~~
### Show All Rooms
~~~
GET http://localhost:8088/room/
Content-Type: application/json
~~~


### Checkout Room
```
replce room id auto generate from sysem with 1 at url
```
~~~
GET http://localhost:8088/room/checkout/1

~~~

### For Controller Testing
~~~
Run Room Service for RoomControllerTest if not cannot run test case


~~~