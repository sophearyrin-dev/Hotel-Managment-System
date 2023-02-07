## User service

### Signup and Signin user with different roles, after successfully sign in with registered user, system response will be session contains JWT tokens

#### First you need to create roles collection in mongodb

```
Open you terminal type following command:

1. mongosh
2. use user_DB
3. db.roles.insertMany([
   { name: "ROLE_USER" },
   { name: "ROLE_MODERATOR" },
   { name: "ROLE_ADMIN" },
])
```
## Docker 

1. Build docker image
```
$ docker build --tag xocbayar/user-service .
```
2. Push docker image to docker hub
```
$ docker push --all-tags xocbayar/user-service
```
### Kubernetes
```
$ helm repo add bitnami https://charts.bitnami.com/bitnami
$ helm install hotel-mongodb --set auth.rootPassword=secretpassword bitnami/mongodb

$ kubectl create deployment user-service --image=xocbayar/user-service --dry-run=client -o=yaml > user-deployment.yaml 

$ echo --- >> user-deployment.yaml

$ kubectl create service loadbalancer user-service --tcp=8080:8080 --dry-run=client -o=yaml >> user-deployment.yaml

$ kubectl apply -f user-deployment.yaml

$ minikube tunnel
$ kubectl port-forward svc/user-service 8080:8080
```
#### Application properties
```
spring.data.mongodb.uri=mongodb://root:secretpassword@hotel-mongodb.default.svc.cluster.local:27017/user_DB?authSource=admin
```

### Endpoints

#### You can use Swagger or Postman
##### http://localhost:8080/swagger-ui/index.html#/

### Sign up
##### Role field is not mandatory you can leave it as a blank default will be User role or choose more than one roles
~~~
POST http://localhost:8080/auth/signup
Content-Type: application/json

Roles: ["admin", "user"]

{
    "username": "super",
    "password": "test123",
    "email": "super@gmail.com",
    "roles": ["admin"]
}
{
    "username": "user",
    "password": "test123",
    "email": "user@gmail.com",
    "roles": ["user"]
}
~~~

### Sign in
~~~
POST http://localhost:8080/auth/signin
Content-Type: application/json

{
    "username": "user",
    "password": "test123"
}
~~~

## Test authorization
### Allowed for all users

~~~
GET http://localhost:8080/test/all
Content-Type: application/json
~~~
### Allowed for only users
~~~
GET http://localhost:8080/test/user
Content-Type: application/json
~~~
### Allowed for only admin
~~~
GET http://localhost:8080/test/admin
Content-Type: application/json
~~~
### Allowed for only moderator
~~~
GET http://localhost:8080/test/mod
Content-Type: application/json
~~~


