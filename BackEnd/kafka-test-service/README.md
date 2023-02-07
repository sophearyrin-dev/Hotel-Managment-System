# Kafka service
## Docker 

1. Build docker image
```
$ docker build --tag xocbayar/kafka-service .
```
2. Push docker image to docker hub
```
$ docker push --all-tags xocbayar/kafka-service
```

## Kubernetes
```
$ helm repo add bitnami https://charts.bitnami.com/bitnami
$ helm install hotel-kafka bitnami/kafka

$ kubectl create deployment kafka-service --image=xocbayar/kafka-service --dry-run=client -o=yaml > kafka-service-deployment.yaml 

$ echo --- >> kafka-service-deployment.yaml

$ kubectl create service clusterip kafka-service --tcp=8100:8100 --dry-run=client -o=yaml >> kafka-service-deployment.yaml

$ kubectl apply -f kafka-service-deployment.yaml

$ kubectl port-forward svc/kafka-service 8100:8100
```

### Application properties
Kafka can be accessed by consumers via port 9092 on the following DNS name from within your cluster:

    hotel-kafka.default.svc.cluster.local

Each Kafka broker can be accessed by producers via port 9092 on the following DNS name(s) from within your cluster:

    hotel-kafka-0.hotel-kafka-headless.default.svc.cluster.local:9092

To create a pod that you can use as a Kafka client run the following commands:

    kubectl run hotel-kafka-client --restart='Never' --image docker.io/bitnami/kafka:3.2.0-debian-11-r3 --namespace default --command -- sleep infinity
    kubectl exec --tty -i hotel-kafka-client --namespace default -- bash

    PRODUCER:
        kafka-console-producer.sh \

            --broker-list hotel-kafka-0.hotel-kafka-headless.default.svc.cluster.local:9092 \
            --topic test

    CONSUMER:
        kafka-console-consumer.sh \

            --bootstrap-server hotel-kafka.default.svc.cluster.local:9092 \
            --topic test \
            --from-beginning