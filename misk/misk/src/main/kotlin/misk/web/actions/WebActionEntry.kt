package misk.web.actions

import misk.web.resources.ResourceEntryCommon
import kotlin.reflect.KClass

/**
 * WebActionEntry
 *
 * A registration of a web action with optional configuration to customize.
 * @param actionClass: WebAction to multibind to WebServlet
 * @param url_path_prefix: defaults to "" empty string. If not empty, must match pattern requirements:
 *   - must begin with "/"
 *   - any number of non-whitespace characters (including additional path segments or "/")
 *   - must terminate with a non-"/" because rest of path will start with "/"
 */
data class WebActionEntry(
  val actionClass: KClass<out WebAction>,
  val url_path_prefix: String = "/"
) {
  init {
    ResourceEntryCommon.requireValidUrlPathPrefix(url_path_prefix)
  }
}

inline fun <reified T : WebAction> WebActionEntry(
  url_path_prefix: String = "/"
): WebActionEntry {
  return WebActionEntry(T::class, url_path_prefix)
}
