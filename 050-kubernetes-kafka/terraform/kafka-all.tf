provider "kubernetes" {
  config_context_cluster   = "minikube"
  config_path = "~/.kube/config"
}

resource "kubernetes_namespace" "my-kafka-prod-cons" {
  metadata {
    name = "my-kafka-prod-cons"
  }
}

resource "kubernetes_deployment" "my_kafka_cons" {
  metadata {
    name      = "my-kafka-cons"
    namespace = "my-kafka-prod-cons"

    labels = {
      app = "my-kafka-cons"
    }
  }

  spec {
    replicas = 1

    selector {
      match_labels = {
        octopusexport = "OctopusExport"
      }
    }

    template {
      metadata {
        labels = {
          app = "my-kafka-cons"

          octopusexport = "OctopusExport"
        }
      }

      spec {
        container {
          name  = "node-app-runner"
          image = "lucasjellema/node-app-runner"

          port {
            container_port = 8096
          }

          env {
            name  = "GIT_URL"
            value = "https://github.com/lucasjellema/2019-fontys-business-and-IT-agility-through-microservice-architecture"
          }

          env {
            name  = "APP_PORT"
            value = "8096"
          }

          env {
            name  = "APP_HOME"
            value = "4-kafka/event-bus-listener"
          }

          env {
            name  = "APP_STARTUP"
            value = "EventBusListener.js"
          }

          env {
            name  = "KAFKA_HOST"
            value = "kafka.kafka-ca1"
          }

          env {
            name  = "ZOOKEEPER_PORT"
            value = "9092"
          }

          env {
            name  = "KAFKA_TOPIC"
            value = "event-bus"
          }

          image_pull_policy = "IfNotPresent"
        }
      }
    }

    strategy {
      type = "RollingUpdate"
    }
  }
}

resource "kubernetes_service" "my_kafka_cons_service" {
  metadata {
    name      = "my-kafka-cons-service"
    namespace = "my-kafka-prod-cons"

    labels = {
      name = "my-kafka-cons-service"
    }
  }

  spec {
    port {
      port        = 8096
      target_port = "8096"
    }

    selector = {
      app = "my-kafka-cons"
    }

    type = "NodePort"
  }
}

resource "kubernetes_deployment" "my_kafka_prod" {
  metadata {
    name      = "my-kafka-prod"
    namespace = "my-kafka-prod-cons"

    labels = {
      app = "my-kafka-prod"
    }
  }

  spec {
    replicas = 1

    selector {
      match_labels = {
        octopusexport = "OctopusExport"
      }
    }

    template {
      metadata {
        labels = {
          app = "my-kafka-prod"

          octopusexport = "OctopusExport"
        }
      }

      spec {
        container {
          name  = "node-app-runner"
          image = "lucasjellema/node-app-runner"

          port {
            container_port = 8096
          }

          env {
            name  = "GIT_URL"
            value = "https://github.com/lucasjellema/2019-fontys-business-and-IT-agility-through-microservice-architecture"
          }

          env {
            name  = "APP_PORT"
            value = "8096"
          }

          env {
            name  = "APP_HOME"
            value = "4-kafka/event-bus-publisher"
          }

          env {
            name  = "APP_STARTUP"
            value = "EventBusPublisher.js"
          }

          env {
            name  = "KAFKA_HOST"
            value = "kafka.kafka-ca1"
          }

          env {
            name  = "ZOOKEEPER_PORT"
            value = "9092"
          }

          env {
            name  = "KAFKA_TOPIC"
            value = "event-bus"
          }

          image_pull_policy = "IfNotPresent"
        }
      }
    }

    strategy {
      type = "RollingUpdate"
    }
  }
}

resource "kubernetes_service" "my_kafka_prod_service" {
  metadata {
    name      = "my-kafka-prod-service"
    namespace = "my-kafka-prod-cons"

    labels = {
      name = "my-kafka-prod-service"
    }
  }

  spec {
    port {
      port        = 8097
      target_port = "8096"
    }

    selector = {
      app = "my-kafka-prod"
    }

    type = "NodePort"
  }
}


