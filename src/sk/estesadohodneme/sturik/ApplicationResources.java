package sk.estesadohodneme.sturik;

import android.content.res.Resources;

/**
 * Provides resources to various classes. User sets ApplicationResources by call
 * {@link ApplicationResources}.setResources() Implementation by Singleton
 * design pattern.
 */
public class ApplicationResources {

	private static Resources mResources = null;

	/**
	 * Gets global application resources
	 * @return resources
	 */
	public static Resources getResources() {
		return mResources;
	}

	/**
	 * Sets application resources for other classes
	 * @param resources resources from activity
	 */
	public static void setResources(Resources resources) {
		mResources = resources;
	}
}
