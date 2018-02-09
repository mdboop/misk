package misk.cloud.gcp

import com.google.auth.Credentials
import com.google.auth.oauth2.ServiceAccountCredentials
import com.google.cloud.NoCredentials
import com.google.cloud.datastore.Datastore
import com.google.cloud.datastore.DatastoreOptions
import com.google.cloud.http.HttpTransportOptions
import com.google.cloud.storage.Storage
import com.google.cloud.storage.StorageOptions
import com.google.common.util.concurrent.AbstractIdleService
import com.google.common.util.concurrent.Service
import com.google.inject.Provides
import com.google.inject.Singleton
import misk.cloud.gcp.datastore.DatastoreConfig
import misk.cloud.gcp.storage.StorageConfig
import misk.environment.Environment
import misk.inject.KAbstractModule
import misk.inject.addMultibinderBinding
import misk.inject.to
import misk.logging.getLogger
import javax.inject.Inject

/** Installs support for talking to real GCP services, either direct or via emulator */
class GoogleCloudModule : KAbstractModule() {
  override fun configure() {
    binder().addMultibinderBinding<Service>().to<GoogleCloud>()
  }

  @Provides
  @Singleton
  fun provideServiceCredentials(env: Environment): Credentials =
      if (env == Environment.DEVELOPMENT) NoCredentials.getInstance()
      else ServiceAccountCredentials.getApplicationDefault()

  @Provides
  @Singleton
  fun provideCloudDatastore(credentials: Credentials, config: DatastoreConfig): Datastore =
      DatastoreOptions.newBuilder()
          .setCredentials(credentials)
          .setHost(config.transport.host)
          .setTransportOptions(HttpTransportOptions.newBuilder()
              .setConnectTimeout(config.transport.connect_timeout_ms)
              .setReadTimeout(config.transport.read_timeout_ms)
              .build())
          .build()
          .service

  @Provides
  @Singleton
  fun provideCloudStorage(credentials: Credentials, config: StorageConfig): Storage =
      StorageOptions.newBuilder()
          .setCredentials(credentials)
          .setHost(config.transport.host)
          .setTransportOptions(HttpTransportOptions.newBuilder()
              .setConnectTimeout(config.transport.connect_timeout_ms)
              .setReadTimeout(config.transport.read_timeout_ms)
              .build())
          .build()
          .service

}

/** Logs cloud configuration on startup */
private class GoogleCloud @Inject internal constructor(
    private val datastore: Datastore,
    private val storage: Storage
) : AbstractIdleService() {
  override fun startUp() {
    log.info { "running as project ${datastore.options.projectId}" }
    log.info { "connected to datastore on ${datastore.options.host}" }
    log.info { "connected to GCS as ${storage.options.rpc.javaClass.simpleName} on ${storage.options.host}" }
  }

  override fun shutDown() {}

  companion object {
    private val log = getLogger<GoogleCloud>()
  }
}
