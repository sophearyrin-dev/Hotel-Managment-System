## Payment service

## Docker 

1. Build docker image
```
$ docker build --tag xocbayar/payment-service .
```
2. Push docker image to docker hub
```
$ docker push --all-tags xocbayar/payment-service
```

## Kubernetes
```
$ helm repo add bitnami https://charts.bitnami.com/bitnami
$ helm install hotel-mongodb --set auth.rootPassword=secretpassword bitnami/mongodb

$ kubectl create deployment payment-service --image=xocbayar/payment-service --dry-run=client -o=yaml > payment-deployment.yaml 

$ echo --- >> payment-deployment.yaml

$ kubectl create service loadbalancer payment-service --tcp=9004:9004 --dry-run=client -o=yaml >> payment-deployment.yaml

$ kubectl apply -f payment-deployment.yaml

$ minikube tunnel
$ kubectl port-forward svc/payment-service 9004:9004
```
### Application properties
```
spring.data.mongodb.uri=mongodb://root:secretpassword@hotel-mongodb.default.svc.cluster.local:27017/paypal_DB?authSource=admin
```
# About Payment Service
- We have added three payment services as a dummy services(bankAccount,credit card and paypal)
- If we want to pay money from any of the payment methods first it will validate that the given payment information is valid or not(like whether sufficient balance or not,expiry date,information)
- If information is not valid then it will show error message with status failure
- If it is valid then it will give transaction information with status success

# To verify and pay money from creditcard
--------------------------------------
POST: localhost:9004/api/v1/payments/creditcard

{
"customerId":8,
"cardNumber":"11111111",
"ccv":"1111",
"expiryDate":"2024-04-04",
"balance":30.0
}


# To verify and pay money from Paypal
--------------------------------------
POST: localhost:9004/api/v1/payments/paypal

{
"customerId":8,
"emailAddress":"giripriya@gmail.com",
"secureKey":"1111",
"balance":30.0

}

# To verify and pay money from bank account
--------------------------------------
POST: localhost:9004/api/v1/payments/bankaccount

{
"customerId":8,
"routingNumber":1111,
"bankAccountNumber":"11111111",
"emailAddress":"giripriya@gmail.com",
"balance":30.0,
"type":"CHECKING"
}

# To find information with transaction code
--------------------------------------
GET: localhost:9004/api/v1/payments/{transaction code}
eg//
GET: localhost:9004/api/v1/payments/P110

-if there is information with request transaction code then it will show data relate to that transaciton code
-if there is no information then it will show error message

# To delete all the data from the table
--------------------------------------
DELETE: localhost:9004/api/v1/payments

