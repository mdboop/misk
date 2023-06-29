plugins {
  `gradle-enterprise`
}

gradleEnterprise {
  buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
  }
}

include(":misk")
include(":misk-action-scopes")
include(":misk-actions")
include(":misk-admin")
include(":misk-aws")
include(":misk-aws-dynamodb")
include(":misk-aws-dynamodb-testing")
include(":misk-aws2-dynamodb")
include(":misk-aws2-dynamodb-testing")
include(":misk-bom")
include(":misk-clustering")
include(":misk-config")
include(":misk-core")
include(":misk-cron")
include(":misk-crypto")
include(":misk-datadog")
include(":misk-events")
include(":misk-events-core")
include(":misk-events-testing")
include(":misk-exceptions-dynamodb")
include(":misk-feature")
include(":misk-feature-testing")
include(":misk-gcp")
include(":misk-gcp-testing")
include(":misk-grpc-reflect")
include(":misk-grpc-tests")
include(":misk-hibernate")
include(":misk-hibernate-testing")
include(":misk-hotwire")
include(":misk-inject")
include(":misk-jdbc")
include(":misk-jdbc-testing")
include(":misk-jobqueue")
include(":misk-jobqueue-testing")
include(":misk-jooq")
include(":misk-launchdarkly")
include(":misk-launchdarkly-core")
include(":misk-lease")
include(":misk-metrics")
include(":misk-metrics-digester")
include(":misk-metrics-testing")
include(":misk-policy")
include(":misk-policy-testing")
include(":misk-prometheus")
include(":misk-proto")
include(":misk-redis")
include(":misk-redis-testing")
include(":misk-service")
include(":misk-slack")
include(":misk-testing")
include(":misk-transactional-jobqueue")
include(":misk-warmup")
include(":samples:exemplar")
include(":samples:exemplarchat")
