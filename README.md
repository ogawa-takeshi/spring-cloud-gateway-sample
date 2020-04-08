# Spring Cloud Gateway Sample

## Requirements

* JDK 11
* [Cloud Native Buildpacks](https://buildpacks.io/docs/install-pack/)
* [Minikube](https://kubernetes.io/docs/tasks/tools/install-minikube/)

## Setup Minikube

Start Minikube
```shell
minikube start -p sample --cpus 4 --memory 8192
```

Set profile to `sample`
```shell
minikube profile sample
✅  minikube profile was successfully set to sample
```

Make sure the context is a sample
```shell
kubectl config current-context
sample
```

Install ingress addons
```shell
minikube addons enable ingress
✅  ingress was successfully enabled

minikube addons enable ingress-dns
✅  ingress-dns was successfully enabled
```

Create a file in `/etc/resolver/minikube-test`  
Replace `<YOUR_MINIKUBE_IP>` with your `minikube ip`
```
domain test
nameserver <YOUR_MINIKUBE_IP>
search_order 1
timeout 5
```

## Create application docker images

```shell
eval $(minikube docker-env)
```

```
pack build sample/gateway --path gateway --builder cloudfoundry/cnb:bionic
pack build sample/service-a --path service-a --builder cloudfoundry/cnb:bionic
pack build sample/service-b --path service-b --builder cloudfoundry/cnb:bionic
pack build sample/service-c --path service-c --builder cloudfoundry/cnb:bionic
```

## Create k8s components on Minikube

```shell
kubectl apply -f ./k8s-manifests/ 
```

## Setup Keycloak

### Login 

http://keycloak.sample.test/  
ID: `keycloak` / PW: `keycloak`

### Add realm

Add realm  
Import: `realm-sample.json`
Name: `sample`

### Add user

Add any user to the `sample` realm.

## Endpoints

Gateway (Login):  
http://gateway.sample.test/login

Service A:  
http://gateway.sample.test/a/hello

Service B:  
http://gateway.sample.test/b/hello

Keycloak:  
http://keycloak.sample.test/

Prometheus:  
http://prometheus.sample.test/

Grafana:  
http://grafana.sample.test/

## Clean up

```shell
minikube delete -p sample
```
