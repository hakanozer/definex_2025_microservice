#!/bin/bash

# Build Java artifacts
echo "Building Java artifacts..."
mvn clean install -DskipTests

# Build Docker images
echo "Building Docker images..."
docker build -t api-gateway:1.0 services/api-gateway
docker build -t service-user:1.0 services/service-user

# All pods - app delete
kubectl delete pod -l app=api-gateway
kubectl delete pod -l app=service-user
sleep 10 && kubectl get pods

# Apply Kubernetes manifests
echo "Deploying to Kubernetes..."
kubectl apply -f deployment/kubernetes/api-gateway/deployment.yaml
kubectl apply -f deployment/kubernetes/service-user/deployment.yaml

echo "Deployment applied. Check status with: kubectl get pods"
