package com.sorrowblue.qiichan.ui

import android.net.Uri
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sorrowblue.qiichan.domains.tag.QiitaTag
import com.sorrowblue.qiichan.domains.tag.QiitaTagId
import kotlinx.serialization.json.Json

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
	@Test
	fun useAppContext() {
		val json = Json.stringify(
			QiitaTag.serializer(),
			QiitaTag(QiitaTagId("this is tag"), Uri.parse("https://example.com"), 101, 4502)
		)
		assertEquals(json, """{"tag_id":"this is tag","followers_count":101,"items_count":4502,"icon_url":"https://example.com"}""")
		val tag = Json.parse(QiitaTag.serializer(), json)
		assertEquals(
			tag.toString(),
			QiitaTag(
				QiitaTagId("this is tag"),
				Uri.parse("https://example.com"),
				101,
				4502
			).toString()
		)
		// Context of the app under test.
	}
}
