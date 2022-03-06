param name string
param redgreenImage string
param secrets array = []
param location string = resourceGroup().location
param tags object
param containerRegistry string
param containerRegistryUsername string
@secure()
param containerRegistryPassword string

var environmentName = 'Production'
var workspaceName = '${name}-log-analytics'
var appInsightsName = '${name}-app-insights'
var containerRegistryPasswordRef = 'container-registry-password'

resource workspace 'Microsoft.OperationalInsights/workspaces@2021-06-01' = {
  name: workspaceName
  location: location
  tags: tags
  properties: {
    sku: {
      name: 'PerGB2018'
    }
    retentionInDays: 30
    workspaceCapping: {}
  }
}

resource appInsights 'Microsoft.Insights/components@2020-02-02' = {
  name: appInsightsName
  location: location
  tags: tags
  kind: 'web'
  properties: {
    Application_Type: 'web'
    Flow_Type: 'Bluefield'
  }
}

resource environment 'Microsoft.Web/kubeEnvironments@2021-03-01' = {
  name: environmentName
  location: location
  tags: tags
  properties: {
    environmentType: 'managed'
    internalLoadBalancerEnabled: false
    appLogsConfiguration: {
      destination: 'log-analytics'
      logAnalyticsConfiguration: {
        customerId: workspace.properties.customerId
        sharedKey: listKeys(workspace.id, workspace.apiVersion).primarySharedKey
      }
    }
  }
}

resource containerApp 'Microsoft.Web/containerapps@2021-03-01' = {
  name: name
  kind: 'containerapps'
  location: location
  tags: tags
  properties: {
    kubeEnvironmentId: environment.id
    configuration: {
      secrets: secrets
      registries: [
        {
          server: containerRegistry
          username: containerRegistryUsername
          passwordSecretRef: containerRegistryPasswordRef
        }
      ]
      ingress: {
        'external': true
        'targetPort': 80
      }
    }
    template: {
      containers: [
        {
          'name': 'redgreen-app'
          'image': redgreenImage
          'command': []
          'resources': {
            'cpu':'.25'
            'memory':'.5Gi'
          }
        }
      ]
    }
  }
}
