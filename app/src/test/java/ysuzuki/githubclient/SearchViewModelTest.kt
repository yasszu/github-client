package ysuzuki.githubclient

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import ysuzuki.githubclient.service.SharedPreference
import ysuzuki.githubclient.view.search.SearchViewModel

/**
 * Created by Yasuhiro Suzuki on 2017/05/20.
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(SearchViewModel::class, SharedPreference::class)
class SearchViewModelTest {

    lateinit var mockPreference: SharedPreference

    @Before
    fun setUp() {
        mockPreference = PowerMockito.mock(SharedPreference::class.java)
        Mockito.`when`(mockPreference.getQualifiers()).thenReturn("Android")
    }

    @Test
    @Throws(Exception::class)
    fun getQualifiers_test() {
        assertEquals(mockPreference.getQualifiers(), "Android")
    }

}