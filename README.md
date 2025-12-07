# 🚀 Microservices Project (Minimal Starter)

Bu proje, **IntelliJ Ultimate** üzerinde hızlıca mikroservis geliştirmeye başlamanız için hazırlanmış **minimal Spring Boot multi-module** starter yapısıdır. Proje hem **lokalde** hem de **Docker + Kubernetes** ortamında kolayca çalıştırılabilir.

## 📦 Proje Modülleri

```
microservices-project/
 ├── common-libs/
 │    └── common-dto
 └── services/
      ├── service-user        → Port 8081
      └── api-gateway         → Port 8080 (OpenFeign ile service-user'a bağlanır)
```

## ⚡ Hızlı Başlangıç (Docker olmadan, Lokal Çalıştırma)

1. Projeyi IntelliJ’de **Maven Project** olarak açın.
2. Tüm modülleri build edin:

   ```bash
   mvn -T 1C clean install
   ```

3. **service-user** servisini başlatın:

   ```bash
   mvn -pl services/service-user spring-boot:run
   ```

4. **api-gateway** servisini başlatın:

   ```bash
   mvn -pl services/api-gateway spring-boot:run
   ```

5. Test edin: http://localhost:8080/proxy/users/1

## 🐳 Docker Kullanımı

```bash
docker build -t api-gateway:1.0 -f services/api-gateway/Dockerfile services/api-gateway/
```

## ☸️ Kubernetes Dağıtımı

Manifest dosyaları: `deployment/kubernetes/{service}/deployment.yaml`

```bash
kubectl apply -f deployment/kubernetes/api-gateway/deployment.yaml
kubectl apply -f deployment/kubernetes/service-user/deployment.yaml
```

## 📝 Kubernetes Dashboard Kurulumu

```bash
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.7.0/aio/deploy/recommended.yaml
kubectl proxy
```

Dashboard:  
http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/

## 🔑 Dashboard için Token

```bash
kubectl -n kubernetes-dashboard create token admin-user
```

## 🔍 Faydalı Kubernetes Komutları

```bash
kubectl get svc
kubectl logs -l app=api-gateway --tail=50
kubectl delete pod -l app=api-gateway
```

## 🛠️ Notlar

Bu proje minimal bir starter’dır. Gerçek ortamlar için Security, ConfigMaps, Secrets, Logging, Monitoring ve CI/CD eklenmelidir.
